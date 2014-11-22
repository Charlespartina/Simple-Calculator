package project03_ZiyiTang;

import processing.core.*;

/**
 * This class serves to create buttons for operands and operators.
 * @author Ziyi Tang (Charles)
 *
 */
public class BasicButton {

	private String character;
	private PFont textfont;
	private int posx;
	private int posy;
	private int buttonWidth;
	private int buttonHeight;
	private PApplet parent;
	private boolean overButton = false;
	private int basecolor;
	private int highlight;

	/**
	 * Constructor of basic button.
	 * @param character Operand or operator indicated by the button
	 * @param posx x coordinate of the button's position
	 * @param posy y coordinate of the button's position
	 * @param basecolor Color of the button
	 * @param highlight Color of the button when the mouse is over the button
	 * @param pa The PApplet of GUICalculator class
	 */
	public BasicButton(String character, int posx, int posy, int basecolor,
			int highlight, PApplet pa) {

		this.posx = posx;
		this.posy = posy;
		this.character = character;
		buttonWidth = 100;
		buttonHeight = 70;
		this.basecolor = basecolor;
		this.highlight = highlight;
		parent = pa;
		textfont = parent.createFont("Helvetica-Light-48", 30);

	}
    
	/**
	 * Display the button in proper position with character on it
	 */
	public void displayButton() {
		mouseUpdate(parent.mouseX, parent.mouseY);

		if (overButton)
			parent.fill(highlight);
		else
			parent.fill(basecolor);

		parent.noStroke();
		parent.rect(posx, posy, buttonWidth, buttonHeight);

		// Display the character on those buttons
		parent.textFont(textfont);
		parent.textAlign(PConstants.CENTER);
		parent.fill(0);
		parent.text(character, posx + buttonWidth / 2, posy + buttonHeight / 2
				+ 12);

	}
    
	/**
	 * Check if the mouse is over the button
	 * @param x x coordinate of the position of the mouse
	 * @param y y coordinate of the position of the mouse
	 */
	public void mouseUpdate(int x, int y) {
		if ((x >= posx && x <= posx + buttonWidth)
				&& (y >= posy && y <= posy + buttonHeight)) {
			overButton = true;
		} else {
			overButton = false;
		}
	}

	/**
	 * Check if the mouse is over the button
	 * @return True if the mouse is over the button. Otherwise false.
	 */
	public boolean overButton() {
		return overButton;
	}
    
	/**
	 * Get the operand or operator on the corresponding button
	 * @return
	 */
	public String getCharacter() {
		return character;
	}

}
