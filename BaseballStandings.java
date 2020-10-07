/**
 *@author: Erin Johnson
 *@description: In this assignment, you will create an application that will read this tab-delimited file 
 *that expresses the current Major League Baseball standings
 *
 *references: 202001002 in-class notes
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class BaseballStandings{
	/**
	 * this first function will welcome the user to the program
	 */
	public static void welcomeUser() {
		System.out.println("********************************************");
		System.out.println("*	BASEBALL STANDINGS ANALYZER         *");
		System.out.println("********************************************");
	}
	/**
	 * the showMenuandGetChoice function will demonstrate the list of choices the user can pick, 
	 * in addition to correcting user input if they enter a character or number that is outside 
	 * of the choice range
	 */
	public static int showMenuAndGetChoice(Scanner sc) {
		int choice;
		do {
			System.out.println("Which standings would you like to see?");
			System.out.println("1. AL East");
			System.out.println("2. AL Central");
			System.out.println("3. AL West");
			System.out.println("4. NL East");
			System.out.println("5. NL Central");
			System.out.println("6. NL West");
			System.out.println("7. Overall");
			System.out.println("8. Exit");
			System.out.println("Enter the number of your choice: ");	
			try{
				choice=sc.nextInt();
				if(choice<= 0 ||choice> 8) {
					System.out.println("That is an invalid choice.");
					}
				}catch(Exception ex) {
					System.out.println("You needed to enter a number.");
					sc.nextLine();// clears the garbage on the input channel
					choice = 0;
					}
			}while(choice<= 0 ||choice> 8);
			return choice;
		
	}
	/**
	 * this function serves the purpose of computing the average through adding the wins and losses,
	 * then dividing wins by total games played
	 * this is similar to the function seen in the example program SemesterGrader
	 * and aids to keep my program...well, organized
	 */
	public static double calculateAvg(String line) {
		String[] parts = line.split("\t");
		double totalGames = (Integer.parseInt(parts[1])) + (Integer.parseInt(parts[2]));
		double avg = (Integer.parseInt(parts[1])) / totalGames;
		return avg;
	}
	/**
	 * once again referring to a similar function seen in the example program SemesterGrader,
	 * this function serves the purpose of ordering choices 1-6 and ordering the teams by their
	 * winning and losing percentages. it will also call my previous function, calculateAvg,
	 * to compute and order the respective averages.
	 */
	public static void printStatsOne(ArrayList<String>standings) {
		String[] parts;
		double avg;
		System.out.println("Team 		Wins  Losses Pct.   Behind");
		System.out.println("-----------------------------------------------------------");
		boolean more = false;//
		float bestTeamGamesBack = 0;//
		for(String standing : standings){
			
			parts = standing.split("\t");
			if (more == false) {//
			 bestTeamGamesBack = Float.parseFloat(parts[2])-  Float.parseFloat(parts[1]);//
			}//
			
			avg = calculateAvg(standing);
			float wins =  Float.parseFloat(parts[1]);//
			float loss = Float.parseFloat(parts[2]);//			
			if (more == false) {//
				 System.out.printf("%-15s%-8s%-8s%6.3f \n ", parts[0], parts[1], parts[2], avg);//
				 
			} else if (more == true){//
				
				float getGamesBack = GamesBack(wins, loss, bestTeamGamesBack) ;//
				System.out.printf("%-15s%-8s%-8s%6.3f %6.1f \n ", parts[0], parts[1], parts[2], avg, getGamesBack);//				 
			}
			more = true;//
	}
}
	public static float GamesBack(float wins, float loss, float bestTeamGamesBack) {//
		float gamesBack = (loss) - (wins);//		
		float getGamesBack;//
		getGamesBack = (gamesBack - bestTeamGamesBack)/2;//
		return getGamesBack;//
	}//
	/**
	 * choice 7 requires its own function as it will print stats of the whole file, rather than individual choices
	 * this function will take in teams and order them by pct
	 * it will print out the team name and the average. we'll have to call my previous function, Calculateavg, for this
	 */
	public static void printStatsTwo(ArrayList<String>standings) {
		String[] parts;
	 // double percentWin;
        System.out.println("Team		 Wins  Losses");
        System.out.println("-------------------------------------");
        for(String standing : standings){
            parts = standing.split("\t");
            
            System.out.printf("%-13s %5s %5s \n ",parts[0], parts[1],parts[2]);
       } 
	}
	/**
	 * now it is important to sort the list by the average
	 * once again i have to call my calculateAvg function
	 * this function is like the insertByAverage function in SemesterGrader
	 */
	public static void insertByAverage(ArrayList<String>teams,String line) {
		double thisAvg = calculateAvg(line);
		double otherAvg;
		int pos = -1;
	       for(int i =0; i < teams.size(); i++){
	           otherAvg = calculateAvg(teams.get(i)); //function call
	           if(thisAvg>otherAvg){
	               pos = i;
	               break;
	           }
	       }
	       if(pos < 0){
	           teams.add(line);
	       } else {
	           teams.add(pos, line);
	       }	
	}
	/**
	 * this function calculates the winning percents of all teams 
	 */
	 static float calcWin(String win, String lose) {
		 float percentWin = Float.parseFloat(win)/(Float.parseFloat(lose)+Float.parseFloat(win));
		 return percentWin;
	}
	
	/**
	 * this is the main body of code which prints the welcome user banner and takes in user input and performs necessary actions
	 * to fit the program's description
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    System.out.println("Enter the name of the file: ");
	    String fname = sc.nextLine(); //file name
	    //create array lists as done in SemesterGrader-- since we imported array lists in the beginning of the program
	    ArrayList<String>aleast = new ArrayList<String>();
	    ArrayList<String>alcentral = new ArrayList<String>();
	    ArrayList<String>alwest = new ArrayList<String>();
	    ArrayList<String>nleast = new ArrayList<String>();
	    ArrayList<String>nlcentral = new ArrayList<String>();
	    ArrayList<String>nlwest = new ArrayList<String>();
	    ArrayList<String>target = null;
	    ArrayList<String>overall = new ArrayList<String>();
	    String line, name;
	    String[] parts;
	    boolean canGoAhead;
	    int choice;
	    try{
	    	Scanner fsc = new Scanner(new File(fname));
	    	while(fsc.hasNextLine()) {
	    		line = fsc.nextLine();
	    		parts = line.split("\t");
	    		if(parts[0].equalsIgnoreCase("LEAGUE")) {
	    			name = parts[1].toUpperCase();
	    			if(name.equalsIgnoreCase("AL EAST")) {
	    				target = aleast;
	    			}else if(name.equalsIgnoreCase("AL CENTRAL")){
	    				target = alcentral;
	    			}else if(name.equalsIgnoreCase("AL WEST")){
	    				target = alwest;
	    			}else if(name.equalsIgnoreCase("NL EAST")){
	    				target = nleast;
	    			}else if(name.equalsIgnoreCase("NL CENTRAL")){
	    				target = nlcentral;
	    			}else if(name.equalsIgnoreCase("NL WEST")) {
	    				target = nlwest;
	    			}
	    			
	    		}else {
	    			target.add(line);
	    			insertByAverage(overall,line);
	    		}
	    	}
	    	welcomeUser();
    		canGoAhead = true;
	    }catch(Exception ex) {
	    	System.out.println("The file could not be read.");
	    	canGoAhead = false;
	    }
	    if(canGoAhead) {
	    	do{
	    		choice = showMenuAndGetChoice(sc);
	    		 if(choice == 1){
	                 printStatsOne(aleast);
	               } else if(choice == 2){
	                   printStatsOne(alcentral);
	               } else if(choice == 3){
	                   printStatsOne(alwest);
	               } else if(choice == 4){
	                   printStatsOne(nleast);
	               } else if(choice == 5){
	                   printStatsOne(nlcentral);
	               } else if(choice == 6){
	                   printStatsOne(nlcentral);
	               } else if(choice == 7){
	            	   printStatsTwo(overall);
	               }
	    }while (choice != 8);
	    	}
	
	}
}
