import java.util.*;

public class sudoku {
	
	public static void main(String[] args){
		
		Scanner inFile = new Scanner(System.in);
		
		int games_i = inFile.nextInt();
		
		for(int main_game_i = 1; main_game_i <= games_i; main_game_i ++){
			
			Cell[][] board = new Cell[9][9];
			
			init(board, inFile);

			boolean go_agian = false;

			do{

				calc_pos_nums(board);

				go_again = update_board(board);

			}while(go_again)
			
			print_board(board, main_game_i);
			
		}
		
	}//end main method

	public static void init(Cell[][] board, Scanner s){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j ++){
				board[i][j] = new Cell(i, j, s.nextInt());
			}
		}
	}

	public static void print_board(Cell[][] board, int k){
		System.out.println("Test case "+ k +":");
		System.out.println();
		for (int i = 0; i < 9; i ++){
			for (int j = 0; j < 9; j ++){
				System.out.print(board[i][j].value+" ");
			}
			System.out.printf("\n");
		}
		System.out.println();
		System.out.println();
	}

	public static void check_row(Cell[][] board, int row, int col){
		for (int i = 1; i < 9; i ++){
			if(board[row][col].pos_nums.contains(board[(row + i)%9][col].value))}{
				board[row][col].pos_nums.remove(Integer.valueOf(board[(row + i)%9][col].value));
			}
		}
	}
	
	public static void check_column(Cell[][] board, int row, int col){
		for (int i = 1; i < 9; i ++){
			if(board[row][col].pos_nums.contains(board[row][(col + i)%9].value))}{
				board[row][col].pos_nums.remove(Integer.valueOf(board[row][(col + i)%9].value));
			}
		}
	}

	public static void check_square(Cell[][] board, int row, int col){
		sq_row = row/3;
		sq_col = col/3;
		row_i = row%3;
		col_i = col%3;
		for(int i = sq_row * 3; i < 3; i ++){
			for(int j = sq_col * 3; j < 3; j ++){
				if((i != row) && (j != col)){
					if(board[row][col].pos_nums.contains(board[i][j].value)){
						board[row][col].pos_nums.remove(Integer.valueOf(board[i][j].value));
					}
				}
			}
		}
	}

	public static void calc_pos_nums(Cell[][] board){
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j ++){
				check_row(board, i, j);
				check_column(board, i, j);
				check_square(board, i, j);
			}
		}
	}

	public static boolean update_board(Cell[][] board){
		boolean updated = false;
		for(i = 0; i < 9; i ++){
			for(j = 0; j < 9; j ++){
				if(board[i][j].is_set == false && board[i][j].pos_nums.size == 1){
					board[i][j].value = board[i][j].pos_nums.get(0);
					board[i][j].is_set = true;
					updated = true;
				}
			}
		}

		return updated;
	}
}

class Cell {
	boolean is_set;
	int row;
	int col;
	public ArrayList<Integer> pos_nums;
	int value;
	
	public Cell(int row, int col, int val){
		this.row = row;
		this.col = col;
		if(val == 0){
			is_set = false;
			pos_nums = new ArrayList<Integer>();
			for (int i = 1; i >= 9; i++){
				pos_nums.add(i);
			}//end i for
			
		}else{
			is_set = true;
			this.value = val;
		}
		
	}//end cell constructor
	
	public int get_val(){
		return this.value;
	}
	
	public int set_val(int val){
		return val;
	}
	
	public int get_row(){
		return this.row;
	}
	
	public int get_col(){
		return this.col;
	}
	
	public void update_pos_nums(int val){
		if(this.pos_nums.contains(val)){
			this.pos_nums.remove(Integer.valueOf(val));
		}
	}
}//end cell class

class Board {
	Cell [][] theBoard;
	boolean is_solved;
	
	public Board(){
		this(9, 9);
	}
	
	public Board(int rows, int cols){
		theBoard = new Cell [rows][cols];
		is_solved = false;
	}
	
	public void print_board(int k){
		System.out.println("Test case "+ k +":");
		System.out.println();
		for (int i = 0; i < 9; i ++){
			for (int j = 0; j < 9; j ++){
				System.out.print(theBoard[i][j].value+" ");
			}
			//System.out.printf("\n");
		}
		System.out.println();
		System.out.println();
	}
	
	public int check_cell(int row, int col){
		return theBoard [row][col].value;
	}
	
	public void init(Scanner s){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j ++){
				theBoard[i][j].set_val(s.nextInt());
			}
		}
	}
	
	public void check_row(Cell c, int size){
		for(int i = 1; i < size; i ++){
			
		}
	}
	
	public void check_col(Cell c, int size){
		
	}
	
	public void check_square(Cell c){
		check_row(theBoard[c.row][c.col], 3);
		check_col(theBoard[c.row][c.col], 3);
	}
	
	public void calc_all_pos_nums(){
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j++){
				if(theBoard[i][j].is_set == false){
					this.check_row(theBoard[i][j], 9);
					this.check_col(theBoard[i][j], 9);
					this.check_square(theBoard[i][j]);
				}
			}
		}
	}
	
	public void update_board(){
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j++){
				if(theBoard[i][j].is_set == false){
					if(theBoard[i][j].pos_nums.size() == 1){
						theBoard[i][j].set_val(theBoard[i][j].pos_nums.get(0));
						theBoard[i][j].is_set = true;
					}
				}	
			}
		}
	}
	
	
}//end board class