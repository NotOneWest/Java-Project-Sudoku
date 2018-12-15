package sudoku;

import java.util.Scanner;

public class Sudoku {
	// ������ �÷��� �ϴ� �Լ�. ���� �Է°��� ����
	public static void main(String[] args) throws Exception {
		Scanner sol = new Scanner(System.in);
		int[][] board = MakeBoard.CreateBoard(); // ���� ����.
		int[][] a_board = new int[9][9];
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				a_board[x][y]=board[x][y];
			}
		}

		System.out.print("���̵��� �������ּ���. (easy/hard)"); // ���̵� �ޱ�.
		int holes_num = MakeBoard.GetLevel(sol);
		int holes = holes_num;
		System.out.println(holes_num);
		int[][] p_board = MakeBoard.MakeHoles(board,holes_num); // ��ĭ�� �ִ� ���� ���� �ޱ�.
		int[][] h_board = new int[9][9];
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				h_board[x][y]=p_board[x][y];
			}
		}
		MakeBoard.ShowBoard(p_board,h_board);
		int col, row, answer, change;
		long start_time, end_time, time;
		start_time = System.currentTimeMillis(); // �ð��� �귯���� ����.
		while (holes > 0) {
			// ���� ���� ��ǥ �ޱ�.
			if(holes_num > holes) {
				System.out.println("��ĭ�� �����Ͻðڽ��ϱ�? (yes: 1/ no: 0)");
				change = sol.nextInt();
				if(change == 1) {
					System.out.println("���θ� �Է��� �ּ���(1~9)");
					col = sol.nextInt();
					if (col>9) {
						System.out.println("�ٽ� �Է��� �ּ���.");
						col = sol.nextInt();
					}
					System.out.println("���θ� �Է��� �ּ���(1~9)");
					row = sol.nextInt();
					if (row>9) {
						System.out.println("�ٽ� �Է��� �ּ���.");
						row = sol.nextInt();
					}
					if(h_board[col-1][row-1] != 0) {
						System.out.println("�� ���� ��ĭ�� �ƴմϴ�.");
						continue;
					}
					System.out.println("������ ���� �Է��� �ּ���.");
					answer = sol.nextInt();
					if (CheckAnswer.Grading(p_board, row-1, col-1, answer) == true) {
						p_board[col-1][row-1] = answer;
					}
				}
			}
			System.out.println("���θ� �Է��� �ּ���(1~9)");
			col = sol.nextInt();
			if (col>9) {
				System.out.println("�ٽ� �Է��� �ּ���.");
				col = sol.nextInt();
			}
			System.out.println("���θ� �Է��� �ּ���(1~9)");
			row = sol.nextInt();
			if (row>9) {
				System.out.println("�ٽ� �Է��� �ּ���.");
				row = sol.nextInt();
			}
			// ��ĭ���� üũ.
			if (p_board[col-1][row-1] != 0) {
				System.out.println("�� ���� ��ĭ�� �ƴմϴ�.");
				continue;
			}
			// ���� �Է�.
			System.out.println("������ �Է��ϼ���.");
			answer = sol.nextInt();
			// ������ üũ���ش�.
			if (CheckAnswer.Grading(p_board, row-1, col-1, answer) == true) {
				p_board[col-1][row-1] = answer;
				holes--;
			}
			// ���带 ��� ����Ʈ ����. ��ĭ�� ä���ִ� �� ������.
			MakeBoard.ShowBoard(p_board,h_board);
		}
		end_time = System.currentTimeMillis();// �ð��� ����.
		time = end_time - start_time; // �ð��� �Ի�.
		System.out.println(time/1000.0f + " ��"); 
		System.out.println("�г����� �Է��� �ּ���.");
		String nicks = sol.next();
		Ranking.WriteAndReadNicks(nicks, time/1000.0f, holes_num); // �ð��� ����.
	}
}