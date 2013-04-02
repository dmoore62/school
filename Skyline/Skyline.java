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
		int l;
		int h;
		int  r;

		main_index = inFile.nextInt();

		while(main_index != 0){

			ArrayList<building> buildings = new ArrayList<building>();

			for(int i = 0; i < main_index; i ++){

				buildings.add(new building(inFile.nextInt(), inFile.nextInt(), inFile.nextInt()));

				System.out.printf("%d %d %d\n", buildings.get(i).l, buildings.get(i).h, buildings.get(i).r);
			}//end main for


			main_index = inFile.nextInt();
		}//end main while

	}//end main method

}//end Skyline class

class building{
	int l;
	int h;
	int r;

	public building(int l, int h, int r){
		this.l = l;
		this.h = h;
		this.r = r;
	}//end building constructor
}