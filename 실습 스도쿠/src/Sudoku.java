import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

class MakeBoard
{
	// ������ ����� --- ��Ģ��� ���带 �迭 �� ��� �ѹ� �迭 �� �� ������ 3ĭ�� ������ ����.
	public static int[][] CreateBoard() {
		
		List<Integer> seed = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
		    seed.add(i);
		}
		Collections.shuffle(seed);
		
		int n1=seed.get(0) ,n2=seed.get(1), n3=seed.get(2), n4=seed.get(3), n5=seed.get(4), n6=seed.get(5), n7=seed.get(6), n8=seed.get(7), n9=seed.get(8);
		int[][] board = {{n1,n2,n3,n4,n5,n6,n7,n8,n9}
						,{n4,n5,n6,n7,n8,n9,n1,n2,n3}
						,{n7,n8,n9,n1,n2,n3,n4,n5,n6}
						,{n2,n3,n1,n5,n6,n4,n8,n9,n7}
						,{n5,n6,n4,n8,n9,n7,n2,n3,n1}
						,{n8,n9,n7,n2,n3,n1,n5,n6,n4}
						,{n3,n1,n2,n6,n4,n5,n9,n7,n8}
						,{n6,n4,n5,n9,n7,n8,n3,n1,n2}
						,{n9,n7,n8,n3,n1,n2,n6,n4,n5}
						};
		
		for(int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				System.out.print("| " + board[x][y] + " ");
			}
			System.out.println();
    	}
		System.out.println("-----------------------------------");
		
		// ���� ����.
		List<Integer> random = new ArrayList<>();
		for (int i=0; i<3;i++) {
			random.add(i);
		}
		
		for(int i=0; i<7; i+=3) {
			Collections.shuffle(random);
			int[] row_temp = board[i];
			board[i] = board[random.get(0)+i];
			board[random.get(0)+i] = board[random.get(1)+i];
			board[random.get(1)+i] = row_temp;
		}
		//���θ� �����.
//		for(int i=0; i<10; i++) {
//			board[i][0];
//		}
		
		
		
		
		System.out.println("���� ����");
		for(int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				System.out.print("| " + board[x][y] + " ");
			}
			System.out.println();
    	}
		
		
		return board;
	}

	// ���̵� �ޱ�
    public static int GetLevel(Scanner scan) {
    	String level = scan.next();
    	
    	while (level.equals("����") == false && level.equals("�����") == false) {
    		System.out.println("�ٽ� �Է����ּ���!");
    		level = scan.next();    		
    	}
    	
    	int holes = 0;
    	
    	if (level.equals("�����")) {
    		holes = 2;
    	}
    	else if (level.equals("����")) {
    		holes = 1;
    	}
    	
    	return holes;
    }
    
    // ���̵� �ޱ⿡�� ���� ���� ������ŭ ������ �մ� �Լ�
    public static int[][] MakeHoles(int[][] board,int holes) {
    	int [][] p_board = board;
    	int i,j,holes_num;
    	holes_num = holes;
    	while (holes_num>0) {
    		i = (int) (Math.random()*9);
    		j = (int) (Math.random()*9);
    		if(p_board[i][j] == 0) {
    			continue;
    		}
    		p_board[i][j] = 0;
    		holes_num--;
    	}
    	for(int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				System.out.print(p_board[x][y]+"  ");
			}
			System.out.println();
    	}
    	return p_board;
    }
    
    // ������ ���� ��ġ�� �������ִ� �Լ�.
    public static int[][] Holeset(int[][] p_board, int holes_num){
    	int[][] holeset = new int[2][holes_num];
    	int holes = holes_num;
	    for(int i=0; i<9; i++) {
	    	for(int j=0; j<9; j++) {
	    		if (p_board[i][j] == 0) {
	    			holeset[0][(holes_num-holes)] = i;
	    			holeset[1][(holes_num-holes)] = j;
	    			holes--;
	    		}
	    	}
	    }
    	return holeset;
    }
}

class CheckAnswer
{
	// ���� ���� 3x3�ڽ��� 1~9�� �ϳ����� �ִ��� üũ���ִ� �Լ�.
	public static boolean Grading(int[][] a_board, int row, int col, int answer) {
		// ����,���� Ȯ���ϱ�
		boolean a = true;
		for(int x=0; x<9;x++) {
			if (a_board[col][x] == answer || a_board[x][row] == answer) {
				System.out.println("fasle");
				a = false;
				return a;
			}
			else {
				a = true;
			}
		}
		// �ڽ� Ȯ��
		// 012345678
		// 012 --- 0�ǰ� 
		// 345 -- 3
		// 678 --- 6 
		// ---> 3���� ���� �� 3������
		int box_col = col/3*3;
		int box_row = row/3*3;
		for(int i = 0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if (a_board[box_col + i][box_row + j] == answer) {
					System.out.println("fasle");
					a = false;
					return a;
				}
				else {
					a = true;
				}
			}
		}
		System.out.println("true");
		return a;
	}
}
class Ranking
{
	// ���Ͽ� �÷��̾��� �г����� �޾Ƽ� ������ Ǭ �ð��ʿ� �Է����ش� --- ���̵� ����
	public static void WriteNicks(String nicks, float time, int holes) {
		try {
			if (holes == 1) {
				File file = new File("C:\\Users\\�� �� ��\\eclipse-workspace\\�ǽ� ������\\Ranking_easy.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
				if (file.isFile() && file.canWrite()) {
					//write
					bufferedWriter.append(time+"��, �г���:"+nicks);
					bufferedWriter.append("\r\n");
					bufferedWriter.close();
				}
			}
			else {
				File file2 = new File("C:\\Users\\�� �� ��\\eclipse-workspace\\�ǽ� ������\\Ranking_hard.txt");
				BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2,true));
				if (file2.isFile() && file2.canWrite()) {
					//write
					bufferedWriter2.append(time+"��, �г���:"+nicks);
					bufferedWriter2.append("\r\n");
					bufferedWriter2.close();
				}
			}
		}catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
	// �Էµ� ������ �ҷ��ͼ� 5����� ������ش�. --- ���̵�����
	public static void ReadNicks(int holes) {
		try {
			if (holes == 1) {
				//���� ��ü ����
				File file = new File("C:\\Users\\�� �� ��\\eclipse-workspace\\�ǽ� ������\\Ranking_easy.txt");
				//�Է� ��Ʈ�� ����
				FileReader filereader = new FileReader(file);
				//�Է� ���� ����
				BufferedReader bufReader = new BufferedReader(filereader);
				ArrayList<String> list = new ArrayList<String>();
				String line = "";
				int count = 5;
				// time ũ�� �� ��� ��� 1�� 2��... �ٿ��� --- 5������� ���.
				while((line = bufReader.readLine()) != null && count>0) {
					list.add(line);
				}
				Collections.sort(list);
				if (list.size() > 5) {
					for(int i=0; i<5; i++) {
						String[] info = list.get(i).split(", ");
						System.out.println("���̵� ��-> " + (i+1) +"��  " + info[1] + " " + info[0]);
					}
				}
				else {
					for(int i=0; i<list.size(); i++) {
						String[] info = list.get(i).split(", ");
						System.out.println("���̵� ��-> " + (i+1) +"��  " + info[1] + " " + info[0]);
					}
				}
				bufReader.close();
			}
			else {
				//���� ��ü ����
				File file2 = new File("C:\\Users\\�� �� ��\\eclipse-workspace\\�ǽ� ������\\Ranking_hard.txt");
				//�Է� ��Ʈ�� ����
				FileReader filereader2 = new FileReader(file2);
				//�Է� ���� ����
				BufferedReader bufReader2 = new BufferedReader(filereader2);
				ArrayList<String> list2 = new ArrayList<String>();
				String line = "";
				int count = 5;
				// time ũ�� �� ��� ��� 1�� 2��... �ٿ��� --- 5������� ���.
				while((line = bufReader2.readLine()) != null && count>0) {
					list2.add(line);
				}
				Collections.sort(list2);
				if (list2.size() > 5) {
					for(int i=0; i<5; i++) {
						String[] info2 = list2.get(i).split(", ");
						System.out.println("���̵� ��-> " + (i+1) +"��  " + info2[1] + " " + info2[0]);
					}
				}
				else {
					for(int i=0; i<list2.size(); i++) {
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

public class Sudoku {
	// ������ �÷��� �ϴ� �Լ�. ���� �Է°��� ����
	public static void main(String[] args) {
		MakeBoard.CreateBoard();
//		Scanner sol = new Scanner(System.in);
//		int[][] board = MakeBoard.CreateBoard();
//		System.out.println("���̵��� �������ּ���. (����/�����)");
//		int holes_num = MakeBoard.GetLevel(sol);
//		System.out.println(holes_num);
//		int holes = holes_num;
//		int[][] p_board = MakeBoard.MakeHoles(board,holes_num);
//		int[][] holeset = MakeBoard.Holeset(p_board,holes_num);
//		for (int j=0; j<holes_num; j++) {
//			System.out.println("��ĭ�� ��ġ�� ("+holeset[0][j] + "," + holeset[1][j] + ")");
//		}
//		int col, row, answer;
//		long start_time, end_time, time;
//		start_time = System.currentTimeMillis();
//		while (holes > 0) {
//			System.out.println("���θ� �Է��� �ּ���(1~9)");
//			col = sol.nextInt();
//			System.out.println("���θ� �Է��� �ּ���(1~9)");
//			row = sol.nextInt();
//			if (p_board[col-1][row-1] != 0) {
//				System.out.println("�� ���� ��ĭ�� �ƴմϴ�.");
//				continue;
//			}
//			System.out.println("������ �Է��ϼ���.");
//			answer = sol.nextInt();
//			if (CheckAnswer.Grading(p_board, row-1, col-1, answer) == true) {
//				p_board[col-1][row-1] = answer;
//				holes--;
//			}
//			for(int x=0; x<9; x++) {
//				for (int y=0; y<9; y++) {
//					System.out.print(p_board[x][y]+"  ");
//				}
//				System.out.println();
//	    	}
//		}
//		end_time = System.currentTimeMillis();
//		time = end_time - start_time;
//		System.out.println(time/1000.0f + "��");
//		System.out.println("�г����� �Է��� �ּ���.");
//		String nicks = sol.next();
//		Ranking.WriteNicks(nicks, time/1000.0f,holes_num);
//		Ranking.ReadNicks(holes_num);
		
	}
}
