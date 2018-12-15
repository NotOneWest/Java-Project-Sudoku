package Sudoku;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameController implements Initializable {

    @FXML
    private GridPane sudokuTable;
	
//	@FXML
//	public void StartGameEasy(ActionEvent event) throws IOException{
//		Parent root = FXMLLoader.load(getClass().getResource("GameBoardEasy.fxml"));
//		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		
//		primaryStage.setScene(new Scene(root));
//		primaryStage.setTitle("Sudoku");
//		primaryStage.show();
//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int holes = GameBoard.holes;
		int[][] board = Sudoku.MakeBoard.CreateBoard();
		int[][] p_board = Sudoku.MakeBoard.MakeHoles(board, holes);
		
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				sudokuTable.add(new Label(Integer.toString(p_board[x][y])), x, y);
			}
		}
		
	}
}

