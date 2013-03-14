/*
Prob8.java
Daniel Moore
COP 3503 Assignment 3
Finds least number of moves solve 8 puzzles
*/
import java.util.*;
import java.lang.*;
import java.io.*;

public class prob8 {

	public static void main (String args[]){

		Scanner inFile = new Scanner(System.in);

		String initial_board = "";

		int num_puzzles, solution;
		int i;

		num_puzzles = inFile.nextInt();

		for(int main_index = 0; main_index < num_puzzles; main_index ++){

			//create boards, queues and hashtable
			initial_board = init_board(inFile);
			LinkedList<String> q = new LinkedList<String>();
			HashMap<Integer,Integer> t = new HashMap<Integer, Integer>();
			
			q.offer(initial_board);
			t.put(initial_board.hashCode(), new Integer(0));

			solution = bfs(initial_board, q, t);		

			System.out.println(solution);
			
		}//end main for loop

	}//end main method

	public static int bfs(String initial_board, LinkedList<String> q, HashMap<Integer, Integer> t){
		String next = new String();
		int num_moves;
		int position;

		while(q.size() > 0){
				next = q.poll().toString();
				//System.out.println(next);
				num_moves = (int)t.get(next.hashCode());
				if(solved(next)){
					return num_moves;
				}else{
					//get position of open spot
					position = next.indexOf("0");
					//System.out.println(position);

					//try left
					if(position % 3 != 0){
						String new_board = new String(swap(next, position, position - 1));
						if(!t.containsKey(new_board.hashCode())){
							//System.out.println("new - unseen board"+ new_board);
							q.offer(new_board);
							t.put(new_board.hashCode(), new Integer(num_moves + 1));
						}else{
							//System.out.println("hash worked");
						}
					}
					//try up
					if(position - 3 > -1){
						String new_board = new String(swap(next, position, position - 3));
						if(!t.containsKey(new_board.hashCode())){
							//System.out.println("new - unseen board"+ new_board);
							q.offer(new_board);
							t.put(new_board.hashCode(), new Integer(num_moves + 1));
						}else{
							//System.out.println("hash worked");
						}
					}
					//try right
					if(position % 3 != 2){
						String new_board = new String(swap(next, position, position + 1));
						if(!t.containsKey(new_board.hashCode())){
							//System.out.println("new - unseen board"+ new_board);
							q.offer(new_board);
							t.put(new_board.hashCode(), new Integer(num_moves + 1));
						}else{
							//System.out.println("hash worked");
						}
					}
					//try down
					if(position + 3 < 9){
						String new_board = new String(swap(next, position, position + 3));
						if(!t.containsKey(new_board.hashCode())){
							//System.out.println("new - unseen board"+ new_board);
							q.offer(new_board);
							t.put(new_board.hashCode(), new Integer(num_moves + 1));
						}else{
							//System.out.println("hash worked");
						}
					}
				}
			}//end while queue

			return 99999;

	}//end BFS method

	public static String init_board(Scanner inFile){
		String s = "";
		int i;

		for(i = 0; i < 9; i ++){
			s += inFile.nextInt();
		}

		return s;

	}//end init_board method

	public static boolean solved(String s){
		if(s.equals("123456780")){
			return true;
		}else{
			return false;
		}
	}//end solved method

	public static char[] swap(String s, int p1, int p2){
		char temp;

		char[] c = s.toCharArray();

		temp = c[p1];
		c[p1] = c[p2];
		c[p2] = temp;

		return c;
	}

}//end prob8 class