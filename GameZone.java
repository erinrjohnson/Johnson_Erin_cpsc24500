/**
 * @author: Erin Johnson
 *@description: In this game, you will implement a program called GameZone that will offer the user the option of playing 
 *two games repeatedly, Twenty-One and Rock Paper Scissors. You will implement a simplified Twenty-One. Assume an infinite 
 *deck so that you don't have to keep track of the number of cards of each number in a standard deck. Assume you can draw
 *cards worth 1 through 11. Assume the dealer can draw a total that lies in the range 13 through 20. The player wins automatically
 *if they hit 21, and they lose automatically if they exceed 21. Otherwise, whether they win or lose depends on how their total
 *compares with the dealer's when they decide to quit. Your Rock Paper Scissors implementation will play according to the standard 
 *rules: paper covers rock, rock crushes scissors, and scissors cuts paper. The user will choose which game they want to play from 
 *a menu. They can choose to quit whenever they'd like by choosing option 3.
 */

//first we must import Scanner in order to receive input from the user
import java.util.Scanner;
//we must also import Random in order to assume a random int
import java.util.Random;


public class GameZone {
	static int choice;
	public static Scanner scan; 
	/**
	 * the showMenuandGetChoice function will correct user input if they enter a character or number that is outside of 
	 * the choice range
	 */
	public static int showMenuAndGetChoice(Scanner sc) {
		int choice;
		do {
			System.out.println("What would you like to play? ");
			System.out.println("1. Twenty-One ");
			System.out.println("2. Rock Paper Scissors ");
			System.out.println("3. Neither- I'm done! ");
			System.out.println("Please enter the number of your choice: ");
				try{
					choice=sc.nextInt();
					if(choice<= 0 ||choice> 3) {
						System.out.println("That is an invalid choice.");
						}
					}catch(Exception ex) {
						System.out.println("You needed to enter a number.");
						sc.nextLine();// clears the garbage on the input channel
						choice = 0;
						}
				}while(choice<= 0 ||choice> 3);
				return choice;
			}
		
	/**
	 * this first function will welcome the user to the program
	 */
	public static void welcomeUser() {
		System.out.println("*****************************************");
		System.out.println("*	 WELCOME TO THE GAME ZONE       *");
		System.out.println("*****************************************");
	}
	/**
	 * this is the main body of code which prints the welcome user banner and takes in user input and performs necessary actions
	 * to fit the program's description
	 */
	public static void main(String[] args){
		
		welcomeUser();
		Random num = new Random();
		Scanner sc = new Scanner(System.in);
		int choice; //the user's selection
		
		//print out menu of options
		 do{
			 choice = showMenuAndGetChoice(sc);
			 //this next chunk of code will manage choice 1- which is the Twenty-One Game
			 if (choice == 1) {
			 int player = 0;
			 //loop until user wants more cards
			 char y;
			 do{
				 int playerDraw = num.nextInt(11)+1;
				 System.out.println("You drew " + playerDraw + ".");
				 //add what the player drew to the score of the player
				 player = player + playerDraw;
				 System.out.println("Your current total is "+ player +".");
				 //if the player reaches 21
				 if(player == 21){
					 System.out.println("You won!");
					 break;
					 }
				 //if player got more than 21
				 if(player > 21){
					 System.out.println("You lost.");
					 break;
					 }
				 //provide the player the option to draw another card or not
				 System.out.print("Do you want to draw another card? (y/n) ");
				 y = sc.next().charAt(0);
			   }while(y == 'y' ||y == 'Y');
			 
			 	int compPlayer = num.nextInt(8)+13;
			 	System.out.println("The computer drew " + compPlayer + ".");
			 	//if player has a higher score than the computer
			 	if(compPlayer <= player){
			 		System.out.println("You won!");
			 		}
			 	//if computer has a higher score than player
			 	else{
			 		System.out.println("You lost.");
			 		}
			 }
			 	//this next section of code deals with choice 2- which is the Rock, Paper, Scissors Game
			 	//FOR USER KNOWLEDGE 1:Paper, 2:Rock, 3:Scissors
			 	if (choice == 2) {
			 	int user = num.nextInt(3);
			 	int comp = num.nextInt(3);
			 	if (user == comp) { 
					if (user == 1) {
						System.out.println("You and the computer both played rock. ");
					} else if (user == 2) {
						System.out.println("You and the computer both played paper. ");
					} else {
						System.out.println("You and the computer both played scissors. ");
					}
					System.out.print("Its a tie! \n");
					
				} else if (user == 1) { 
					if (comp == 2) {
						System.out.println("You lose. The computer covered your rock with paper.\n");
					} else {
						System.out.println("You win. You smashed the computer's scissors with your rock.\n");
					}	
				} else if (user== 2) { 
					if (comp == 3) {
						System.out.println("You lose. The computer cut your paper with its scissors.\n");
					} else {
						System.out.println("You win. You covered the computer's rock with your paper.\n");
					}
				
				} else if (user == 3) { 
					if (comp == 1) {
						System.out.println("You lose. The computer smashed your scissors with its rock. \n");
					} else {
						System.out.println("You win. You cut the computer's paper with your scissors.\n");
					}
				}
			 	//test user input
				} else if  (choice == 3) {
					System.out.println("Thank you for playing! ");
					sc.close();
					break;		
				
		 		} 
			} while (choice != 3);	 
	
		 }
	} 
