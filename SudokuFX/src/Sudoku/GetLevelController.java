package Sudoku;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GetLevelController extends GameBoard{
	
	private String getlv;
	
	@FXML
	public void GetLevel(ActionEvent event) throws IOException {
		getlv = ((Button) event.getSource()).getText();
		if(getlv.equals("easy")) {
			StartGameEasy(event);
		} else {
			StartGameHard(event);
		}
	}
}
