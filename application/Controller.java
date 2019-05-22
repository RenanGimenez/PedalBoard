package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Controller implements Initializable {
	
	@FXML
	Text modeLCD;
	
	@FXML
	TextArea arduinoLCD;
	
	private ArduinoScreenText arduinoText;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObjectsPool.getInstance().put("ArduinoText", new ArduinoScreenText());
		arduinoText = (ArduinoScreenText) ObjectsPool.getInstance().get("ArduinoText");
		arduinoText.setTextAlignement(Alignement.CENTER);
	}
	
	@FXML
	public void setMode() {
		System.out.println("setMode");
		Integer mode = Integer.decode(modeLCD.getText());
		mode = (mode)%3 + 1;
		modeLCD.setText(mode.toString());
		arduinoText.setText("Mode "+mode);
		System.out.println(arduinoLCD);
		arduinoLCD.setText(arduinoText.getText());
		
	}
	
	@FXML
	public void setPresetNoteMinus() {
		System.out.println("setPresetNoteMinus");
	}
	
	@FXML
	public void setPresetNotePlus() {
		System.out.println("setPresetNotePlus");
	}
	
	@FXML
	public void setListOctaveMinus() {
		System.out.println("setListOctaveMinus");
	}
	
	@FXML
	public void setListOctavePlus() {
		System.out.println("setListOctavePlus");
	}
}
