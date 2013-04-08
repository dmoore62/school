/*Skyline.java
Name: Daniel Moore
COP 3503
Assignment 4 - final submitted version
4/8/2013*/

import java.io.*;
import java.util.*;

public class Skyline{

	public static void main(String[] args){

		Scanner inFile = new Scanner(System.in);
		int main_index;
		
		main_index = inFile.nextInt();

		while(main_index != 0){

			//create a list to scan input skylines into
			//and one to hold final list
			ArrayList<Integer> input_skylines = new ArrayList<Integer>();
			ArrayList<Integer> final_skyline = new ArrayList<Integer>();

			for(int i = 0; i < main_index; i ++){

				for(int j = 0; j < 3; j ++){
					input_skylines.add(inFile.nextInt());
				}

				input_skylines.add(0);

			}//end main for

			//get rid of invisible buildings
			for(int k = 0; k < input_skylines.size(); k += 4){
				if(input_skylines.get(k).intValue() == input_skylines.get(k + 2).intValue()){
					//System.out.printf("found it! %d\n", k);
					for(int m = 0; m < 4; m ++){
						input_skylines.remove(k);
					}
					k-=4;
				}
			}
			
			//recursive merge solution
			final_skyline = Skyline_split(input_skylines);
			
			//print out final skyline
			for(int x = 0; x < final_skyline.size(); x++){
				System.out.printf("%d ", final_skyline.get(x));
			}

			System.out.printf("\n");

			//see if we go again!
			main_index = inFile.nextInt();
		}//end main while

	}//end main method

	/* Splits input in half based on a mod 4 rule
	// the lower half gets the leftover if a clean 50/50 split is not possible
	*/
	public static ArrayList<Integer> Skyline_split(ArrayList<Integer> skyline){
		int size = skyline.size();

		//base case - the skyline of a single building
		if(size == 4){
				return skyline;
		}

		//create ArrayList to hold things
		ArrayList<Integer> skyline_one = new ArrayList<Integer>();
		ArrayList<Integer> skyline_two = new ArrayList<Integer>();
		ArrayList<Integer> new_skyline = new ArrayList<Integer>();

		//split the arrays
		int split = (size / 2) + ((size / 2) % 4);
		
		for(int i = 0; i < split; i ++){
			skyline_one.add(skyline.get(i));
		}
		for(int j = split; j < skyline.size(); j ++){
			skyline_two.add(skyline.get(j));
		}

		//Looks like Merge-Sort
		skyline_one = Skyline_split(skyline_one);
		skyline_two = Skyline_split(skyline_two);
		
		new_skyline = Skyline_merge(skyline_one, skyline_two);
		
		return new_skyline;
	}

	/* Merges two skylines into one
	// Hides all smaller buildings - Returns new Skyline
	*/ 
	public static ArrayList<Integer> Skyline_merge(ArrayList<Integer> sl1, ArrayList<Integer> sl2){
		
		ArrayList<Integer> new_skyline = new ArrayList<Integer>();
		int i_sl1 = 0;
		int i_sl2 = 0;


		while(i_sl1 < sl1.size() && i_sl2 < sl2.size()){

			if(sl1.get(i_sl1).intValue() == sl2.get(i_sl2).intValue()){
				//the index values are the same
				if(i_sl1 - 1 < 0 && i_sl2 - 1 < 0){
					//no lookback exists
					//take the taller building increment both
					if(sl1.get(i_sl1 + 1).intValue() > sl2.get(i_sl2 + 1).intValue()){
						//sl1 is higher
						new_skyline.add(sl1.get(i_sl1));
						new_skyline.add(sl1.get(i_sl1 + 1));
					}else{
						//sl2 is higher, or same
						new_skyline.add(sl2.get(i_sl2));
						new_skyline.add(sl2.get(i_sl2 + 1));
					}
					i_sl1 += 2;
					i_sl2 += 2;
				}else{
					//one or more lookbacks exist
					//take the taller building increment both
					if(i_sl1 - 1 > 0 && sl1.get(i_sl1 - 1).intValue() == sl2.get(i_sl2 + 1).intValue()){
						i_sl1 += 2;
						i_sl2 += 2;
					}else if(i_sl2 - 1 > 0 && sl2.get(i_sl2 - 1).intValue() == sl1.get(i_sl1 + 1).intValue()){
						i_sl1 += 2;
						i_sl2 += 2;
					}else{
						if(sl1.get(i_sl1 + 1).intValue() > sl2.get(i_sl2 + 1).intValue()){
							//sl1 is higher
							new_skyline.add(sl1.get(i_sl1));
							new_skyline.add(sl1.get(i_sl1 + 1));
						}else{
							//sl2 is higher, or same
							new_skyline.add(sl2.get(i_sl2));
							new_skyline.add(sl2.get(i_sl2 + 1));
						}
						i_sl1 += 2;
						i_sl2 += 2;
					}
				}	

			}else if(sl1.get(i_sl1).intValue() < sl2.get(i_sl2).intValue()){
				//the top value should go in
				if(i_sl2 - 1 < 0){
					//there is no lookback value
					//sl1 goes in
					new_skyline.add(sl1.get(i_sl1));
					new_skyline.add(sl1.get(i_sl1 + 1));
					i_sl1 += 2;
				}else{
					//there is a lookback value
					if(sl1.get(i_sl1 + 1).intValue() > sl2.get(i_sl2 - 1).intValue()){
						//the building is higher
						//sl1 goes in
						new_skyline.add(sl1.get(i_sl1));
						new_skyline.add(sl1.get(i_sl1 + 1));
						i_sl1 += 2;
					}else{
						//the building may be hidden
						if(i_sl1 - 1 < 0){
							//no lookback on sl1
							//hidden, skip it
							i_sl1 += 2;
						}else{
							//find if build is going down
							if(sl1.get(i_sl1 - 1).intValue() > sl1.get(i_sl1 + 1).intValue() && sl1.get(i_sl1 - 1).intValue() > sl2.get(i_sl2 - 1).intValue()){
								//building is dropping off
								new_skyline.add(sl1.get(i_sl1));
								new_skyline.add(sl2.get(i_sl2 - 1));
								i_sl1 += 2;
							}else{
								//hidden, skip it
								i_sl1 += 2;
							}
						}

					}

				}
			}else if(sl1.get(i_sl1).intValue() > sl2.get(i_sl2).intValue()){
				//the bottom value should go in
				if(i_sl1 - 1 < 0){
					//there is no lookback value
					//sl2 goes in
					new_skyline.add(sl2.get(i_sl2));
					new_skyline.add(sl2.get(i_sl2 + 1));
					i_sl2 += 2;
				}else{
					//there is a lookback value
					if(sl2.get(i_sl2 + 1).intValue() > sl1.get(i_sl1 - 1).intValue()){
						//the building is higher
						//sl2 goes in
						new_skyline.add(sl2.get(i_sl2));
						new_skyline.add(sl2.get(i_sl2 + 1));
						i_sl2 += 2;
					}else{
						//the building may be hidden
						if(i_sl2 - 1 < 0){
							//no lookback on sl2
							//hidden, skip it
							i_sl2 += 2;
						}else{
							//find if build is going down
							if(sl2.get(i_sl2 - 1).intValue() > sl2.get(i_sl2 + 1).intValue() && sl2.get(i_sl2 - 1).intValue() > sl1.get(i_sl1 - 1).intValue()){
								//building is dropping off
								new_skyline.add(sl2.get(i_sl2));
								new_skyline.add(sl1.get(i_sl1 - 1));
								i_sl2 += 2;
							}else{
								//hidden, skip it
								i_sl2 += 2;
							}
						}

					}

				}
			}
			
		}
		//fill in whatever is left
		if(i_sl1 == sl1.size()){
			while(i_sl2 < sl2.size()){
				new_skyline.add(sl2.get(i_sl2));
				i_sl2 ++;
			}
		}else{
			while(i_sl1 < sl1.size()){
				new_skyline.add(sl1.get(i_sl1));
				i_sl1 ++;
			}
		}

	return new_skyline;
	}

}//end Skyline class

