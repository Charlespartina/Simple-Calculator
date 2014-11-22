package project03_ZiyiTang;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * This class serves to create buttons for enter and clear.
 * @author Ziyi Tang (Charles)
 *
 */
public class EnterButton {
    private PFont textfont;
    private String character;
    private int posx;
    private int posy;
    private int buttonWidth;
	private int buttonHeight;
    private int basecolorR;
    private int basecolorG;
    private int basecolorB;
    private int highlightcolorR;
    private int highlightcolorG;
    private int highlightcolorB;
    private boolean overButton;
    private PApplet parent;
    
    
    /**
	 * Constructor of enter button.
	 * @param character Name and usage of the button
	 * @param posx x coordinate of the button's position
	 * @param posy y coordinate of the button's position
	 * @param basecolorR Color of the button (Red)
	 * @param basecolorG Color of the button (Green)
	 * @param basecolorB Color of the button (Blue)
	 * @param highlightcolorR Color of the button when the mouse is over the button (Red)
	 * @param highlightcolorG Color of the button when the mouse is over the button (Green)
	 * @param highlightcolorB Color of the button when the mouse is over the button (Blue)
	 * @param pa The PApplet of GUICalculator class
	 */
    public EnterButton(String character, int posx, int posy, int basecolorR, int basecolorG, int basecolorB, int highlightcolorR,
    		int highlightcolorG, int highlightcolorB, PApplet pa){
    	this.parent = pa;
    	textfont = parent.createFont("Helvetica-Light-48", 30);
    	this.character = character;
    	this.posx = posx;
    	this.posy = posy;
    	this.basecolorR = basecolorR;
    	this.basecolorG = basecolorG;
    	this.basecolorB = basecolorB;
    	this.highlightcolorR = highlightcolorR;
    	this.highlightcolorG = highlightcolorG;
    	this.highlightcolorB = highlightcolorB;
    	overButton = false;
    	buttonWidth = 100;
    	buttonHeight = 70;
    	
    }
    
    /**
     * Display the button at proper position with character on it
     */
    public void displayButton(){
    	mouseUpdate(parent.mouseX, parent.mouseY);

		if (overButton)
			parent.fill(highlightcolorR, highlightcolorG, highlightcolorB);
		else
			parent.fill(basecolorR, basecolorG, basecolorB);

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
    
    
}


