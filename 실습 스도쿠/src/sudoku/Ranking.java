package sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Ranking
{
	// 파일에 플레이어의 닉네임을 받아서 스도쿠를 푼 시간초와 입력해준다 --- 난이도 별로
	public static void WriteNicks(String nicks, float time, int holes) {
		try {
			if (holes == 1) { // 난이도 쉬움일때.
				File file = new File("src/sudoku/Ranking_easy.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
				if (file.isFile() && file.canWrite()) {
					//write
					bufferedWriter.append(time+"초, 닉네임:"+nicks);
					bufferedWriter.append("\r\n");
					bufferedWriter.close();
				}
			}
			else {// 난이도 어려움일때.
				File file2 = new File("src/sudoku/Ranking_hard.txt"); // 파일 열기.
				BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2,true)); // 작성해주는 기능 지정.
				if (file2.isFile() && file2.canWrite()) {
					//write
					bufferedWriter2.append(time+"초, 닉네임:"+nicks);
					bufferedWriter2.append("\r\n");
					bufferedWriter2.close();
				}
			}
		}catch (IOException e) { // 예외?, 오류? 잡기.
			System.out.println(e);
		}
		
	}
	
	// 입력된 파일을 불러와서 5등까지 출력해준다. --- 난이도별로
	public static void ReadNicks(int holes) {
		try {
			if (holes == 1) {
				//파일 지정.
				File file = new File("src/sudoku/Ranking_easy.txt");
				//reader 생성.
				FileReader filereader = new FileReader(file);
				// 버퍼 생성.
				BufferedReader bufReader = new BufferedReader(filereader);
				ArrayList<String> list = new ArrayList<String>();
				String line = "";
				// time 크기 순 대로 출력 1등 2등... 붙여서 --- 5등까지만 출력.
				while((line = bufReader.readLine()) != null) {
					list.add(line);
				}
				Collections.sort(list); // 리스트 타임 순서대로 정렬.
				if (list.size() > 5) {
					for(int i=0; i<5; i++) {
						// 시간 초 부터 나와있음으로 순서 바꿔주기.
						String[] info = list.get(i).split(", ");
						System.out.println("난이도 하-> " + (i+1) +"등  " + info[1] + " " + info[0]);
					}
				}
				else {
					for(int i=0; i<list.size(); i++) {
						// 시간 초 부터 나와있음으로 순서 바꿔주기.
						String[] info = list.get(i).split(", ");
						System.out.println("난이도 하-> " + (i+1) +"등  " + info[1] + " " + info[0]);
					}
				}
				bufReader.close();
			}
			else {
				//파일 생성
				File file2 = new File("src/sudoku/Ranking_hard.txt");
				//reader 생성
				FileReader filereader2 = new FileReader(file2);
				//버퍼 생성
				BufferedReader bufReader2 = new BufferedReader(filereader2);
				ArrayList<String> list2 = new ArrayList<String>();
				String line = "";
				// time 크기 순 대로 출력 1등 2등... 붙여서 --- 5등까지만 출력.
				while((line = bufReader2.readLine()) != null) {
					list2.add(line);
				}
				Collections.sort(list2);
				if (list2.size() > 5) {
					for(int i=0; i<5; i++) {
						// 시간 초 부터 나와있음으로 순서 바꿔주기.
						String[] info2 = list2.get(i).split(", ");
						System.out.println("난이도 상-> " + (i+1) +"등  " + info2[1] + " " + info2[0]);
					}
				}
				else {
					for(int i=0; i<list2.size(); i++) {
						// 시간 초 부터 나와있음으로 순서 바꿔주기.
						String[] info2 = list2.get(i).split(", ");
						System.out.println("난이도 상-> " + (i+1) +"등  " + info2[1] + " " + info2[0]);
					}
				}
				bufReader2.close();
			}
		}catch (FileNotFoundException e) {
			// TODO:handel exception
		}catch (IOException e) {
			System.out.println(e);
		}
		
	}
}