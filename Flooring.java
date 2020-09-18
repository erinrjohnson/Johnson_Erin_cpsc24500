/**
 *@author: Erin Johnson
 *@description: Suppose you want to cover an area of your house with laminate flooring. Each board is 30 inches long 
 *and 6 inches wide. The boards come packaged in boxes of six. Each package costs $24.99. The dimensions of your room are shown 
 *in this diagram. Because you will have to cut the boards to accommodate the angle, there will be some waste, so
 *you will have to purchase 20% extra flooring to account for that. Also, you can't buy individual boards, as you can purchase 
 *them only in boxes of six. Write a program called Flooring.java that will determine and print how many pack ages of laminate
 *boards you will need to buy and what that will cost. Use at least one function other than main.
 */

/**
 *Flooring is the public class in which it makes it visible to the whole program.
 */
public class Flooring {
	public static void welcomeUser() {
		System.out.println("********************");
		System.out.println("  Flooring Program  ");
		System.out.println("********************\n");
	}
//must break up the shape of the room into separate calculations
//shape breaks up into a combination of rectangles and a triangle, thus requiring need to find area of both
//the operations to find areas of both can be put into functions
//***similar to Sum and Product in-class program***, substitute sum and product for area operations
	/**This computeRecOne() function takes in a set of variables and goes forth to effectively calculate the area.
	 * Area is accomplished by finding length * width.
	 * It then returns the value for future storage into another variable in the main body.
	 */
	public static int computeRecOne(int reconelength, int reconewidth) {
		//Area of a rectangle is width *  length
		int reconearea = reconelength * reconewidth;
		return reconearea;
	}
	/**This computeRecTwo() function takes in a set of variables and goes forth to effectively calculate the area.
	 * Area is accomplished by finding length * width.
	 * It then returns the value for future storage into another variable in the main body.
	 */
	public static int computeRecTwo(int rectwolength, int rectwowidth) {
		//Once again, area of a rectangle is width * length
		int rectwoarea = rectwolength * rectwowidth;
		return rectwoarea;
	}
	/**This computeTri() function takes in a set of variables and goes forth to effectively calculate the area.
	 * Area is accomplished by finding base * height/2.
	 * It then returns the value for future storage into another variable in the main body.
	 */
	public static int computeTri(int triheight, int tribase) {
		//Area of a triangle is b*h/2
		int triarea = (tribase * triheight)/2;
		return triarea;
	}
	/**This is the main body of my program. It encompasses all of my main arguments.
	 * These arguments hold the math necessary for finding the area of the room, number of boards needed, excess boards,
	 * and total package cost.
	 * Then the result is printed.
	 */
	public static void main(String[] args) {
		welcomeUser();
		//input data from room diagram after completing necessary math
		//allows passing of data to functions to perform their operations 
		int reconelength = 10;
		int reconewidth = 12;
		int rectwolength = 13;
		int rectwowidth = 20;
		int triheight = 12;
		int tribase = 10;
		//the total area of the room must be calculated, this can be done by adding all functions together
		//as a note, int can be removed as they have already been int-ified
		int roomarea = (computeRecOne(reconelength, reconewidth) + computeRecTwo(rectwolength, rectwowidth) + computeTri(triheight, tribase));
		int boardarea = 30 * 6;
		int numboard = 0, numpackage = 0;
		double totalpackagecost = 0.0;
		//must account for extra 20%
		roomarea = (int) (roomarea + (roomarea * 0.20));
		numboard = (int)Math.ceil(roomarea/boardarea);
		numpackage = numboard / 6;
		totalpackagecost = costofpackages(numpackage);
		System.out.println("You need to purchase " + numpackage + " boxes of flooring.");
		System.out.println("The total cost of flooring your room will be " + totalpackagecost + " dollars.");
	}
private static double costofpackages(int numpackage){
	double singlepackcost = 24.99;
	return numpackage * singlepackcost;
}
}
