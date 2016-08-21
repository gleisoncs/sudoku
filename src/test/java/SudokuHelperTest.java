import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.affinitas.sudoku.utils.SudokuHelper;

public class SudokuHelperTest {

	@Test
	public void testBoardCreation() {
		int fakeBoard[][] = SudokuHelper.generateFixedNumbersForSudokuFromExample();
		int toltalNumbersOnBoard = 0;
		for (int x = 0; x < fakeBoard.length; x++)
			for (int y = 0; y < fakeBoard.length; y++)
				if (fakeBoard[x][y] == 0)
					toltalNumbersOnBoard++;
		assertTrue("Number of zeros in board", toltalNumbersOnBoard == 1);
	}

	@Test
	public void testIfMoveIsValid() {
		int fakeBoard[][] = SudokuHelper.generateFixedNumbersForSudokuFromExample();
		
//		assertEquals(SudokuHelper.isValidMove(5, 5, 5, fakeBoard), MoveValidator.VALID);
//		assertEquals(SudokuHelper.isValidMove(0, 0, 7, fakeBoard), MoveValidator.INVALID);
	}
}
