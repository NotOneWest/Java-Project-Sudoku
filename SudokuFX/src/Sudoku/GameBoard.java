package Sudoku;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameBoard{
	public static int holes;
	
	public void StartGameEasy(ActionEvent event) throws IOException{
		holes = 21;
		Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Sudoku");
		primaryStage.show();
	}
	
	public void StartGameHard(ActionEvent event) throws IOException{
		holes = 41;
		Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Sudoku");
		primaryStage.show();
	}
}
