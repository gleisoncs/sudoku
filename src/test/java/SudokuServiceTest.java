import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.affinitas.sudoku.dao.Store;
import de.affinitas.sudoku.exceptions.SudokuNotFoundException;
import de.affinitas.sudoku.service.SudokuGenerateBoardService;
import de.affinitas.sudoku.service.SudokuValidateService;
import de.affinitas.sudoku.utils.Constants;
import de.affinitas.sudoku.vo.MoveValidator;
import de.affinitas.sudoku.vo.SudokuBoard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SudokuGenerateBoardService.class,
		SudokuValidateService.class, Store.class })
public class SudokuServiceTest {

	private final int SIZE = 9;

	@Inject
	SudokuGenerateBoardService sudokuGenerateService;

	@Inject
	SudokuValidateService sudokuValidationService;

	@Test
	public void testBoardCreation() {
		int fakeBoard[][] = sudokuGenerateService.generateSudoku(Constants.EASY_LEVEL, SIZE).getBoard();
		int toltalNumbersOnBoard = 0;
		for (int x = 0; x < fakeBoard.length; x++)
			for (int y = 0; y < fakeBoard.length; y++)
				if (fakeBoard[x][y] == 0)
					toltalNumbersOnBoard++;
		assertTrue("Number of zeros in board", toltalNumbersOnBoard == 52);
	}

	@Test
	public void testIfMoveIsValid() throws SudokuNotFoundException {
		SudokuBoard board = sudokuGenerateService.generateSudoku(Constants.EASY_LEVEL, SIZE);
		assertEquals(sudokuValidationService.makeMove(board.getId(), 0, 1, 9), MoveValidator.VALID);
	}

	@Test
	public void testGetBoard() {
		SudokuBoard board = sudokuGenerateService.generateSudoku(Constants.EASY_LEVEL, SIZE);
		assertNotNull(board);
		assertEquals(board.getDifficultyLevel(), Constants.EASY_LEVEL);
		assertEquals(board.getSize(), SIZE);
		assertNotNull(board.getId());
	}

	@Test(expected = SudokuNotFoundException.class)
	public void testValidateMoveWithNonExistentId() throws SudokuNotFoundException {
		sudokuValidationService.makeMove(1234, 4, 5, 8);
	}

	@Test
	public void testAllValidateMoves() {
		SudokuBoard board = sudokuGenerateService.generateSudoku(Constants.EASY_LEVEL, SIZE);
		try {
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 0, 1, 9));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 0, 2, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 0, 3, 1));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 0, 5, 6));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 0, 8, 8));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 1, 0, 4));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 1, 1, 6));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 1, 3, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 1, 4, 3));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 1, 6, 7));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 1, 8, 9));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 2, 0, 3));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 2, 1, 1));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 2, 4, 7));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 2, 6, 6));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 2, 8, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 3, 3, 8));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 3, 5, 4));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 3, 6, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 3, 7, 7));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 4, 0, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 4, 1, 7));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 4, 2, 6));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 4, 3, 9));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 4, 5, 3));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 4, 6, 4));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 4, 7, 8));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 5, 1, 4));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 5, 2, 1));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 5, 5, 5));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 5, 7, 6));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 5, 8, 3));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 6, 1, 5));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 6, 4, 8));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 6, 5, 1));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 6, 6, 3));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 6, 7, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 6, 8, 6));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 7, 0, 1));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 7, 1, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 7, 2, 3));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 7, 3, 6));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 7, 6, 8));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 7, 7, 9));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 7, 8, 4));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 8, 1, 8));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 8, 2, 4));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 8, 3, 3));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 8, 4, 9));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 8, 5, 2));
			assertEquals(MoveValidator.VALID, sudokuValidationService.makeMove(board.getId(), 8, 6, 1));
			assertEquals(MoveValidator.COMPLETE, sudokuValidationService.makeMove(board.getId(), 8, 8, 7));
		} catch (SudokuNotFoundException e) {
			e.printStackTrace();
		}
	}
}