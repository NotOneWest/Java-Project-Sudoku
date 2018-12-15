package sudoku;

import java.util.Scanner;

public class Sudoku {
	// 스도쿠 플레이 하는 함수. 정답 입력과정 포함
	public static void main(String[] args) throws Exception {
		Scanner sol = new Scanner(System.in);
		int[][] board = MakeBoard.CreateBoard(); // 보드 생성.
		int[][] a_board = new int[9][9];
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				a_board[x][y]=board[x][y];
			}
		}

		System.out.print("난이도를 선택해주세요. (easy/hard)"); // 난이도 받기.
		int holes_num = MakeBoard.GetLevel(sol);
		int holes = holes_num;
		System.out.println(holes_num);
		int[][] p_board = MakeBoard.MakeHoles(board,holes_num); // 빈칸이 있는 문제 보드 받기.
		int[][] h_board = new int[9][9];
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				h_board[x][y]=p_board[x][y];
			}
		}
		MakeBoard.ShowBoard(p_board,h_board);
		int col, row, answer, change;
		long start_time, end_time, time;
		start_time = System.currentTimeMillis(); // 시간초 흘러가기 시착.
		while (holes > 0) {
			// 가로 세로 좌표 받기.
			if(holes_num > holes) {
				System.out.println("빈칸을 수정하시겠습니까? (yes: 1/ no: 0)");
				change = sol.nextInt();
				if(change == 1) {
					System.out.println("세로를 입력해 주세요(1~9)");
					col = sol.nextInt();
					if (col>9) {
						System.out.println("다시 입력해 주세요.");
						col = sol.nextInt();
					}
					System.out.println("가로를 입력해 주세요(1~9)");
					row = sol.nextInt();
					if (row>9) {
						System.out.println("다시 입력해 주세요.");
						row = sol.nextInt();
					}
					if(h_board[col-1][row-1] != 0) {
						System.out.println("그 곳은 빈칸이 아닙니다.");
						continue;
					}
					System.out.println("수정할 값을 입력해 주세요.");
					answer = sol.nextInt();
					if (CheckAnswer.Grading(p_board, row-1, col-1, answer) == true) {
						p_board[col-1][row-1] = answer;
					}
				}
			}
			System.out.println("세로를 입력해 주세요(1~9)");
			col = sol.nextInt();
			if (col>9) {
				System.out.println("다시 입력해 주세요.");
				col = sol.nextInt();
			}
			System.out.println("가로를 입력해 주세요(1~9)");
			row = sol.nextInt();
			if (row>9) {
				System.out.println("다시 입력해 주세요.");
				row = sol.nextInt();
			}
			// 빈칸인지 체크.
			if (p_board[col-1][row-1] != 0) {
				System.out.println("그 곳은 빈칸이 아닙니다.");
				continue;
			}
			// 정답 입력.
			System.out.println("정답을 입력하세요.");
			answer = sol.nextInt();
			// 정답을 체크해준다.
			if (CheckAnswer.Grading(p_board, row-1, col-1, answer) == true) {
				p_board[col-1][row-1] = answer;
				holes--;
			}
			// 보드를 계속 프린트 해줌. 빈칸이 채워주는 것 보여줌.
			MakeBoard.ShowBoard(p_board,h_board);
		}
		end_time = System.currentTimeMillis();// 시간초 종료.
		time = end_time - start_time; // 시간초 게산.
		System.out.println(time/1000.0f + " 초"); 
		System.out.println("닉네임을 입력해 주세요.");
		String nicks = sol.next();
		Ranking.WriteAndReadNicks(nicks, time/1000.0f, holes_num); // 시간초 저장.
	}
}