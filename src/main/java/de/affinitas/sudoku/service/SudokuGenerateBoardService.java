package de.affinitas.sudoku.service;

import javax.inject.Inject;
import javax.inject.Named;

import de.affinitas.sudoku.dao.Store;
import de.affinitas.sudoku.utils.IdGenerator;
import de.affinitas.sudoku.vo.SudokuBoard;

@Named
public class SudokuGenerateBoardService {
	
	@Inject
	Store store;
	
	/**
	 * <p>Method generates a new fixed board.</p>
	 * 
	 * It's return a array of integers and the main goal is form a sudoku 
	 * of Cartesian Coordinates to the front-end.
	 * 
	 * */
	public SudokuBoard generateSudoku(int difficultyLevel, int size) {
        SudokuBoard board = new SudokuBoard(difficultyLevel, size);
        board.setId(IdGenerator.getUniqueLongId());
        board.setBoard(getSudokuData());
        store.addToDatabase(board.getId(), board);
        return board;
	}

	private int[][] getSudokuData() {
		int exampleSudoku[][] = new int[][] { 
			{ 7, 0, 0, 0, 4, 0, 5, 3, 0 }, 
			{ 0, 0, 5, 0, 0, 8, 0, 1, 0 },
			{ 0, 0, 8, 5, 0, 9, 0, 4, 0 }, 
			{ 5, 3, 9, 0, 6, 0, 0, 0, 1 }, 
			{ 0, 0, 0, 0, 1, 0, 0, 0, 5 },
			{ 8, 0, 0, 7, 2, 0, 9, 0, 0 }, 
			{ 9, 0, 7, 4, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 5, 7, 0, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 5, 0 } };
		return exampleSudoku;
	}
}
