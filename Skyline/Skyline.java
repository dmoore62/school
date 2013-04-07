/*Skyline.java
Name: Daniel Moore
COP 3503
Assignment 4
4/1/2013*/

import java.io.*;
import java.util.*;

public class Skyline{

	public static void main(String[] args){

		Scanner inFile = new Scanner(System.in);
		int main_index;
		
		main_index = inFile.nextInt();

		while(main_index != 0){

			ArrayList<Integer> input_skylines = new ArrayList<Integer>();

			for(int i = 0; i < main_index; i ++){

				for(int j = 0; j < 3; j ++){
					input_skylines.add(inFile.nextInt());
				}

				input_skylines.add(0);

			}//end main for
			for(int x = 0, y = input_skylines.size(); x < y; x++){
				System.out.printf("%d ", input_skylines.get(x));
			}
			main_index = inFile.nextInt();
		}//end main while

	}//end main method

	public ArrayList<Integer> Skyline_split(ArrayList<Integer> skyline){
		int size = skyline.size();

		if(size == 4){
			return skyline;
		}

		ArrayList<Integer> skyline_one = new ArrayList<Integer>();
		ArrayList<Integer> skyline_two = new ArrayList<Integer>();
		ArrayList<Integer> new_skyline = new ArrayList<Integer>();

		int split = (size / 2) + ((size / 2) % 4) - 1;
		for(int i = 0; i < split; i ++){
			skyline_one.add(skyline.get(i));
		}
		for(int j = split; j < skyline.size(); j ++){
			skyline_two.add(skyline.get(j));
		}

		skyline_one = Skyline_split(skyline_one);
		skyline_two = Skyline_split(skyline_two);
		new_skyline = Skyline_merge(skyline_one, skyline_two);

		return new_skyline;
	}

	public ArrayList<Integer> Skyline_merge(ArrayList<Integer> sl1, ArrayList<Integer> sl2){
		
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
					//one or more exists
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
		if(i_sl1 > sl1.size()){
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

