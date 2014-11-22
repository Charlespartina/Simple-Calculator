package project03_ZiyiTang;

import processing.core.*;
import project03_ZiyiTang.ExpressionTools;

/**
 * This class serves to create a graphic user interface of the calculator.
 * @author Ziyi Tang (Charles)
 *
 */
public class GUICalculator extends PApplet {

	
	private static final long serialVersionUID = 1L;
	BasicButton[] operandbutton = new BasicButton[10];
	BasicButton[] operatorbutton = new BasicButton[6];
	EnterButton[] functionbutton = new EnterButton[2];
	String explicitexpression;
	String implicitexpression;
	private PFont expfont;
	private PFont errfont;
	private int expressionstartpos;
    
	/**
	 * Set up the initial graphic layout and values of several variables of the calculator.
	 */
	public void setup() {
		size(700, 400);
        
		/* The initial value of expressions are zero, so if the user click "=" without click on any
         * operand or operator before, the result will still be 0 but not "null"
         */
		explicitexpression = "0";
		implicitexpression = "0";
		expressionstartpos = 30;
		operandbutton[0] = new BasicButton("0", 340, 160, 255, 235, this);
		operandbutton[1] = new BasicButton("1", 10, 320, 255, 235, this);
		operandbutton[2] = new BasicButton("2", 120, 320, 255, 235, this);
		operandbutton[3] = new BasicButton("3", 230, 320, 255, 235, this);
		operandbutton[4] = new BasicButton("4", 10, 240, 255, 235, this);
		operandbutton[5] = new BasicButton("5", 120, 240, 255, 235, this);
		operandbutton[6] = new BasicButton("6", 230, 240, 255, 235, this);
		operandbutton[7] = new BasicButton("7", 10, 160, 255, 235, this);
		operandbutton[8] = new BasicButton("8", 120, 160, 255, 235, this);
		operandbutton[9] = new BasicButton("9", 230, 160, 255, 235, this);
		operatorbutton[0] = new BasicButton("(", 340, 240, 150, 100, this);
		operatorbutton[1] = new BasicButton(")", 340, 320, 150, 100, this);
		operatorbutton[2] = new BasicButton("+", 450, 160, 150, 100, this);
		operatorbutton[3] = new BasicButton("-", 560, 160, 150, 100, this);
		operatorbutton[4] = new BasicButton("*", 450, 240, 150, 100, this);
		operatorbutton[5] = new BasicButton("/", 560, 240, 150, 100, this);
		functionbutton[0] = new EnterButton("=", 450, 320, 93, 149, 88, 63,
				139, 79, this);
		functionbutton[1] = new EnterButton("AC", 560, 320, 252, 99, 93, 239,
				87, 84, this);
		
		expfont = createFont("Helvetica-Light-48", 30);
		errfont = createFont("Helvetica-Light-48", 20);
	}
    
	/**
	 * Update the status and show the graphic calculator.
	 */
	public void draw() {

		// Draw background
		background(200);
		textFont(createFont("Helvetica-Bold-48", 20));
		fill(170);
		text("Project 03 - Calculator - Ziyi Tang (Charles)", 20, 145);

		// Display Buttons
		rectMode(CENTER);
		fill(50);
		rect(width / 2, 60, width - 20, 100);
		rectMode(CORNER);
		operandbutton[0].displayButton();
		operandbutton[1].displayButton();
		operandbutton[2].displayButton();
		operandbutton[3].displayButton();
		operandbutton[4].displayButton();
		operandbutton[5].displayButton();
		operandbutton[6].displayButton();
		operandbutton[7].displayButton();
		operandbutton[8].displayButton();
		operandbutton[9].displayButton();
		operatorbutton[0].displayButton();
		operatorbutton[1].displayButton();
		operatorbutton[2].displayButton();
		operatorbutton[3].displayButton();
		operatorbutton[4].displayButton();
		operatorbutton[5].displayButton();
		functionbutton[0].displayButton();
		functionbutton[1].displayButton();

		// Display explicit expression
		/* If the the expression is too long, the screen will only show a part
		 * of the expression. 
		 * But the whole expression is still stored in the variable implicitexpression.
		 */
		
        if (explicitexpression.substring(0, 1).equals("I")){
        	textFont(errfont);
    		textAlign(LEFT);
    		fill(255, 0, 0);
    		text(explicitexpression, expressionstartpos, 50);
    		text("Please click on 'AC' to continue", expressionstartpos, 80);
    		
    		
		} else {
			
			textFont(expfont);
			textAlign(LEFT);
			fill(255);
			if (textWidth(explicitexpression) <= 645) {
				text(explicitexpression, expressionstartpos, 70);
			} else {
				explicitexpression = explicitexpression.substring(1);
				text(explicitexpression, expressionstartpos, 70);
			}
		}
		
		
		

	}
    
	/**
	 *  Input operands and operators, and implement Expression tools when buttons are clicked.
	 */
	public void mouseClicked() {
		// Click on operand buttons
		for (int i = 0; i < operandbutton.length; i++) {
			if (operandbutton[i].overButton()) {
				/* If there is only a "0" in the expression, then whenever an operand is clicked,
				 * the "0" will be removed automatically, just like what the real calculator does.
				 */
				if (explicitexpression.equals("0")){
					explicitexpression = "";
					implicitexpression = "";
				}
				explicitexpression = explicitexpression.concat(operandbutton[i]
						.getCharacter());

				/* When the user inputs two numbers continuously, the program
				 * recognizes them as a single operand, then there is no space
				 * between the two numbers in the implicit expression.
				 */ 
				if (implicitexpression.length() == 0) {
					implicitexpression = implicitexpression.concat(" "
							+ operandbutton[i].getCharacter());
				} else if (ExpressionTools.isNumber(implicitexpression
						.substring(implicitexpression.length() - 1))) {
					implicitexpression = implicitexpression
							.concat(operandbutton[i].getCharacter());
				} else {
					implicitexpression = implicitexpression.concat(" "
							+ operandbutton[i].getCharacter());
				}


			}
		}
		// Click on operator buttons
		for (int i = 0; i < operatorbutton.length; i++) {
			
			/* If there is only a "0" in the expression, then whenever an "(" is clicked,
			 * the "0" will be removed automatically, just like what the real calculator does.
			 */
			if (operatorbutton[0].overButton() && explicitexpression.equals("0")){
				explicitexpression = "";
				implicitexpression = "";
			}
			if (operatorbutton[i].overButton()) {
				explicitexpression = explicitexpression
						.concat(operatorbutton[i].getCharacter());

				implicitexpression = implicitexpression.concat(" "
						+ operatorbutton[i].getCharacter());
				
            }
			
		}
		// Click on EQUAL button
		if (functionbutton[0].overButton()) {
           
			implicitexpression = ExpressionTools.PostfixCalculate(implicitexpression);
			explicitexpression = implicitexpression;
		}
		// Click on CLEAR button
		if (functionbutton[1].overButton()) {
            implicitexpression = "0";
            explicitexpression = "0";
		}
		
		
		
		
	}

}
