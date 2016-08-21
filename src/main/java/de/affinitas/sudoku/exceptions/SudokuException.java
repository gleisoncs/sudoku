package de.affinitas.sudoku.exceptions;

public class SudokuException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final int statusCode;
	
	private final String statusMessage;

	public SudokuException(final int statusCode, final String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String toString() {
		return statusCode + statusMessage;
	}
}
