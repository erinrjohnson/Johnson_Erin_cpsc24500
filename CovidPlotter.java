/**
 *@author: Erin Johnson
 *@description: In this assignment, you will plot daily and cumulative CoVID-19 death totals for countries 
 *specified by the user. The data will come from a tab-delimited file called countries_deaths.txt
 *that have placed in your assignments folder. It specifies total deaths over time.
 *The program will begin by printing a header and then asking you for the name of the file. 
 *The program will then repeatedly ask the user to enter the names of one or more countries
 *separated by commas, and whether the user wants to plot cumulative or daily death totals. 
 *The program will then plot those totals for the countries specified by the user
 *
 *references: investment program
 */
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;
import java.awt.Container;
import java.awt.BorderLayout;

/**
 * This function serves the purpose of printing a banner that welcomes the user to the program.
 */
public class CovidPlotter {
	public static void welcomeUser() {
		System.out.println("*******************************************************");
		System.out.println("*	INTERNATIONAL COVID-19 MORTALITY RATES        *");
		System.out.println("*******************************************************");
	}
	/**
	 * This function creates a LinkedHashMap that reads the text file and returns respective results of each part.
	 */
	public static LinkedHashMap<String,double[]> readData(Scanner fsc){
		LinkedHashMap<String,double[]> result = new LinkedHashMap<String,double[]>();
		fsc.hasNextLine(); // gets rid of the first line
		String country; // name of country
		String line; // line that we read
		String [] parts;
		double [] values; // the number of deaths per day
		while (fsc.hasNextLine()) {
			line = fsc.nextLine();
			parts = line.split("\t");
			country = parts[0];
			values = new double[parts.length-1];
			// fill the values with the death totals
			for (int i = 1; i < parts.length; i++) {
				values[i-1] = Double.parseDouble(parts[i]);
				
			}
			// now I have the country name and the death totals
			// put them in the result map
			result.put(country,values);
		}
		// all the country's information will now be returned 
		return result;
	} 
	/**
	 * used to generate cumulative numbers for the x axis of plot
	 */
	 public static double [] getNumbers(int howMany) {
		 double[] result = new double[howMany];
		 for (int i = 0; i < howMany; i++) {
			 result[i] = i;
		 }
		 return result;
	 }
	 /**
	  * this function shows the cumulative deaths graph
	  */
	 public static void setUpAndShowPlot(Plot2DPanel plot) {
		 JFrame frm = new JFrame();
		 frm.setBounds(100,100,500,500);
		 frm.setTitle("Cumulative Deaths");
		 frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // get rid from memory
		 Container c = frm.getContentPane();
		 c.setLayout(new BorderLayout());
		 plot.addLegend("SOUTH");
		 plot.setAxisLabels("Day", "Deaths");
		 c.add(plot, BorderLayout.CENTER);
		 frm.setVisible(true);
	 }
	 /*
	  * this function shows the daily deaths graph 
	  */
	 public static void setUpandShowDaily(Plot2DPanel plot) {
		 JFrame frm = new JFrame();
		 frm.setBounds(100,100,500,500);
		 frm.setTitle("Daily Deaths");
		 frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // get rid from memory
		 Container c = frm.getContentPane();
		 c.setLayout(new BorderLayout());
		 plot.addLegend("SOUTH");
		 plot.setAxisLabels("Day", "Deaths");
		 c.add(plot, BorderLayout.CENTER);
		 frm.setVisible(true);
		 //need to do current day - previous day
//i really tried 
	 }
	 /**
	  * @param args
	  * @throws Exception
	  * this is the main body of code which prints the welcome user banner and takes in user input and performs necessary actions
	  * to fit the program's description
	  */
	 public static void main(String[] args) throws Exception{
		 welcomeUser();
		 LinkedHashMap<String,double[]>countries;
		 String inputtedCountries;
		 String[] names;
		 Scanner sc = new Scanner(System.in);
		 double [] countryValues;
		 System.out.println("Enter the name of the file: ");
		 String fname = sc.nextLine(); // file name
		 try {
			 Scanner fsc = new Scanner(new File(fname));
			 countries = readData(fsc);
		 } catch (Exception ex) {
			 countries = null; // I wasn't able to read the data
		 }
		 if (countries == null) {
			 System.out.println("Couldn't read the file. ");
		 } else {
			 // repeatedly ask the user for the names of the countries for whom they
			 // want to see the death total
			 do {
				 System.out.print("Enter the names of countries separated by commas: \n");
				 inputtedCountries = sc.nextLine();
				 System.out.println("[D]aily or [C]umulative?: ");
				 String choice =sc.nextLine();
				 if (!inputtedCountries.equalsIgnoreCase("exit")) {
					 Plot2DPanel plot = new Plot2DPanel();
					 // plot is where the data will eventually be shown
					// try to plot the data 
					 names = inputtedCountries.split(",");
					 for (String name : names) {
						 name = name.trim(); // remove any leading space
						 if (!countries.containsKey(name)) {
							 System.out.printf("%s is not in the data.\n", name);
						 }else {
							 countryValues = countries.get(name); // this country's death values 
							 plot.addLinePlot(name, getNumbers(countryValues.length), countryValues);
						 }
					 }
					 // configure and show the frame that houses the plot
					 setUpAndShowPlot(plot);
				 }
			 } while (!inputtedCountries.equalsIgnoreCase("exit"));
			 System.out.println("Your only task...is to wear a mask. ");
			 
		 }
		 
	 }
}

