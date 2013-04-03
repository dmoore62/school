/*Skyline.java
Name: Daniel Moore
COP 3503
Assignment 5
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
			System.out.println("New Skylines");
			for(int x = 0, y = input_skylines.size(); x < y; x++){
				System.out.printf("%d ", input_skylines.get(x));
			}
			System.out.println();
			main_index = inFile.nextInt();
		}//end main while

	}//end main method

	public ArrayList Skyline_split(ArrayList skyline){
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

	public ArrayList Skyline_merge(ArrayList sl1, ArrayList sl2){
		
	}

}//end Skyline class

