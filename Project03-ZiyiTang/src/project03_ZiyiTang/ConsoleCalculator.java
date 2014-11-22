package project03_ZiyiTang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class serves to evaluate expressions from a text file, 
 * and print results into another text file. 
 * @author Ziyi Tang (Charles)
 *
 */
public class ConsoleCalculator {
    
	/**
	 * This main method runs this program with two arguments in command line.
	 * @param args Two arguments in command line: One is for providing expressions, 
	 * the other is for printing results.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub


		if (args.length != 2) {
			System.err.println("Invalid text file directories or names! Only two arguments are allowed.");
			System.exit(1);
		} else {
			try {
				// Identify Input and Output file
				File file = new File(args[0]);
				File output = new File(args[1]);
				
				Scanner fileinput = new Scanner(file);
				PrintWriter fileoutput = new PrintWriter(output);
				
				ArrayList<String> inputexpressions = new ArrayList<String>();
				inputexpressions = scanexpressions(fileinput);
				
				// Print all results of those expressions
				for (int i = 0; i < inputexpressions.size(); i++){
					//fileoutput.printf("For the expression %-20s \n",inputexpressions.get(i));
					fileoutput.printf("Result: %-2s \n",
							ExpressionTools.PostfixCalculate(inputexpressions.get(i)));
				}
				fileoutput.close();
			
			} catch (FileNotFoundException ex) {
				System.err.println("The input file doesn't exist! ");
			} 
		}

	}
    
	/**
	 * Convert expressions from Scanner type to ArrayList<String> type.
	 * @param input Expressions stored as Scanner type.
	 * @return Expressions stored as ArrayList<String> type.
	 * @throws FileNotFoundException
	 */
	private static ArrayList<String> scanexpressions(Scanner input)
			throws FileNotFoundException {
		ArrayList<String> result = new ArrayList<String>();
		while (input.hasNext()) {
			result.add(input.nextLine());
		}
		input.close();
		return result;
	}
	
	
	
	

}
