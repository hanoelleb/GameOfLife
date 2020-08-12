package application;

public class Board {
	//EMPTY is 0, LIVE is 
	final int EMPTY = 0;
	final int LIVE = 1;
	final int DEAD = 2;
	
	private int[][] board;
	
	Board(int size) {
		board = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	public void setCellToDead(int row, int col) {
		board[row][col] = DEAD;
	}
	
	public void setCellToLive(int row, int col) {
		board[row][col] = LIVE;
	}
	
	public void setCellToEmpty(int row, int col) {
		board[row][col] = EMPTY;
	}
}
