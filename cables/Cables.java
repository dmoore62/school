/*	Daniel Moore - Cables.java
	COP 3503 - Program #2
*/
import java.util.*;


//Cables class 
public class Cables {

	public static void main(String args[]){

		//create scanner
		Scanner inFile = new Scanner(System.in);

		int main_index;

		main_index = inFile.nextInt();

		while(main_index != 0){

			//create ArraysLists
			ArrayList<Point> all_points = new ArrayList<Point>();
			ArrayList<Wire> all_wires = new ArrayList<Wire>();

			//we will need all of these
			int i, j, next_x, next_y;
			Point next_origin, next_end;

			//colect all points
			for(i = 0; i < main_index; i ++){
				next_x = inFile.nextInt();
				next_y = inFile.nextInt();
				all_points.add(new Point(next_x, next_y, i));
			}//end i for

			//create all wires
			for(i = 0; i < main_index; i ++){
				next_origin = all_points.get(i);

				for(j = i + 1; j < main_index; j ++){
					next_end = all_points.get(j);
					all_wires.add(new Wire(next_origin, next_end));
				}

			}

			//sort wires
			Collections.sort(all_wires, new WireLengthComparator());

			//create appropriate DisjointSet
			DisjointSet parents = new DisjointSet(all_wires.size());

			//Keep track of sum and wire length
			int linked_wire = 0;
			double sum = 0;

			//base case only one wire
			if(all_wires.size() == 1){

				sum = all_wires.get(0).length;

			}else{

				//start connecting points
				for(i = 0; i < all_wires.size(); i ++){

					//base case, all points are connected
					if(linked_wire == main_index - 1){
						break;
					}

					//check if points are connected
					if(parents.find(all_wires.get(i).end) != parents.find(all_wires.get(i).origin)){
						//connect wires and add distance and increment counter
						parents.union(all_wires.get(i).origin, all_wires.get(i).end);
						linked_wire ++;
						sum += all_wires.get(i).length;
					}
				}
			}

			//Output single line and get the next main_index
			System.out.printf("%.2f\n", sum);

			main_index = inFile.nextInt();

		}//end main loop

	}//end main method


}//end Cables class

//Point Class 
//Every point has an x,y value
//and carries the index it was created at 
class Point {
	public int x;
	public int y;
	public int point_index;

	public Point(int x, int y, int point_index) {
		this.x = x;
		this.y = y;
		this.point_index = point_index;
	}//end Point constructor

}//end Point class


//Wire Class
//Constructor creates origin and end using point indexes
//and calculates distance based on point positions
class Wire {
	public int origin;
	public int end;
	public double length;

	public Wire(Point p1, Point p2){
		this.origin = p1.point_index;
		this.end = p2.point_index;
		this.length = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
}

//Custom Comparator Class
//Compares wire lengths and returns
//-1 for less, 0 for same, 1 for more
//used in object sort
class WireLengthComparator implements Comparator<Wire> {

	@Override
	public int compare(Wire wire1, Wire wire2) {
		return Double.compare(wire1.length, wire2.length);
	}
}

//DisjointSet Class
//Constructor creates array where index is the same as value
//find method finds the Disjoint Set the element belongs to
//union method joins end set with origin set
class DisjointSet {

	public int[] set;

	public DisjointSet(int size){
		set = new int [size];


		for(int s = 0; s < set.length; s ++){
			set[s] = s;
		}
	}

	public int find(int id) {
		while(id != set[id]){
			id = set[id];
		}
		return id;
	}

	public void union(int origin, int end){
		set[find(end)] = find(origin);
	}

}