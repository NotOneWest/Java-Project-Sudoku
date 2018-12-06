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
	// 보드판 만들기 --- 규칙대로 보드를 배열 후 섞어서 한번 배열 그 후 가로줄 3칸씩 나누어 섞음.
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
		
		// 가로 섞기.
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
		//세로를 섞어보자.
//		for(int i=0; i<10; i++) {
//			board[i][0];
//		}
		
		
		
		
		System.out.println("가로 섞음");
		for(int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				System.out.print("| " + board[x][y] + " ");
			}
			System.out.println();
    	}
		
		
		return board;
	}

	// 난이도 받기
    public static int GetLevel(Scanner scan) {
    	String level = scan.next();
    	
    	while (level.equals("쉬움") == false && level.equals("어려움") == false) {
    		System.out.println("다시 입력해주세요!");
    		level = scan.next();    		
    	}
    	
    	int holes = 0;
    	
    	if (level.equals("어려움")) {
    		holes = 2;
    	}
    	else if (level.equals("쉬움")) {
    		holes = 1;
    	}
    	
    	return holes;
    }
    
    // 난이도 받기에서 받은 구멍 개수만큼 구멍을 뚫는 함수
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
    
    // 구멍을 뚫은 위치를 저장해주는 함수.
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
	// 가로 세로 3x3박스에 1~9가 하나씩만 있는지 체크해주는 함수.
	public static boolean Grading(int[][] a_board, int row, int col, int answer) {
		// 가로,세로 확인하기
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
		// 박스 확인
		// 012345678
		// 012 --- 0되고 
		// 345 -- 3
		// 678 --- 6 
		// ---> 3으로 나눈 몫에 3곱해줌
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
	// 파일에 플레이어의 닉네임을 받아서 스도쿠를 푼 시간초와 입력해준다 --- 난이도 별로
	public static void WriteNicks(String nicks, float time, int holes) {
		try {
			if (holes == 1) {
				File file = new File("C:\\Users\\안 한 서\\eclipse-workspace\\실습 스도쿠\\Ranking_easy.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
				if (file.isFile() && file.canWrite()) {
					//write
					bufferedWriter.append(time+"초, 닉네임:"+nicks);
					bufferedWriter.append("\r\n");
					bufferedWriter.close();
				}
			}
			else {
				File file2 = new File("C:\\Users\\안 한 서\\eclipse-workspace\\실습 스도쿠\\Ranking_hard.txt");
				BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2,true));
				if (file2.isFile() && file2.canWrite()) {
					//write
					bufferedWriter2.append(time+"초, 닉네임:"+nicks);
					bufferedWriter2.append("\r\n");
					bufferedWriter2.close();
				}
			}
		}catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
	// 입력된 파일을 불러와서 5등까지 출력해준다. --- 난이도별로
	public static void ReadNicks(int holes) {
		try {
			if (holes == 1) {
				//파일 객체 생성
				File file = new File("C:\\Users\\안 한 서\\eclipse-workspace\\실습 스도쿠\\Ranking_easy.txt");
				//입력 스트림 생성
				FileReader filereader = new FileReader(file);
				//입력 버퍼 생성
				BufferedReader bufReader = new BufferedReader(filereader);
				ArrayList<String> list = new ArrayList<String>();
				String line = "";
				int count = 5;
				// time 크기 순 대로 출력 1등 2등... 붙여서 --- 5등까지만 출력.
				while((line = bufReader.readLine()) != null && count>0) {
					list.add(line);
				}
				Collections.sort(list);
				if (list.size() > 5) {
					for(int i=0; i<5; i++) {
						String[] info = list.get(i).split(", ");
						System.out.println("난이도 하-> " + (i+1) +"등  " + info[1] + " " + info[0]);
					}
				}
				else {
					for(int i=0; i<list.size(); i++) {
						String[] info = list.get(i).split(", ");
						System.out.println("난이도 하-> " + (i+1) +"등  " + info[1] + " " + info[0]);
					}
				}
				bufReader.close();
			}
			else {
				//파일 객체 생성
				File file2 = new File("C:\\Users\\안 한 서\\eclipse-workspace\\실습 스도쿠\\Ranking_hard.txt");
				//입력 스트림 생성
				FileReader filereader2 = new FileReader(file2);
				//입력 버퍼 생성
				BufferedReader bufReader2 = new BufferedReader(filereader2);
				ArrayList<String> list2 = new ArrayList<String>();
				String line = "";
				int count = 5;
				// time 크기 순 대로 출력 1등 2등... 붙여서 --- 5등까지만 출력.
				while((line = bufReader2.readLine()) != null && count>0) {
					list2.add(line);
				}
				Collections.sort(list2);
				if (list2.size() > 5) {
					for(int i=0; i<5; i++) {
						String[] info2 = list2.get(i).split(", ");
						System.out.println("난이도 상-> " + (i+1) +"등  " + info2[1] + " " + info2[0]);
					}
				}
				else {
					for(int i=0; i<list2.size(); i++) {
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

public class Sudoku {
	// 스도쿠 플레이 하는 함수. 정답 입력과정 포함
	public static void main(String[] args) {
		MakeBoard.CreateBoard();
//		Scanner sol = new Scanner(System.in);
//		int[][] board = MakeBoard.CreateBoard();
//		System.out.println("난이도를 선택해주세요. (쉬움/어려움)");
//		int holes_num = MakeBoard.GetLevel(sol);
//		System.out.println(holes_num);
//		int holes = holes_num;
//		int[][] p_board = MakeBoard.MakeHoles(board,holes_num);
//		int[][] holeset = MakeBoard.Holeset(p_board,holes_num);
//		for (int j=0; j<holes_num; j++) {
//			System.out.println("빈칸의 위치는 ("+holeset[0][j] + "," + holeset[1][j] + ")");
//		}
//		int col, row, answer;
//		long start_time, end_time, time;
//		start_time = System.currentTimeMillis();
//		while (holes > 0) {
//			System.out.println("세로를 입력해 주세요(1~9)");
//			col = sol.nextInt();
//			System.out.println("가로를 입력해 주세요(1~9)");
//			row = sol.nextInt();
//			if (p_board[col-1][row-1] != 0) {
//				System.out.println("그 곳은 빈칸이 아닙니다.");
//				continue;
//			}
//			System.out.println("정답을 입력하세요.");
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
//		System.out.println(time/1000.0f + "초");
//		System.out.println("닉네임을 입력해 주세요.");
//		String nicks = sol.next();
//		Ranking.WriteNicks(nicks, time/1000.0f,holes_num);
//		Ranking.ReadNicks(holes_num);
		
	}
}
