import java.util.*;

public class sudoku {
	
}

class cell {
	boolean is_set;
	int row;
	int col;
	public ArrayList<Integer> pos_nums;
	int value;
	
	public cell(int row, int col, int val){
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
	
	public void set_val(int val){
		this.value = val;
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

class board {
	cell [][] theBoard;
	boolean is_solved;
	
	public board(){
		this(9, 9);
	}
	
	public board(int rows, int cols){
		theBoard = new cell [rows][cols];
		is_solved = false;
	}
	
	public void print_board(){
		for (int i = 0; i < 9; i ++){
			for (int j = 0; j < 9; j ++){
				System.out.print(theBoard[i][j].value+" ");
			}
			System.out.printf("\n");
		}
		System.out.printf("\n\n");
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
	
	public void check_row(cell c){
		
	}
	
	public void check_col(cell c){
		
	}
	
	public void check_square(cell c){
		
	}
	
	public void calc_all_pos_nums(){
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j++){
				check_row(theBoard[i][j]);
				check_col(theBoard[i][j]);
				check_square(theBoard[i][j]);
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
