/*
Prob8.java
Daniel Moore
COP 3503 Assignment 3
Finds least number of moves solve 8 puzzles
*/
import java.util.*;

public class prob8 {

	public static void main (String args[]){

		Scanner inFile = new Scanner(System.in);

		int num_puzzles;
		int i;

		num_puzzles = inFile.nextInt();

		for(int main_index = 0; main_index < num_puzzles; main_index ++){

			int[] scan_ary = new int[9];

			for(i = 0; i < 9; i ++){
				scan_ary[i] = inFile.nextInt();
			}

			//create boards, queues and hashtable
			board initial_board = new board(scan_ary);
			Queue<board> q = new LinkedList<board>();
			Map t = new HashMap();
			
			
		}//end main for loop

	}//end main method

}//end prob8 class

class board {
	public int[] tiles = new int[9];
	String asString = "";
	public board (int ary[]){
		
		for(int i = 0; i < 9; i ++){
			this.tiles[i] = ary[i];
			this.asString += ary[i];
		}//end i for

	}//end board constructor

	public board swap(board b, int p1, int p2){
		int temp = b.tiles[p1];
		b.tiles[p1] = b.tiles[p2];
		b.tiles[p2] = temp;

		return b;
	}

	public boolean solved(){
		for(int i = 0; i < 8; i ++){
			if(this.tiles[i] != i + 1)
				return false;
		}

		return true;
	}

	public String toString(){
		return this.toString();
	}
}//end board class