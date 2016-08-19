package de.affinitas.sudoku.controller;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.affinitas.sudoku.service.SudokuService;

/**
 * SudokuController is The main class that receives a call from front end.
 * 
 * <p>
 * The class has four methods <b>index()</b>, <br>
 * getRandomBoard()</b>, <b>validateMove</b> and <b>handleException</b>.
 * </p>
 * 
 * Method index just is expected calling if you decide to put front end level
 * inside this application.
 * 
 */
@RestController
public class SudokuController {

	@Inject
	SudokuService sudokuService;

	@RequestMapping("/")
	public String index() {
		return "Welcome Sudokuer";
	}
}
