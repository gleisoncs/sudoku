package de.affinitas.sudoku.service;

import javax.inject.Inject;
import javax.inject.Named;

import de.affinitas.sudoku.dao.Store;
import de.affinitas.sudoku.exceptions.SudokuException;
import de.affinitas.sudoku.utils.Constants;
import de.affinitas.sudoku.utils.IdGenerator;
import de.affinitas.sudoku.utils.SudokuHelper;
import de.affinitas.sudoku.vo.MoveValidator;
import de.affinitas.sudoku.vo.SudokuBoard;

@Named
public class SudokuService {

    @Inject
    Store database;

    public Store getDatabase() {
        return database;
    }
    
    /**
     * <p>Class responsible to generate generate the fixed or random board and save 
     * all games in in memory `database`.</p>
     * 
     * <p>The option difficulty and size will be implement in a version 2.0</p>
     * 
     * <p>Param <b>difficulty</b> means what is the difficulty of game</p>
     * <p>Param <b>size</b> means what is the difficulty of game</p> 
     * 
     * @author Gleison Caetano
     * @see SudokuBoard
     * @see SudokuHelper
     * 
     * */
    public SudokuBoard getBoard(int difficultyLevel, int size) {
        SudokuBoard board = new SudokuBoard(difficultyLevel, size);
        
        board.setId(IdGenerator.getUniqueLongId());
        board.setBoard(SudokuHelper.generateFixedNumbersForSudokuFromExample());
        
        database.addToDatabase(board.getId(), board);
        
        return board;
    }

    /**
     * <p>validateMovie method recover the Sudoku game from the 'Database'. This method validates the move from operator.</p>
     * 
     * <p>Attributes</p>
     * <b>long id<b> is the uniqueId generated from sudoku board
     * <b>int x<b> means the position 'X' based on Cartesian Coordinates (<b>X</b>,Y)
     * <b>int y<b> means the position 'Y' based on Cartesian Coordinates (X, <b>Y</b>)
     * <b>int number<b> indicates the number received by operator on board in front end application
     * 
     * @see SudokuBoard
     * @see MoveValidator
     * @see SudokuHelper
     * 
     * @param id
     * @param x
     * @param y
     * @param number
     *  
     * */
    public MoveValidator validateMove(long id, int x, int y, int number) throws SudokuException {
        SudokuBoard board =  database.getSudoku(id);
        if(board == null) throw new SudokuException(Constants.GENERIC_ERROR_CODE, "Oops, wrong Id, check and submit again.");
        MoveValidator result = SudokuHelper.updateAndValidateMove(x, y, number, board);
        return result;
    }
}