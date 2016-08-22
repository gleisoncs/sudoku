import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.affinitas.sudoku.dao.Store;
import de.affinitas.sudoku.exceptions.SudokuException;
import de.affinitas.sudoku.service.SudokuService;
import de.affinitas.sudoku.utils.Constants;
import de.affinitas.sudoku.vo.MoveValidator;
import de.affinitas.sudoku.vo.SudokuBoard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SudokuService.class, Store.class })
public class SudokuServiceTest {

	private final int SIZE = 9;

	@Autowired
	SudokuService sudokuService;

	@Test
	public void testGetBoard() {
		SudokuBoard board = sudokuService.getBoard(Constants.EASY_LEVEL, SIZE);
		assertNotNull(board);
		assertEquals(board.getDifficultyLevel(), Constants.EASY_LEVEL);
		assertEquals(board.getSize(), SIZE);
		assertNotNull(board.getId());
	}

	@Test(expected = SudokuException.class)
	public void testValidateMoveWithNonExistentId() throws SudokuException {
		sudokuService.validateMove(1234, 4, 5, 8);
	}

	@Test
	public void testAllValidateMoves() {
		SudokuBoard board = new SudokuBoard(Constants.EASY_LEVEL, SIZE);
		Long id = 8218078219143876788L;
		board.setId(id);
		board.setBoard(new int[][] { { 7, 0, 0, 0, 4, 0, 5, 3, 0 }, { 0, 0, 5, 0, 0, 8, 0, 1, 0 },
				{ 0, 0, 8, 5, 0, 9, 0, 4, 0 }, { 5, 3, 9, 0, 6, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 5 },
				{ 8, 0, 0, 7, 2, 0, 9, 0, 0 }, { 9, 0, 7, 4, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 5, 7, 0, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 5, 0 } });
		sudokuService.getDatabase().addToDatabase(id, board);
		try {
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 0, 1, 9));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 0, 2, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 0, 3, 1));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 0, 5, 6));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 0, 8, 8));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 1, 0, 4));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 1, 1, 6));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 1, 3, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 1, 4, 3));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 1, 6, 7));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 1, 8, 9));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 2, 0, 3));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 2, 1, 1));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 2, 4, 7));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 2, 6, 6));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 2, 8, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 3, 3, 8));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 3, 5, 4));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 3, 6, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 3, 7, 7));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 4, 0, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 4, 1, 7));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 4, 2, 6));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 4, 3, 9));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 4, 5, 3));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 4, 6, 4));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 4, 7, 8));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 5, 1, 4));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 5, 2, 1));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 5, 5, 5));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 5, 7, 6));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 5, 8, 3));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 6, 1, 5));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 6, 4, 8));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 6, 5, 1));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 6, 6, 3));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 6, 7, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 6, 8, 6));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 7, 0, 1));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 7, 1, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 7, 2, 3));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 7, 3, 6));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 7, 6, 8));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 7, 7, 9));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 7, 8, 4));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 8, 1, 8));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 8, 2, 4));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 8, 3, 3));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 8, 4, 9));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 8, 5, 2));
			assertEquals(MoveValidator.VALID, sudokuService.validateMove(id, 8, 6, 1));
			assertEquals(MoveValidator.COMPLETE, sudokuService.validateMove(id, 8, 8, 7));
		} catch (SudokuException e) {
			e.printStackTrace();
		}
	}
}
