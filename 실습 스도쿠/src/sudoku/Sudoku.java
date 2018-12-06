package sudoku;

import java.util.Scanner;

public class Sudoku {
	// ������ �÷��� �ϴ� �Լ�. ���� �Է°��� ����
	public static void main(String[] args) {
		Scanner sol = new Scanner(System.in);
		int[][] board = MakeBoard.CreateBoard(); // ���� ����.
		System.out.println("���̵��� �������ּ���. (����/�����)"); // ���̵� �ޱ�.
		int holes_num = MakeBoard.GetLevel(sol);
		int holes = holes_num;
		int[][] p_board = MakeBoard.MakeHoles(board,holes_num); // ��ĭ�� �ִ� ���� ���� �ޱ�.
		int[][] holeset = MakeBoard.Holeset(p_board,holes_num); // ��ĭ�� ��ġ �޾ƿ���.
		for (int j=0; j<holes_num; j++) {
			System.out.println("��ĭ�� ��ġ�� ("+holeset[0][j] + "," + holeset[1][j] + ")");
		}
		int col, row, answer;
		long start_time, end_time, time;
		start_time = System.currentTimeMillis(); // �ð��� �귯���� ����.
		while (holes > 0) {
			// ���� ���� ��ǥ �ޱ�.
			System.out.println("���θ� �Է��� �ּ���(1~9)");
			col = sol.nextInt();
			System.out.println("���θ� �Է��� �ּ���(1~9)");
			row = sol.nextInt();
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
			for(int x=0; x<9; x++) {
				for (int y=0; y<9; y++) {
					System.out.print(p_board[x][y]+"  ");
				}
				System.out.println();
	    	}
		}
		end_time = System.currentTimeMillis(); // �ð��� ����.
		time = end_time - start_time; // �ð��� �Ի�.
		System.out.println(time/1000.0f + "��"); 
		System.out.println("�г����� �Է��� �ּ���.");
		String nicks = sol.next();
		Ranking.WriteNicks(nicks, time/1000.0f,holes_num); // �ð��� ����.
		Ranking.ReadNicks(holes_num); // ��ŷ �ҷ�����.
		
	}
}