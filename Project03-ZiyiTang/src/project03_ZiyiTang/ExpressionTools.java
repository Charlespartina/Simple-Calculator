package project03_ZiyiTang;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides necessary methods to evaluate mathematical expressions.
 * @author Ziyi Tang (Charles)
 * 
 */
public class ExpressionTools {

	private static String[] NUMBER_STRING = new String[] { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9" };
	private static int numofbrackets = 0;

	/**
	 * This is a private constructor. This class cannot be used to create an object.
	 */
	private ExpressionTools() {

	}
    
	/**
	 * This method serves to calculate an mathematical expression.
	 * @param expression An infix mathematical expression.
	 * @return The result of the expression
	 */
	public static String PostfixCalculate(String expression) {
		
		try {
			String postfix = ConvertToPostfix(expression);
			
			return "" + CalculateExpression(postfix);
			
		} catch (PostFixException e) {
			// TODO Auto-generated catch block
			return "INVALID: " + e.getMessage();
		} catch (ArithmeticException e){
			return "INVALID: Arithmetic Exception (such as the denominator equals to 0).";
		}
		
	}
    
	/**
	 * This method serves to convert an infix expression to a postfix expression.
	 * @param infix An infix expression.
	 * @return A postfix expression.
	 * @throws PostFixException
	 */
	private static String ConvertToPostfix(String infix) throws PostFixException {

		MyStack<String> operator = new MyStack<String>();
		ArrayList<String> tokens = new ArrayList<String>();
		tokens = stringToToken(infix);
		ArrayList<String> result = new ArrayList<String>();
		String postfix = new String();

		for (int i = 0; i < tokens.size(); i++) {
			
			if (isOperand(tokens.get(i))) {
				result.add(tokens.get(i));
			} else if (isLeftBrace(tokens.get(i))) {
				operator.push(tokens.get(i));
			} else if (isOperator(tokens.get(i))) {

				/*
				 * If the stack has top elements and those elements have higher
				 * precedence than the current token: 
				 * 
				 * 1. The "*" "/" are higher than "+" "-" 
				 * 2. The "/" is higher than "*" if the "/" is before "*" 
				 * 3. The "-" is higher than "+" if the "-" is before "+"
				 * 
				 * Then pop this element and append it to the postfix expression
				 */

				while ((operator.peek() != null)
						&& (tokens.get(i).equals("+") && (operator.peek()
								.equals("*") || operator.peek().equals("/") || operator
								.peek().equals("-")))) {
					result.add(operator.pop());
				}

				while ((operator.peek() != null)
						&& (tokens.get(i).equals("-") && (operator.peek()
								.equals("*") || operator.peek().equals("/") || operator
								.peek().equals("-")))) {
					result.add(operator.pop());
				}

				while ((operator.peek() != null)
						&& (tokens.get(i).equals("*") && operator.peek()
								.equals("/"))) {
					result.add(operator.pop());
				}

				while ((operator.peek() != null)
						&& (tokens.get(i).equals("/") && operator.peek()
								.equals("/"))) {
					result.add(operator.pop());
				}
				operator.push(tokens.get(i));

			} else if (isRightBrace(tokens.get(i))) {
				numofbrackets++;
				while (operator.peek() != null) {
					if (!operator.peek().equals("("))
						result.add(operator.pop());
					else {
						operator.pop();
						numofbrackets--;
						break;
					}
				}

			} else {
				throw new PostFixException("This expression has illegal character");
			}

		}
		while (operator.peek() != null) {
			result.add(operator.pop());
		}

		for (int i = 0; i < result.size(); i++) {
			postfix = postfix.concat(" " + result.get(i));
		}
		return postfix;
	}
    
	/**
	 * This method serves to calculate a postfix expression.
	 * @param postfix An postfix expression
	 * @return The result of the expression.
	 * @throws PostFixException
	 */
	private static Integer CalculateExpression(String postfix) throws PostFixException {
		MyStack<Integer> operand = new MyStack<Integer>();
		ArrayList<String> tokens = new ArrayList<String>();
		tokens = stringToToken(postfix);
		Integer result;
		for (int i = 0; i < tokens.size(); i++) {
			if (isOperand(tokens.get(i))) {
				operand.push(tokenToInteger(tokens.get(i)));
			} else if (isOperator(tokens.get(i))) {
				Integer operand2 = operand.pop();
				Integer operand1 = operand.pop();
				if (operand1 == null || operand2 == null) {
					throw new PostFixException("Operands and operators cannot be matched");
				}
				if (tokens.get(i).equals("+")) {
					operand.push(operand1 + operand2);
				}
				if (tokens.get(i).equals("-")) {
					operand.push(operand1 - operand2);
				}
				if (tokens.get(i).equals("*")) {
					operand.push(operand1 * operand2);
				}
				if (tokens.get(i).equals("/")) {
					operand.push(operand1 / operand2);
				}

			} else {
				throw new PostFixException("This expression's parenthesis cannot be matched");
			}

		}

		result = operand.pop();
		if (operand.peek() == null && numofbrackets == 0) {
			return result;
		}
		else if (operand.peek() != null) {
			
			throw new PostFixException("Extra operand");
		} 
        else{
        	numofbrackets = 0;
			throw new PostFixException("This expression's parenthesis cannot be matched");
		}
		

	}
    
	/**
	 * This method serves to check if the token is an operand (including positive and negative). 
	 * 
	 * @param string The token supposed to be checked by this method
	 * @return True if the token is an operand. Otherwise false.
	 * @throws PostFixException
	 */
	private static boolean isOperand(String string) throws PostFixException {
		
		/* The initial character of an operand can be "-", but the length of this token should
		 * be larger than 1. Otherwise the token isn't a number but a "-" operator.
		 */
        if (string.substring(0,1).equals("-") && string.length() == 1){
        	return false;
        }
        if (!isNumber(string.substring(0, 1)) && !string.substring(0,1).equals("-")){
        	return false;
        }
		for (int i = 1; i < string.length(); i++) {
			if (!isNumber(string.substring(i, i + 1)) ) {
				return false;
			}
		}
		if (string.length() != 1 && string.substring(0,1).equals("0")){
			throw new PostFixException("Operand with two or more digits can't have '0' as initial");
		}
		return true;
	}
    
	/**
	 * This method serves to check if a character in a token 
	 * is a number.
	 * 
	 * @param string The character supposed to be checked by this method
	 * @return True if the character is a number. Otherwise false.
	 * @throws PostFixException
	 */
	public static boolean isNumber(String number) {
		for (int i = 0; i < 10; i++) {
			if (number.equals(NUMBER_STRING[i])) {
				return true;
			}
		}
		return false;
	}
    
	/**
	 * This method serves to check if the token is an operator. 
	 * 
	 * @param string The token supposed to be checked by this method
	 * @return True if the token is an operator. Otherwise false.
	 * @throws PostFixException
	 */
	private static boolean isOperator(String string) {
		if (string.equals("+") || string.equals("-") || string.equals("*")
				|| string.equals("/"))
			return true;
		return false;
	}
    
	/**
	 * This method serves to check if the token is a left bracket. 
	 * 
	 * @param string The token supposed to be checked by this method
	 * @return True if the token is a left bracket. Otherwise false.
	 * @throws PostFixException
	 */
	private static boolean isLeftBrace(String string) {
		if (string.equals("("))
			return true;
		return false;
	}

	/**
	 * This method serves to check if the token is a right bracket. 
	 * 
	 * @param string The token supposed to be checked by this method
	 * @return True if the token is a right bracket. Otherwise false.
	 * @throws PostFixException
	 */
	private static boolean isRightBrace(String string) {
		if (string.equals(")"))
			return true;
		return false;
	}
    
	/**
	 * This method serves to split an expression into several tokens.
	 * @param text A complete expression.
	 * @return Several tokens of which the expression consists of.
	 * @throws PostFixException 
	 */
	private static ArrayList<String> stringToToken(String text) throws PostFixException {
		ArrayList<String> token = new ArrayList<String>();
		//Check if the input has no expression at all
		if (text.length() == 0){
			throw new PostFixException("No operator or operand is found");
		}
		Scanner expression = new Scanner(text);
		while (expression.hasNext()) {
			token.add(expression.next());
		}
		expression.close();
		return token;
	}

	/**
	 * This method serves to convert a token that is already verified as an operand to an integer.
	 * @param token The token prepared to be converted to Integer.
	 * @return The value of the token as an integer
	 */
	private static Integer tokenToInteger(String token) {
			Integer result = new Integer(token);
			return result;
	
	}

}
