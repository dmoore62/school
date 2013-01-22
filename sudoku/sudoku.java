import java.util.*;

public class sudoku {
	
	public static void main(String[] args){
		
		Scanner inFile = new Scanner(System.in);
		
		int games_i = inFile.nextInt();
		
		for(int main_game_i = 1; main_game_i <= games_i; main_game_i ++){
			
			//Cell[][] board = new Cell[9][9];
			Board b = new Board();

			//init(board, inFile);
			b.init(inFile);

			boolean go_again = false;

			do{

				b.calc_all_pos_nums();

				b.print_board(main_game_i);

				go_again = b.update_board();

				//b.print_board(main_game_i);

			}while(go_again);
			
			b.print_board(main_game_i);
			
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
			if(board[row][col].pos_nums.contains(board[(row+i)%9][col].get_val())){
				board[row][col].pos_nums.remove(board[(row+i)%9][col].get_val());
				
			}
		}
	}
	
	public static void check_column(Cell[][] board, int row, int col){
		for (int i = 1; i < 9; i ++){
			if(board[row][col].pos_nums.contains(Integer.valueOf(board[row][(col + i)%9].value))){
				board[row][col].pos_nums.remove(Integer.valueOf(board[row][(col + i)%9].value));
			}
		}
	}

	public static void check_square(Cell[][] board, int row, int col){
		int sq_row = row/3;
		int sq_col = col/3;
		int row_i = row%3;
		int col_i = col%3;
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
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j ++){
				if(board[i][j].is_set == false && board[i][j].pos_nums.size() == 1){
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
		//System.out.println("creating cell at: "+ row +", "+ col + " with value: "+ val);
		this.row = row;
		this.col = col;
		if(val == 0){
			this.is_set = false;
			this.pos_nums = new ArrayList<Integer>();
			for (int i = 1; i <= 9; i++){
				this.pos_nums.add(new Integer(i));
			}//end i for
			
		}else{
			this.is_set = true;
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
		this.is_solved = false;
	}
	
	public void print_board(int k){
		System.out.println("Test case "+ k +":");
		System.out.println();
		for (int i = 0; i < 9; i ++){
			for (int j = 0; j < 9; j ++){
				System.out.print(theBoard[i][j].value+" ");
			}
			System.out.printf("\n");
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
				theBoard[i][j] = new Cell(i, j, s.nextInt());
			}
		}
	}
	
	public void check_row(int row, int col){
		/*System.out.println("check row!");
		System.out.println("pos_nums(in):");
		for(int i = 0; i < 9; i ++){
			System.out.print(theBoard[row][col].pos_nums.get(i));
		}*/
		for (int i = 1; i < 9; i ++){
			if(theBoard[row][col].pos_nums.contains(theBoard[(row+i)%9][col].get_val())){
				theBoard[row][col].pos_nums.remove(Integer.valueOf(theBoard[(row+i)%9][col].get_val()));
				
			}
		}
		//System.out.println(theBoard[row][col].pos_nums.get(0));

	}
	
	public void check_col(int row, int col){
		//System.out.println("check col!");
		for (int i = 1; i < 9; i ++){
			if(theBoard[row][col].pos_nums.contains(theBoard[row][(col+i)%9].get_val())){
				theBoard[row][col].pos_nums.remove(Integer.valueOf(theBoard[row][(col+i)%9].get_val()));
				
			}
		}
		//System.out.println(theBoard[row][col].pos_nums.get(0));

	}
	
	public void check_square(int row, int col){
		//System.out.println("check square!");
		int sq_row = row/3;
		int sq_col = col/3;
		//int row_i = row%3;
		//int col_i = col%3;
		for(int i = sq_row * 3; i < 3; i ++){
			for(int j = sq_col * 3; j < 3; j ++){
				if((i != row) && (j != col)){
					if(theBoard[row][col].pos_nums.contains(theBoard[i][j].get_val())){
						theBoard[row][col].pos_nums.remove(Integer.valueOf(theBoard[i][j].get_val()));
					}
				}
			}
		}
		//System.out.println(theBoard[row][col].pos_nums.get(0));

	}
	
	public void calc_all_pos_nums(){
		//System.out.println("calc all pos nums!");
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j++){
				if(theBoard[i][j].is_set == false){
					this.check_row(i, j);
					this.check_col(i, j);
					this.check_square(i, j);
				}
			}
		}
	}
	
	public boolean update_board(){
		boolean gets_updated = false;
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j++){
				if(theBoard[i][j].is_set == false){
					//System.out.println("noticed false");
					if(theBoard[i][j].pos_nums.size() == 1){
						//System.out.println("noticed single");
						theBoard[i][j].value = theBoard[i][j].pos_nums.get(0);
						//System.out.println("Value"+ theBoard[i][j].get_val());
						theBoard[i][j].is_set = true;
						gets_updated = true;
					}
				}	
			}
		}
		return gets_updated;
	}
	
	
}//end board class