package Sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RankingController implements Initializable{
	
	private float time;
	
	@FXML private TextField inputName;
	@FXML private Button InputBtn;
	@FXML private Button nextBtn;
	@FXML private Text Score;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Score.setText(Float.toString(GameController.t));
	}
	
	@FXML
	public void EndGame() {
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
	}
	
	@FXML
	public void InputName() {
		InputBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					WriteName();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void WriteName() throws IOException {
		if(GameBoard.holes == 21) {
			File file = new File("./src/sudoku/Ranking_easy.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String list[] = new String[5];
			String s = inputName.getText().toString();
			time = GameController.t;
			
			int n = 0;
			
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				list[n] = line;
				n++;
			}
			bufReader.close();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < n; i++) {
				String info[] = list[i].split("초, ");
				if (Float.parseFloat(info[0]) > time) {
					if (n < 5) list[n] = list[n-1];
					for (int j = n-1; j > i; j--) {
						list[j] = list[j-1];
					}
					list[i] = time + "초, (닉네임:" + s + ")";
					if (n<5) n++;
					break;
				}
			}
			
			if (file.isFile() && file.canWrite()) {
				//write
				for (int i = 0; i < n; i++) {
					bufferedWriter.write(list[i]);
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		} else {
			File file = new File("src/sudoku/Ranking_hard.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String list[] = new String[5];
			String s = inputName.getText().toString();
			time = GameController.t;
			
			int n = 0;
			
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				list[n] = line;
				n++;
			}
			bufReader.close();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < n; i++) {
				String info[] = list[i].split("초, ");
				if (Float.parseFloat(info[0]) > time) {
					if (n < 5) list[n] = list[n-1];
					for (int j = n-1; j > i; j--) {
						list[j] = list[j-1];
					}
					list[i] = time + "초, (닉네임:" + s + ")";
					if (n<5) n++;
					break;
				}
			}
			
			if (file.isFile() && file.canWrite()) {
				//write
				for (int i = 0; i < n; i++) {
					bufferedWriter.write(list[i]);
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		}
	}
}