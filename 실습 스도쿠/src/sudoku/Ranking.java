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
	// ���Ͽ� �÷��̾��� �г����� �޾Ƽ� ������ Ǭ �ð��ʿ� �Է����ش� --- ���̵� ����
	public static void WriteNicks(String nicks, float time, int holes) {
		try {
			if (holes == 1) { // ���̵� �����϶�.
				File file = new File("src/sudoku/Ranking_easy.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
				if (file.isFile() && file.canWrite()) {
					//write
					bufferedWriter.append(time+"��, �г���:"+nicks);
					bufferedWriter.append("\r\n");
					bufferedWriter.close();
				}
			}
			else {// ���̵� ������϶�.
				File file2 = new File("src/sudoku/Ranking_hard.txt"); // ���� ����.
				BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2,true)); // �ۼ����ִ� ��� ����.
				if (file2.isFile() && file2.canWrite()) {
					//write
					bufferedWriter2.append(time+"��, �г���:"+nicks);
					bufferedWriter2.append("\r\n");
					bufferedWriter2.close();
				}
			}
		}catch (IOException e) { // ����?, ����? ���.
			System.out.println(e);
		}
		
	}
	
	// �Էµ� ������ �ҷ��ͼ� 5����� ������ش�. --- ���̵�����
	public static void ReadNicks(int holes) {
		try {
			if (holes == 1) {
				//���� ����.
				File file = new File("src/sudoku/Ranking_easy.txt");
				//reader ����.
				FileReader filereader = new FileReader(file);
				// ���� ����.
				BufferedReader bufReader = new BufferedReader(filereader);
				ArrayList<String> list = new ArrayList<String>();
				String line = "";
				// time ũ�� �� ��� ��� 1�� 2��... �ٿ��� --- 5������� ���.
				while((line = bufReader.readLine()) != null) {
					list.add(line);
				}
				Collections.sort(list); // ����Ʈ Ÿ�� ������� ����.
				if (list.size() > 5) {
					for(int i=0; i<5; i++) {
						// �ð� �� ���� ������������ ���� �ٲ��ֱ�.
						String[] info = list.get(i).split(", ");
						System.out.println("���̵� ��-> " + (i+1) +"��  " + info[1] + " " + info[0]);
					}
				}
				else {
					for(int i=0; i<list.size(); i++) {
						// �ð� �� ���� ������������ ���� �ٲ��ֱ�.
						String[] info = list.get(i).split(", ");
						System.out.println("���̵� ��-> " + (i+1) +"��  " + info[1] + " " + info[0]);
					}
				}
				bufReader.close();
			}
			else {
				//���� ����
				File file2 = new File("src/sudoku/Ranking_hard.txt");
				//reader ����
				FileReader filereader2 = new FileReader(file2);
				//���� ����
				BufferedReader bufReader2 = new BufferedReader(filereader2);
				ArrayList<String> list2 = new ArrayList<String>();
				String line = "";
				// time ũ�� �� ��� ��� 1�� 2��... �ٿ��� --- 5������� ���.
				while((line = bufReader2.readLine()) != null) {
					list2.add(line);
				}
				Collections.sort(list2);
				if (list2.size() > 5) {
					for(int i=0; i<5; i++) {
						// �ð� �� ���� ������������ ���� �ٲ��ֱ�.
						String[] info2 = list2.get(i).split(", ");
						System.out.println("���̵� ��-> " + (i+1) +"��  " + info2[1] + " " + info2[0]);
					}
				}
				else {
					for(int i=0; i<list2.size(); i++) {
						// �ð� �� ���� ������������ ���� �ٲ��ֱ�.
						String[] info2 = list2.get(i).split(", ");
						System.out.println("���̵� ��-> " + (i+1) +"��  " + info2[1] + " " + info2[0]);
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