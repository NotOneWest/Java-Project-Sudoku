package sudoku;

import java.util.Scanner;

public class Sudoku {
	// 스도쿠 플레이 하는 함수. 정답 입력과정 포함
	public static void main(String[] args) {
		Scanner sol = new Scanner(System.in);
		int[][] board = MakeBoard.CreateBoard(); // 보드 생성.
		System.out.println("난이도를 선택해주세요. (쉬움/어려움)"); // 난이도 받기.
		int holes_num = MakeBoard.GetLevel(sol);
		int holes = holes_num;
		int[][] p_board = MakeBoard.MakeHoles(board,holes_num); // 빈칸이 있는 문제 보드 받기.
		int[][] holeset = MakeBoard.Holeset(p_board,holes_num); // 빈칸의 위치 받아오기.
		for (int j=0; j<holes_num; j++) {
			System.out.println("빈칸의 위치는 ("+holeset[0][j] + "," + holeset[1][j] + ")");
		}
		int col, row, answer;
		long start_time, end_time, time;
		start_time = System.currentTimeMillis(); // 시간초 흘러가기 시착.
		while (holes > 0) {
			// 가로 세로 좌표 받기.
			System.out.println("세로를 입력해 주세요(1~9)");
			col = sol.nextInt();
			System.out.println("가로를 입력해 주세요(1~9)");
			row = sol.nextInt();
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
			for(int x=0; x<9; x++) {
				for (int y=0; y<9; y++) {
					System.out.print(p_board[x][y]+"  ");
				}
				System.out.println();
	    	}
		}
		end_time = System.currentTimeMillis(); // 시간초 종료.
		time = end_time - start_time; // 시간초 게산.
		System.out.println(time/1000.0f + "초"); 
		System.out.println("닉네임을 입력해 주세요.");
		String nicks = sol.next();
		Ranking.WriteNicks(nicks, time/1000.0f,holes_num); // 시간초 저장.
		Ranking.ReadNicks(holes_num); // 랭킹 불러오기.
		
	}
}