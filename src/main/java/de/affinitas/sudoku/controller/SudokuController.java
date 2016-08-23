/*
 * Copyright (C) 2016 The Affinitas Expert Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.affinitas.sudoku.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.affinitas.sudoku.exceptions.SudokuException;
import de.affinitas.sudoku.service.SudokuService;
import de.affinitas.sudoku.vo.MoveValidator;
import de.affinitas.sudoku.vo.SudokuBoard;

/**
 * SudokuController is The main class that receives a call from front end.
 * 
 * <p>The class has four methods <b>index()</b>, <br>
 * getRandomBoard()</b>, <b>validateMove</b> and <b>handleException</b>.</p>
 * 
 * Method index just is expected calling if you decide to put front end level
 * inside this application.
 * 
 * 
 * @author  Gleison Caetano
 * @see SudokuBoard
 * @see MoveValidator
 */
@RestController
public class SudokuController {

	@Inject
	SudokuService sudokuService;

	/**
	 * <p>Method responsible to a introduction about software app.</p>
	 * 
	 * */
	
	@RequestMapping("/")
	public String index() {
		return "Welcome to Sudoku Affinitas";
	}

	/**
	 * <p>Method brings the fixed board as the example, it brings a EASY challenge.</p>
	 * 
	 * */
	@RequestMapping(value = "/getboard/{difficultyLevel}/{size}", method = RequestMethod.GET)
	public SudokuBoard getboard(@PathVariable int difficultyLevel, @PathVariable int size) {
		return sudokuService.getSudokuBoard(difficultyLevel, size);
	}

	/**
	 * <p>Validate the move of operator at front-end call. The movement is validate by a range of validations.</p>
	 * 
	 * */
	@RequestMapping(value = "/makemove/{id}/{x}/{y}/{number}", method = RequestMethod.PUT)
	public MoveValidator makeMove(@PathVariable("id") long id, @PathVariable("x") int x, @PathVariable("y") int y, @PathVariable("number") int number) throws SudokuException {
		return sudokuService.makeMove(id, x, y, number);
	}
	
	/**
	 * <p>Delete a movement occurred older times. It's necessary execute when some position wasn't correct.</p>
	 * 
	 * */
	@RequestMapping(value = "/deletemove/{id}/{x}/{y}", method = RequestMethod.DELETE)
	public MoveValidator deleteMove(@PathVariable("id") long id, @PathVariable("x") int x, @PathVariable("y") int y, @PathVariable("number") int number) throws SudokuException {
		return sudokuService.deleteMove(id, x, y);
	}

}
