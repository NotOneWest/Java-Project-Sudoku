package Sudoku;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameController implements Initializable {
	
	private int mouse_x;
	private int mouse_y;
	
	private int[][] p_board;
	private int[][] h_board = new int[9][9];
	private int[][] a_board = new int[9][9];
	
    @FXML
    private GridPane sudokuTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) { // 보드를 만들어준다.
		int holes = GameBoard.holes;
		int[][] board = Sudoku.MakeBoard.CreateBoard();
		p_board = Sudoku.MakeBoard.MakeHoles(board, holes);
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				h_board[i][j] = p_board[i][j];
			}
		}
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				a_board[i][j] = p_board[i][j];
			}
		}
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(p_board[i][j] + "  ");
			}
			System.out.println();
		}
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				if(p_board[x][y] != 0) { // 0이 아닌수들은 보드에 표시
					Text text = new Text(Integer.toString(p_board[x][y]));
					sudokuTable.add(text, x, y);
					text.setFont(Font.font("Arial",FontWeight.BOLD,20));
				}
			}
		}
	}

	public void FillYourAnswer(int val, int x, int y) { // 사용자가 입력한 값을 보드에 담는다.
		x /= 50;
		y /= 50;
		Label label = new Label();
		if(p_board[x][y] == 0 || h_board[x][y] == 0) {
			p_board[x][y] = val;
			label.setText(Integer.toString(p_board[x][y]));
			label.setStyle("-fx-background-color:white;");
			boolean check = Sudoku.CheckAnswer.Grading(a_board,y,x,val);
			if(check == true) {
				label.setTextFill(Color.BLUE);
			}
			else label.setTextFill(Color.RED);
			sudokuTable.add(label, x, y);
		}
	}

	@FXML
	public void gridPaneClicked() {
		sudokuTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouse_x = (int) event.getX();
				mouse_y = (int) event.getY();
			}
		});
	}
	
	@FXML
	public void btn1() {
		FillYourAnswer(1, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn2() {
		FillYourAnswer(2, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn3() {
		FillYourAnswer(3, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn4() {
		FillYourAnswer(4, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn5() {
		FillYourAnswer(5, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn6() {
		FillYourAnswer(6, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn7() {
		FillYourAnswer(7, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn8() {
		FillYourAnswer(8, mouse_x, mouse_y);
	}
	
	@FXML
	public void btn9() {
		FillYourAnswer(9, mouse_x, mouse_y);
	}
}

