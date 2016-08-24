package de.affinitas.sudoku.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Named;

import de.affinitas.sudoku.vo.SudokuBoard;

/**
 * Class created to persist the information 
 * */
@Named
public class Store {
	
	Map<Long, SudokuBoard> database = new ConcurrentHashMap<>();

	public void addToDatabase(long id, SudokuBoard sudokuBoard) {
		database.put(Long.valueOf(id), sudokuBoard);
	}

	public SudokuBoard getSudoku(Long id) {
		return database.get(id);
	}
}