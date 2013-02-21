#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

//GLOBALS

//function declarations
struct node{
	char* value;
	struct node* next;
	};

enum TOKEN{
	nulsym = 1, identsym, numbersym, plussym, minussym,
	multsym, slashsym, oddsym, eqsym, neqsym, lessym, leqsym,
	gtrsym, geqsym, lparentsym, rparentsym, commasym, semicolonsym,
	periodsym, becomessym, beginsym, endsym, ifsym, thensym,
	whilesym, dosym, callsym, constsym, intsym, procsym, writesym,
	readsym, elsesym } token_type;

struct node* add_rear(struct node* ll, char* val);

void print_list(struct node* list, FILE* fpout);

int check_word(const char** words, char* cur_id);

int check_sym(char syms[], char ch);

char* int_to_string(char* pass_str, int d);

int main(void){
	FILE* fpin = fopen("test.in", "r");
	FILE* fpout = fopen("output.txt", "w");

	char syms[13] = "+-*/()=,.<>;:";

	const char* res_words[13];
	res_words[0] = "const";
	res_words[1] = "int";
	res_words[2] = "procedure";
	res_words[3] = "call";
	res_words[4] = "begin";
	res_words[5] = "end";
	res_words[6] = "if";
	res_words[7] = "then";
	res_words[8] = "else";
	res_words[9] = "while";
	res_words[10] = "do";
	res_words[11] = "read";
	res_words[12] = "write";

	struct node* ll = NULL;
	int lex_list_i = 0;
	int lex_list_size = 20;
	char* cur_id = (char*)calloc(13, sizeof(char));
	char* pass_str = NULL;
	int cur_index = 0;
	int ret_type;
	int ret_sym;
	int end;
	int i;
	int again;

	char ch;
	char next_ch = '\0';

	fprintf(fpout, "Source Code:\n");
	while((ch = fgetc(fpin)) != EOF){
		fprintf(fpout, "%c", ch);
	}
	fprintf(fpout, "\n\n");

	fclose(fpin);

	fpin = fopen("test.in", "r");

	fprintf(fpout,  "Lexeme Table:\n");
	fprintf(fpout, "lexeme\ttoken type\n");
	while((ch = fgetc(fpin)) != EOF){
		
		if(cur_id == NULL){
			cur_id = (char*)calloc(13, sizeof(char));
			if(next_ch != '\0'){
				cur_id[cur_index] = next_ch;
				cur_index ++;
				
			}
		}
		
		if(!isblank(ch) && isprint(ch)){
			if(isalpha(ch)){
				if(cur_index < 11){
					cur_id[cur_index] = ch;
					cur_index++;
					if(isdigit(cur_id[0])){
						printf("ERROR: Variable does not start with a letter\n");
					}else{
						ret_type = check_word(res_words, cur_id);
						if(ret_type >= 0){
							switch(ret_type){
								case 0: //const
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) constsym);
									break;
								case 1: //int
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) intsym);
									pass_str = int_to_string(pass_str, (enum TOKEN) intsym);
									ll = add_rear(ll, pass_str);
									break;
								case 2: //procedure
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) procsym);
									break;
								case 3:	//call
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) callsym);
									break;
								case 4: //begin
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) beginsym);
									break;
								case 5: //end
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) endsym);
									break;
								case 6: //if
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) ifsym);
									break;
								case 7: //then
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) thensym);
									break;
								case 8: //else
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) elsesym);
									break;
								case 9: //while
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) whilesym);
									break;
								case 10: //do
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) dosym);
									break;
								case 11: //read
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) readsym);
									break;
								case 12: //write
									fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) writesym);
									break;
							}//end switch

							
							cur_id = NULL;
							cur_index = 0;
							
						}
					}
				}else{
					printf("ERROR: Name too long!\n");
				}
			}else if(isdigit(ch)){ 
				if(cur_index == 5){
					for(i = 0; i < 5; i++){
						if(isalpha(cur_id[i])){
							break;
						}
						if(i == 4){
							printf("ERROR: Number too long\n");
						}
					}
				}
				if(cur_index < 11){
					cur_id[cur_index] = ch;
					cur_index ++;
				}else{
					printf("ERROR: Name too long\n");
				}
			}else if(ispunct(ch)){
				ret_sym = check_sym(syms, ch);
				
				if(ret_sym >= 0){
					for(i = 0, end = strlen(cur_id); i < end; i ++){
							if(!isdigit(cur_id[i])){
								fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) identsym);
								break;
							}
							if(isdigit(cur_id[i]) && i == (end - 1)){
								fprintf(fpout, "%s\t\t%d\n", cur_id, (enum TOKEN) numbersym);
							}
						}
					switch(ret_sym){

						case 0: //+
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) plussym);
							break;
						case 1: //-
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) minussym);
							break;
						case 2: //*
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) multsym);
							break;
						case 3: ///
							next_ch = fgetc(fpin);
							if(next_ch == '*'){
								again = 1;
								while(again == 1){
									next_ch = fgetc(fpin);
									while(next_ch != '*'){
										next_ch = fgetc(fpin);
									}
									next_ch = fgetc(fpin);
									if(next_ch == '/'){
										again = 0;
										next_ch = '\0';
										break;
									}

								}
							}else{
								fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) slashsym);
							}
							break;
						case 4: //(
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) lparentsym);
							break;
						case 5: //)
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) rparentsym);
							break;
						case 6: //=
							next_ch = fgetc(fpin);
							if(next_ch == '<'){
								fprintf(fpout, "=<\t\t%d\n", (enum TOKEN) leqsym);
								next_ch = '\0';
							}else if(next_ch == '>'){
								fprintf(fpout, "=>\t\t%d\n", (enum TOKEN) geqsym);
								next_ch = '\0';
							}else{							
								fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) eqsym);
							}
							break;
						case 7: //,
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) commasym);
							break;
						case 8: //.
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) periodsym);
							break;
						case 9: //<
							next_ch = fgetc(fpin);
							if(next_ch == '>'){
								fprintf(fpout, "<>\t\t%d\n", (enum TOKEN) neqsym);
							}else{
								fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) lessym);
							}
							break;
						case 10: //>
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) gtrsym);
							break;
						case 11: //;
							
							fprintf(fpout, "%c\t\t%d\n", ch, (enum TOKEN) semicolonsym);
							break;
						case 12: //:
							next_ch = fgetc(fpin);
							if(next_ch == '='){
								
								fprintf(fpout, ":=\t\t%d\n", (enum TOKEN) becomessym);
								next_ch = '\0';
							}
							break;
					}//end switch

					cur_id = NULL;
					cur_index = 0;

				}else{
					printf("ERROR: Invalid Symbols\n");
				}
			}else{
				printf("ERROR: Invalid Symbols\n");
			}
		}

	}

	fprintf(fpout, "\nLexeme List:\n");
	print_list(ll, fpout);

	fclose(fpin);
	fclose(fpout);

}//end main

int check_word(const char** words, char* cur_id){
	int i;
	for(i = 0; i < 13; i ++){
		if(strcmp(words[i], cur_id) == 0){
			return i;
		}
	}
	return -1;
}//end check_word

int check_sym(char sym[], char ch){
	int i;
	
	for(i = 0; i < 13; i++){
		if(sym[i] == ch){
			return i;
		}
	}
	return -1;
}//end check_sym

struct node* add_rear(struct node* ll, char* val){
	
	struct node* new = NULL;
	struct node* current = NULL;

	new = (struct node*)malloc(sizeof(struct node));

	new->value = (char*)malloc(11*sizeof(char));

	strcpy(new->value, val);
	
	new->next = NULL;

	current = ll;

	if(ll == NULL){
		return new;
	}

	while(current->next != NULL){
		current = current->next;
	}

	current->next = new;

	return ll;
}//end add_rear

void print_list(struct node* ll, FILE* fpout){
	while (ll != NULL){
		fprintf(fpout, "%s ", ll->value);
		ll = ll->next;
	}
}//end print_list

char* int_to_string(char* pass_str, int d){
	pass_str = NULL;

	pass_str = (char *)calloc(11, sizeof(char));
	sprintf(pass_str, "%d", d);

	return pass_str;
}//end int_to_string