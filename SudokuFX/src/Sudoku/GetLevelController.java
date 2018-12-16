package Sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GetLevelController extends GameBoard implements Initializable{
	
	private String getlv;
	
	@FXML private GridPane RankBoardEasy;
	@FXML private GridPane RankBoardHard;
	
	@FXML
	public void GetLevel(ActionEvent event) throws IOException {
		getlv = ((Button) event.getSource()).getText();
		if(getlv.equals("easy")) {
			StartGameEasy(event);
		} else if(getlv.equals("hard")){
			StartGameHard(event);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			File FileEasy = new File("src/sudoku/Ranking_easy.txt");
			FileReader FileReaderEasy = new FileReader(FileEasy);
			BufferedReader bufReaderEasy = new BufferedReader(FileReaderEasy);
			ArrayList<String> ListEasy = new ArrayList<String>();
			String LineEasy = "";
			while((LineEasy = bufReaderEasy.readLine()) != null) {
				ListEasy.add(LineEasy);
			}
			if (ListEasy.size() > 5) {
				for(int i=0; i<5; i++) {
					String[] info = ListEasy.get(i).split(", ");
					Text text = new Text(Integer.toString(i+1) +"등 : " + info[1] + "  " + info[0]);
					RankBoardEasy.add(text, 0, i+1);
				}
			}
			else {
				for(int i=0; i<ListEasy.size(); i++) {
					String[] info = ListEasy.get(i).split(", ");
					Text text = new Text(Integer.toString(i+1) +"등 : " + info[1] + "  " + info[0]);
					RankBoardEasy.add(text, 0, i+1);
				}
			}
			bufReaderEasy.close();
//--------------------------------------------------------------------------------------------------------------------
			File FileHard = new File("src/sudoku/Ranking_hard.txt");
			FileReader FileReaderHard = new FileReader(FileHard);
			BufferedReader bufReaderHard = new BufferedReader(FileReaderHard);
			ArrayList<String> ListHard = new ArrayList<String>();
			String LineHard = "";
			while((LineHard = bufReaderHard.readLine()) != null) {
				ListHard.add(LineHard);
			}
			if (ListHard.size() > 5) {
				for(int i=0; i<5; i++) {
					String[] info = ListHard.get(i).split(", ");
					Text text = new Text(Integer.toString(i+1) +"등 : " + info[1] + "  " + info[0]);
					RankBoardHard.add(text, 0, i+1);
				}
			}
			else {
				for(int i=0; i<ListHard.size(); i++) {
					String[] info = ListHard.get(i).split(", ");
					Text text = new Text(Integer.toString(i+1) +"등 : " + info[1] + "  " + info[0]);
					RankBoardHard.add(text, 0, i+1);
				}
			}
			bufReaderHard.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}