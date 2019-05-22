package application;
import java.lang.Math;
public class ArduinoScreenText {
	private static int NB_CHAR = 20;
	private static int NB_LINE = 4;
	private Alignement alignement;
	private char[][] text;
	
	public ArduinoScreenText() {
		this.text = new char[NB_LINE][NB_CHAR];
		this.alignement = alignement.CENTER;
	}
	
	/* @brief 
	 * return all the text on the arduino screen
	 * */
	public String getText() {
		String textBuilder = new String();
		for(int lineNum = 0; lineNum < NB_LINE; ++lineNum) {
			textBuilder += new String(this.text[lineNum])+"\n";
		}
		return textBuilder.substring(0, textBuilder.length()-1);
	}
	
	/* @brief 
	 *set all the text of the arduino screen
	 * */
	public void setText(String newText) {
		setAlignementLeft();
		String[] textArray = newText.split("\n");
		int numberOfLines = Math.min(NB_LINE, textArray.length);
		for(int i = 0; i < numberOfLines; ++i) {
			if(textArray.length > NB_CHAR)
				this.text[i] = textArray[i].substring(0, NB_CHAR).toCharArray();
			else {
				this.text[i] = textArray[i].toCharArray();
			}
		}
		setTextAlignement(alignement);
	}
	
	/* @brief 
	 *set one line on the arduino screen
	 * */
	public void setLine(int lineNum, String newText) {
		setAlignementLeft();
		String[] textArray = newText.split("\n");
		this.text[lineNum] = textArray[0].substring(0, NB_CHAR).toCharArray();
		setTextAlignement(alignement);
	}
	
	/* @brief 
	 *return one line of the arduino screen
	 * */
	public String getLine(int lineNum) {
		return new String(this.text[lineNum]);
	}
	
	public void setTextAlignement(Alignement alignement) {
		switch(alignement) {
		case LEFT:
			setAlignementLeft();
			this.alignement = Alignement.LEFT;
			break;
		case CENTER:
			setAlignementCenter();
			this.alignement = Alignement.CENTER;
			break;
		case RIGHT:
			setAlignementRight();
			this.alignement = Alignement.RIGHT;
			break;
		}
	}

	private void setAlignementLeft() {
		for(int lineNum = 0; lineNum < NB_LINE; ++lineNum) {
			
			this.text[lineNum] = new String(this.text[lineNum]).trim().toCharArray();
		}
	}
	
	private void setAlignementCenter() {
		for(int lineNum = 0; lineNum < NB_LINE; ++lineNum) {
			String strText = new String(this.text[lineNum]);
			int lineLength = strText.length();
			int whiteSpaceLength = NB_CHAR - lineLength;
			for (int i=0; i<whiteSpaceLength/2;++i)
				strText = " "+strText;	
		}
	}
	
	private void setAlignementRight() {
		for(int lineNum = 0; lineNum < NB_LINE; ++lineNum) {
			String strText = new String(this.text[lineNum]);
			int lineLength = strText.length();
			int whiteSpaceLength = NB_CHAR - lineLength;
			for (int i=0; i<whiteSpaceLength;++i)
				strText = " "+strText;	
		}
		
	}

	
}
