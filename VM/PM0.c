#include <stdlib.h>
#include <stdio.h>

#define MAX_STACK_HEIGHT 2000
#define MAX_CODE_LENGTH 500
#define MAX_LEXI_LEVELS 3

//structs and function dec;arations
struct instruction{
       int op;
       int l;
       int m;       
};

int base(int l, int base);

//functions
int main(void){
//main declarations
       FILE* fpin = fopen("file.in", "r");
       FILE* fpout = fopen("output.txt", "w");
       struct instruction instr_ary[MAX_CODE_LENGTH];
//main statements    
       //fill array of structs
       int struct_index = 0;
       int i;
       while(!feof(fpin)){
          fscanf(fpin, "%d", &i);
          instr_ary[struct_index].op = i;
          fscanf(fpin, "%d", &i);
          instr_ary[struct_index].l = i;
          fscanf(fpin, "%d", &i);
          instr_ary[struct_index].m = i;
          struct_index ++;                
       }
       
       //print instructions
       fprintf(fpout, "Line\tOP\tL\tM\n");
       int print_index;
       for(print_index = 0; print_index < struct_index - 1; print_index ++){
               fprintf(fpout, "%d\t\t%d\t%d\t%d\n", print_index, instr_ary[print_index].op, instr_ary[print_index].l, instr_ary[print_index].m);
       }//end print for
}//end main

/*int base(int l, int base){
//base declarations
       int b1 = base;
//base statements
       while(l > 0)
       {
               b1 = stack[b1];
               l --;        
       }
       return b1;    
}//end base*/
