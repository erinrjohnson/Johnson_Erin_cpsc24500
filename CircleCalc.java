/**
 *@author: Erin Johnson
 *@description: Write a program called CircleCalc.java that calculates and prints the area and
 *circumference of a circle or random radius. The program should randomly generate the
 *radius and then call functions to compute the circles' area and circumference. It should
 *print the area and circumference to the screen
 */

//import Random so as to generate a random radius
import java.util.Random;

/**
 *@description:CircleCalc is a public class in which it makes it visible to the whole program.
 */
public class CircleCalc {
/**
 *@description: This welcomeUser() function welcomes the user to my program. 
 *It does so by printing out a banner of stars and the words
 *"Circle Calculator," which effectively describes what this program accomplishes. 
 */
	public static void welcomeUser() {
		System.out.println("********************");
		System.out.println(" Circle Calculator");
		System.out.println("********************\n");
	}
/**
*@description: This is the main body of my program.
*It encompasses all of my main arguments.
* These arguments hold the math necessary for finding the area and circumference.
* Then the result is printed in much the same way as my welcome function.
*/
	public static void main(String[]args) {
		welcomeUser();
		Random num = new Random();
		int radius;
		radius = num.nextInt(25);
		//The first operation I will perform is the calculation of the area
		//Area = PI*radius*radius
		//Using double in the case of decimals
		double area = Math.PI * (radius * radius);
		System.out.printf("The area of the circle is: %.2f.\n", area);
		//The second operation I will perform is the calculation of the circumference
		//Circumference = 2*PI*radius
		//Once again using double in the case of decimals
		double circumference = Math.PI * 2 * radius;
		System.out.printf("The circumference of the circle is: %.2f.\n", circumference);
		
	}

}
