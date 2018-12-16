package Sudoku;

class CheckAnswer
{
	// 가로 세로 3x3박스에 1~9가 하나씩만 있는지 체크해주는 함수.
	public static boolean Grading(int[][] a_board, int row, int col, int answer) {
		// 가로,세로 확인하기
		boolean a = true;
		for(int x=0; x<9;x++) {
			if (a_board[col][x] == answer || a_board[x][row] == answer) {
				a = false;
			}
		}
		
		int box_col = col/3*3;
		int box_row = row/3*3;
		
		for(int i = 0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if (a_board[box_col + i][box_row + j] == answer) {
					a = false;
				}
			}
		}
		return a;
	}
}