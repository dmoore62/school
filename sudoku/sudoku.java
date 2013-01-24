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

				//b.print_board(main_game_i);

				go_again = b.update_board();

				//b.print_board(main_game_i);

			}while(go_again);

			if(b.solve_rec(0, 0)){
			
				b.print_board(main_game_i);
			
			}else{
				
				System.out.println("Test case "+ main_game_i +":");
				System.out.println();
				System.out.println("This board has no solution.");
				System.out.println();
				System.out.println();
			}

		}

		
	}//end main method
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
		int sq_row = (row/3) * 3;
		int sq_col = (col/3) * 3;
		int row_limit = sq_row + 3;
		int col_limit = sq_col + 3;
		for(int i = sq_row; i < row_limit; i ++){
			for(int j = sq_col; j < col_limit; j ++){
				if(!((i == row) && (j == col))){
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
	

	public boolean solve_rec(int row, int col){
		int next_row = row;
		int next_col = col;
		//System.out.println("Cursor on: "+ row +", "+ col);
		//this.print_board(0);

		if(this.solved()){
			return true;
		}else{
			
			while(theBoard[row][col].value != 0){
				if(!(row == 8 && col == 8)){
					col ++;
					if(col > 8){
						col = 0;
						row ++;
					}
				}
			}
			
			for(int loop_i = 0; loop_i < theBoard[row][col].pos_nums.size(); loop_i ++){
				theBoard[row][col].value = theBoard[row][col].pos_nums.get(loop_i);
				theBoard[row][col].is_set = true;
				if(this.valid_move(row, col)){
					/*System.out.println("Valid Move!");
					System.out.println("Cursor on: "+ row +", "+ col);
					this.print_board(0);*/
					if(!(row == 8 && col == 8)){
						next_col = col + 1;
						if(next_col > 8){
							next_col = 0;
							next_row = row + 1;
						}
					}else{
						if(!this.solved()){
							return false;
						}
					}
					if(this.solve_rec(next_row, next_col)){
						return true;
					}else{
						theBoard[row][col].value = 0;
						theBoard[row][col].is_set = false;
					}
				}else{
					/*System.out.println("NOT Valid Move!");
					System.out.println("Cursor on: "+ row +", "+ col);
					this.print_board(0);*/
					theBoard[row][col].value = 0;
					theBoard[row][col].is_set = false;
				}
			}
			/*System.out.println("NO Valid Moves! - Went back");
			System.out.println("Cursor on: "+ row +", "+ col);
			this.print_board(0);*/
			theBoard[row][col].value = 0;
			theBoard[row][col].is_set = false;
			return false;
		}
		
	}

	public boolean solved(){
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j ++){
				if(theBoard[i][j].value == 0){
					return false;
				}
				if(!this.valid_row(i, j)){
					return false;
				}
				if(!this.valid_col(i, j)){
					return false;
				}
				if(!this.valid_square(i, j)){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean valid_row(int row, int col){
		for(int i = 1; i < 9; i ++){
			//System.out.println("current index: "+ (row+i)%9 +", "+ col);
			if(theBoard[row][col].value == theBoard[(row + i)%9][col].value){
				return false;
			}
		}
		return true;
	}

	public boolean valid_col(int row, int col){
		for(int i = 1; i < 9; i ++){
			if(theBoard[row][col].value == theBoard[row][(col + i)%9].value){
				return false;
			}
		}
		return true;
	}

	public boolean valid_square(int row, int col){
		
		int sq_row = (row/3) * 3;
		int sq_col = (col/3) * 3;
		int row_limit = sq_row + 3;
		int col_limit = sq_col + 3;
		for(int i = sq_row; i < row_limit; i ++){
			for(int j = sq_col; j < col_limit; j ++){
				if(!((i == row) && (j == col))){
					if(theBoard[row][col].value == theBoard[i][j].value){
						return false;
					}
				}
			}
		}
		return true;

	}

	public boolean valid_move(int row, int col){
		if(!this.valid_row(row, col)){
			return false;
		}
		if(!this.valid_col(row, col)){
			return false;
		}
		if(!this.valid_square(row, col)){
			return false;
		}

		return true;
	}
}//end board class