/*
 * Daniel Moore
 * Eric Sorokowsky
 * Lab #5 - SpreadingNews
 */
 
 import java.io.*;
 import java.util.*;
 
public class SpreadingNews 
{   
    public static void main(String[] args)
    {
        //Create a scanner object to read in from the console.
        Scanner input = new Scanner(System.in);
        
        //Get the first number which corresponds to the number of test cases.
        int numInputs = input.nextInt();
        //Loop for each test case.
        for(int i = 0; i < numInputs; i++)
        {
            //Get the number of employees in this test case.
            int numEmployees = input.nextInt();
            
            //Create an array to store all the employees
            int[] employees = new int[numEmployees];
            //Add the main supervisor to the list.
            employees[0] = -1;
            
            //Loop for each employee and add them to the list of employees.
            for(int j = 1; j < numEmployees; j++)
            {
                employees[j] = input.nextInt();
            }
            
            //Calculate the minimum call time and print it to the screen.
            System.out.println(GetOptimumCallTime(employees, 0));
        }
    }
    
    //This function will return the minimum optimum call time of a corporate tree.
    private static int GetOptimumCallTime(int[] i_nEmployees, int i_nCurLoc)
    {
        //Array list to hold the call time of subordinates of the supervisor at the current location.
        ArrayList<Integer> curSubordinates = new ArrayList<Integer>();
        
        //Loop through the employee list starting from the current location and
        //find all of the current employees direct subordinates.
        for (int i = i_nCurLoc + 1; i < i_nEmployees.length; i++) 
        {
            //If this employee is a direct subordinate get his optimzed call time and add it to the list of subordinate call times.
            if (i_nEmployees[i] == i_nCurLoc)
            {
                curSubordinates.add(GetOptimumCallTime(i_nEmployees, i));
            }
        }

        //Sort the call times from least to greatest.
        Collections.sort(curSubordinates);
        //Invert the list so that we can proccess them from greatest to least
        Collections.reverse(curSubordinates);

        //Loop throught the list of call times and find the maximum call time to be returned.
        int curMax = 0;
        for (int i = 0; i < curSubordinates.size(); i++) 
        {
            //The current call time is equal to the number corresponding to which child we are 
            //plus the call time of the current nodes subordinates plus 1 for the current node.
            int curCallTime = curSubordinates.get(i)+i+1;
            if(curCallTime > curMax)
                curMax = curCallTime;
        }

        //return the max call time for this node.
        return curMax;
    }
}
