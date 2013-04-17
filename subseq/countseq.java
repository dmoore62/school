/*countseq.java
Daniel Moore
COP 3503 HW5 Part 2
4/15/2013*/

import java.util.*;

public class countseq{

	public static void main(String[] args){
		int loop;
		Scanner inFile = new Scanner(System.in);
		String word1;
		String word2;
		loop = inFile.nextInt();

		for(int loop_i = 0; loop_i < loop; loop_i++){

			word1 = new String();
			word2 = new String();

			word1 = inFile.next();
			word2 = inFile.next();

			System.out.println(solve(word2, word1));

		}//end loop for

	}//ens main method

	public static int solve(String t, String s){

		int[][] dp = new int[t.length()][s.length()];
		if(s.length() < t.length())
			return 0;

		for(int ti = 0; ti < t.length(); ti ++){
			for(int si = 0; si < s.length(); si ++){

				if(ti == 0){
					if(si == 0){
						if(t.charAt(ti) == s.charAt(si)){
							dp[ti][si] = 1;
						}else{
							dp[ti][si] = 0;
						}
					}else{
						dp[ti][si] = dp[ti][si - 1];
						if(t.charAt(ti) == s.charAt(si)){
							dp[ti][si] ++;
						}
					}
				}else{
					if(si < ti){
						dp[ti][si] = 0;
					}else{
						dp[ti][si] = dp[ti][si - 1];
						if(t.charAt(ti) == s.charAt(si)){
							dp[ti][si] += dp[ti - 1][si - 1];
						}
					}
				}

			}//end si for
		}//end ti for

		return dp[t.length() - 1][s.length() - 1];

	}//end solve method

}//end countseq class