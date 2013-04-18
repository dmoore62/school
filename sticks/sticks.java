/*Sticks.java
Daniel Moore
COP 3503 HW5 Part 1
4/15/2013*/

import java.util.*;

public class sticks{

	public static void main(String[] args){
		 int loop;
		 int length;
		 int size;
		 int[] ary;
		 int[][] memo;
		 Scanner inFile = new Scanner(System.in);

		 loop = inFile.nextInt();

		 for(int loop_i = 0; loop_i < loop; loop_i++){

		 	length = inFile.nextInt();
		 	size = inFile.nextInt();

		 	ary = new int[size + 2];
		 	memo = new int[size + 2][size + 2];

		 	ary[0] = 0;
		 	for(int i = 1; i < size + 1; i ++){
		 		ary[i] = inFile.nextInt();
		 	}
		 	ary[size + 1] = length;

		 	for(int i = 0; i < size + 2; i ++){
		 		for(int j = 0; j < size + 2; j ++){
		 			memo[i][j] = -1;
		 		}
		 	}

		 	System.out.println(minCut(ary, memo, 0, size+1));

		 }//end main for
	}//end main method

	public static int minCut(int[] ary, int[][] memo, int i, int j){
		if(memo[i][j] != -1){
			return memo[i][j];
		}
		else if(j == (i + 1)){
			memo[i][j] = 0;
			return 0;
		}
		else{
			int score;
			int best = 2147483647;
			for(int k = i + 1; k < j; k++){
				score = minCut(ary, memo, i, k) + minCut(ary, memo, k, j) + (ary[j] - ary[i]);
				if(score < best){
					best = score;
				}
			}
			memo[i][j] = best;
			return best;
		}

	}//end minCut method

}//end sticks class