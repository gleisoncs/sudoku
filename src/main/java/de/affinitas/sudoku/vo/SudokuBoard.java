package de.affinitas.sudoku.vo;

public class SudokuBoard {

	private long id;
	private int size;
	private int difficultyLevel;
	private int[][] board;

	public SudokuBoard(int difficultyLevel, int size) {
		this.difficultyLevel = difficultyLevel;
		this.size = size;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public void setValueOnBoard(int x, int y, int number){
		this.board[x][y] = number;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (byte row = 0; row < board.length; row++) {
			for (byte col = 0; col < board.length; col++) {
				sb.append(board[row][col] + ",");
			}
		}
		return sb.substring(0, sb.length() - 1);
	}

}