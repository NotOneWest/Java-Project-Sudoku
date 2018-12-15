package sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Ranking
{
	// ���Ͽ� �÷��̾��� �г����� �޾Ƽ� ������ Ǭ �ð��ʿ� �Է����ش� --- ���̵� ����
	public static void WriteAndReadNicks(String nicks, float time, int holes) {
		try {
			if (holes == 21) { // ���̵� �����϶�.
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
					String info[] = list[i].split("��, ");
					if (Float.parseFloat(info[0]) > time) {
						if (n < 5) list[n] = list[n-1];
						for (int j = n-1; j > i; j--) {
							list[j] = list[j-1];
						}
						list[i] = time + "��, (�г���:" + nicks + ")";
						if (n<5) n++;
						break;
					}
				}
				
				if (n == 0) {
					list[0] = time + "��, (�г���:" + nicks + ")";
					n++;
				}
				System.out.println("���̵� : ����");
				for (int i = 0; i < n; i++) {
					System.out.println((i+1) + "�� -> " + list[i]);
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
			else {// ���̵� ������϶�.
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
					String info[] = list[i].split("��, ");
					if (Float.parseFloat(info[0]) > time) {
						if (n < 5) list[n] = list[n-1];
						for (int j = n-1; j > i; j--) {
							list[j] = list[j-1];
						}
						list[i] = time + "��, (�г���:" + nicks + ")";
						if (n<5) n++;
						break;
					}
				}
				
				if (n == 0) {
					list[0] = time + "��, (�г���:" + nicks + ")";
					n++;
				}
				
				for (int i = 0; i < n; i++) {
//					System.out.println("���̵� : �����");
					System.out.println((i+1) + "�� -> " + list[i]);
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
		}catch (IOException e) { // ����?, ����? ���.
			System.out.println(e);
		}
		
	}
}
	// �Էµ� ������ �ҷ��ͼ� 5����� ������ش�. --- ���̵�����