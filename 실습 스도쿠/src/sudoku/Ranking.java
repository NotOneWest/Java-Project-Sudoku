package sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Ranking
{
	// 파일에 플레이어의 닉네임을 받아서 스도쿠를 푼 시간초와 입력해준다 --- 난이도 별로
	public static void WriteAndReadNicks(String nicks, float time, int holes) {
		try {
			if (holes == 21) { // 난이도 쉬움일때.
				File file = new File("./src/sudoku/Ranking_easy.txt");
				FileReader filereader = new FileReader(file);
				BufferedReader bufReader = new BufferedReader(filereader);
				String list[] = new String[5];
				
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
						list[i] = time + "초, (닉네임:" + nicks + ")";
						if (n<5) n++;
						break;
					}
				}
				
				if (n == 0) {
					list[0] = time + "초, (닉네임:" + nicks + ")";
					n++;
				}
				System.out.println("난이도 : 쉬움");
				for (int i = 0; i < n; i++) {
					System.out.println((i+1) + "등 -> " + list[i]);
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
			else {// 난이도 어려움일때.
				File file = new File("src/sudoku/Ranking_easy.txt");
				FileReader filereader = new FileReader(file);
				BufferedReader bufReader = new BufferedReader(filereader);
				String list[] = new String[5];
				
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
						list[i] = time + "초, (닉네임:" + nicks + ")";
						if (n<5) n++;
						break;
					}
				}
				
				if (n == 0) {
					list[0] = time + "초, (닉네임:" + nicks + ")";
					n++;
				}
				
				for (int i = 0; i < n; i++) {
//					System.out.println("난이도 : 어려움");
					System.out.println((i+1) + "등 -> " + list[i]);
				}
				
				if (file.isFile() && file.canWrite()) {
					//write
					for (int i = 0; i < n; i++) {
						bufferedWriter.write(list[i]);
						bufferedWriter.newLine();
					}
					bufferedWriter.close();
				}
			}
		}catch (IOException e) { // 예외?, 오류? 잡기.
			System.out.println(e);
		}
		
	}
}
	// 입력된 파일을 불러와서 5등까지 출력해준다. --- 난이도별로