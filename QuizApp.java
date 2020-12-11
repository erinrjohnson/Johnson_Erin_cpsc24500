/**
 * @author: Erin Johnson
 * @description: In this assignment, you will create a quiz tool that will ask the user random questions 
 * about the object-oriented theory and concepts. The questions will come from a file called questions.json
 * that you will add to your project. A json file without the answers specified can be downloaded here.
 * Specify the correct answers to each of these questions, and then save the resulting file as
 * questions.json in your project, right outside the src file folder.
 * 
 * Reference assignment: LewisWordPress project done in class
 */

import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The Question class is the model class that contains the text, choices, and correct answer for each question. 
 * Question, the model class, stores the question, four possible answers, and correct answer.
 * It has get and set functions, constructors, and functions that return strings that can be used conveniently
 * in other parts of the program. 
 */
class Question {
	//variables
	private String question;
	private String answer;
	private String a;
	private String b;
	private String c;
	private String d;
	//get and set functions
	//for Question
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	//for Answer
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	//for A
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	//for B
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	//for C
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	//for D
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	
	//default constructor 
	public Question() {
		question = "none";
		a = "";
		b = "";
		c = "";
		d = "";
		answer = "none";
	}
	//corresponds to constructor- need inputs for each variable
	public Question(String question, String a, String b, String c, String d, String answer) {
		setQuestion(question);
		setA(a);
		setB(b);
		setC(c);
		setD(d);
		setAnswer(answer);
	}
	
	@Override
	public String toString(){
        return String.format("%s\n%s\n%s\n%s\n%s",question, a,b,c,d);
    }
}

/**
 * The QuestionPrinter class is a controller class that has a function that prints the questions and their correct
 * answers to the screen. 
 * It prints the answers and question text for all questions as shown in the sample output.
 */
class QuestionPrinter {
    public void printAll(ArrayList<Question> questions) {
        System.out.println("Here are the answers: ");
        for(Question question:questions){
            System.out.print(question.getAnswer() + " ");
            System.out.println(question.getQuestion());
        }
        System.out.println("");
    }
}

/**
 * The QuestionReader class is a controller class that has a function that reads a json file of questions and 
 * returns an ArrayList of them.
 */
class QuestionReader {
	public ArrayList<Question> readFromJSON(String fname) {
		try { 
			ArrayList<Question> questions = new ArrayList<Question>();
			FileReader reader = new FileReader(new File(fname));
			JSONParser parser = new JSONParser();
			JSONObject all = (JSONObject)parser.parse(reader);
			JSONArray arr = (JSONArray) all.get("questions");
			Iterator itr = arr.iterator(); 
			JSONObject questionObject; 
			String question, a, b, c, d, answer;
			while (itr.hasNext()) {
				questionObject = (JSONObject)itr.next();
				question = questionObject.get("question").toString();			
				a = questionObject.get("a").toString();			
				b = questionObject.get("b").toString();
				c = questionObject.get("c").toString();
				d = questionObject.get("d").toString();
				answer = questionObject.get("answer").toString();
				questions.add(new Question(question, a, b, c, d, answer));
			}
			//have to close the file
			reader.close(); 
			return questions; 
		} catch (Exception ex) {
			//in the case that the file is not valid
			return null;
		}
		
	}
}

/**
 * The Quizzer class is a controller class that has a function that randomly chooses and presents questions
 * to the student and keeps track of and returns how many questions the student answered correctly.
 * Quizzer chooses the user-requested number of questions at random, displays them, checks the user's answer 
 * against the actual answer, and keep track of and returns how many of the questions the user answered
 * correctly. 
 */
class Quizzer{
	public int takeQuiz(int numberofQuestions, ArrayList<Question> questions) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random(); 
		int numCorrect = 0; 
		String guess;  
		for (int i = 0; i < numberofQuestions; i++) { //for number questions being answered
		//need random int to pick from number of questions available
			int randomnum = rand.nextInt(18); 
            System.out.println(questions.get(randomnum));
            System.out.print("Type the letter of your choice a,b,c,d: ");
            guess = sc.nextLine();
            if (guess.equals(questions.get(randomnum).getAnswer())){ 
            	numCorrect = numCorrect + 1;
                System.out.println("Correct!");
            } else {
            	System.out.println("Sorry. The correct answer is " + questions.get(randomnum).getAnswer() + ".");
            }
        }
        return numCorrect;
		}
}

/**
 * The QuizApp class is the class with the main function that runs the application.
 * It will print the welcome banner as well as the footer and present the user with choices.
 */
public class QuizApp {
	//function to print the header
	public static void welcomeBanner(){
        System.out.println("*          What could possibly be more fun than this?          *");
        System.out.println("****************************************************************");
        System.out.println("*               OOP Theory and Concept Questions               *");
        System.out.println("****************************************************************");
        System.out.println("*          Nothing. Nothing at all. Nope. Nada. Nunca.         *");
        System.out.println("\n");
        System.out.println(" ");
    }
	//function to get user choice from a menu of options
	//this function will also check user input in the case the user enters something invalid
	//for me: refer to showMenuAndGetChoice function from GameZone hw
	public static int showMenuAndGetChoice(Scanner sc) {
		int choice; 
		do {
			System.out.println("Here are your choices: ");
			System.out.println("1. Take a quiz. ");
			System.out.println("2. See all questions and answers. ");
			System.out.println("3. Exit. ");
			System.out.print("Enter the number of your choice: ");
			try {
				choice = sc.nextInt();
				sc.nextLine();
				System.out.println();
			} catch (Exception ex) { 
				System.out.println();
				System.out.println("Please enter a valid choice.");
				System.out.println();
				sc.nextLine();
				choice = 0;
			}
		} while (choice < 1 || choice > 3);
		return choice;
	}
	//function to print thank you message shown in sample output
	  public static void goodbyeBanner(){
	        System.out.println("****************************************************************");
	        System.out.println("*               Thank you for taking CPSC 24500                *");
	        System.out.println("****************************************************************");
	    }
	//main function of the program that displays both banners, takes in user choice, and considers the json file
	  public static void main(String[] args) {
		  //print the welcome banner
		  welcomeBanner(); 
		  //hold all the array of questions
		  ArrayList<Question> questions = new ArrayList<Question>(); 
		  Scanner sc = new Scanner(System.in);
		  //need variables 
		  int choice;
		  int numQuestions;
		  int numCorrect;
		  String path;
		  Question question;
		  QuestionReader qr = new QuestionReader();
		  QuestionPrinter qp = new QuestionPrinter();
		  Quizzer qz = new Quizzer();
		  //need name of file
		  System.out.print("Enter name of file containing questions: "); 
		  path = sc.nextLine();
		  System.out.println();
		  questions = qr.readFromJSON(path); //C:\codes\questions_without_answers.json
		  do { //if user does not wish to exit
				choice = showMenuAndGetChoice(sc); 
				if (choice == 1) { //take a quiz case
					System.out.print("How many questions would you like? ");
					numQuestions = sc.nextInt();
					numCorrect = qz.takeQuiz(numQuestions, questions);
					sc.nextLine();
					//System.out.println();
				// results are displayed
				System.out.printf("You answered %s correct out of %s questions asked.", numCorrect, numQuestions);
				System.out.println();
				System.out.println();
				}else if (choice == 2) { //see questions and answers case
					qp.printAll(questions);
				} 
			} while (choice != 3); //exit program case
		  	goodbyeBanner();	  
	  }
}