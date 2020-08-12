package application;

import java.util.Arrays;

public class Board {
	// EMPTY is 0, LIVE is
	final int LIVE = 1;
	final int DEAD = 0;

	final int SIZE;

	private int[][] board;

	Board(int size) {
		SIZE = size;
		board = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = DEAD;
			}
		}
	}

	public int getCell(int i, int j) {
		return this.board[i][j];
	}

	public void setCellToDead(int row, int col) {
		this.board[row][col] = DEAD;
	}

	public void setCellToLive(int row, int col) {
		this.board[row][col] = LIVE;
	}

	public void update() {
		final int[][] current = new int[SIZE][];
		for (int i = 0; i < SIZE; i++) {
			current[i] = Arrays.copyOf(this.board[i], SIZE);
		}
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				int status = board[i][j];

				// CHECK ADJACENT CELLS
				int liveNeighbors = 0;
				if (i > 1) {
					if (current[i - 1][j] == LIVE)
						liveNeighbors++;
					
					if (j > 1) {
						if (current[i - 1][j - 1] == LIVE)
							liveNeighbors++;
					}
					if (j < SIZE - 1) {
						if (current[i - 1][j + 1] == LIVE)
							liveNeighbors++;
					}
				}
				else if (i < SIZE - 1) {
					if (current[i + 1][j] == LIVE)
						liveNeighbors++;
					if (j > 1) {
						if (current[i + 1][j - 1] == LIVE)
							liveNeighbors++;
					}
					if (j < SIZE - 1) {
						if (current[i + 1][j + 1] == LIVE)
							liveNeighbors++;
					}
				}
				if (j > 1) {
					if (current[i][j - 1] == LIVE)
						liveNeighbors++;
				}
				else if (j < SIZE - 1) {
					if (current[i][j + 1] == LIVE)
						liveNeighbors++;
				}

				// ACTION DEPENDING ON NEIGHBORS
				if (status == DEAD && liveNeighbors == 3 || status == LIVE && liveNeighbors == 2
						|| status == LIVE && liveNeighbors == 3) {
					this.board[i][j] = LIVE;
				} else {
					this.board[i][j] = DEAD;
				}
				;

			}
		}
	}
}
