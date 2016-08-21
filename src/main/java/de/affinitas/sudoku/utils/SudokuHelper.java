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
package de.affinitas.sudoku.utils;


import java.util.Arrays;

import de.affinitas.sudoku.vo.MoveValidator;
import de.affinitas.sudoku.vo.SudokuBoard;

/**
 * Class supports the operation of Controller
 * 
 * Methods generateFixedNumbersForSudokuFromExample, 
 * 
 * @author Gleison Caetano
 * @see 
 * @since 1.0
 * */
public class SudokuHelper {

	
	/**
	 * Method return a array of integers and the main goal is form a sudoku 
	 * of Cartesian Coordinates to the front-end.
	 * 
	 * */
	public static int[][] generateFixedNumbersForSudokuFromExample() {
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
//		int exampleSudoku[][] = new int[][] { 
//			{ 0, 9, 1, 1, 4, 6, 5, 3, 8 }, 
//			{ 4, 6, 5, 6, 3, 8, 7, 1, 9 },
//			{ 3, 1, 8, 5, 7, 9, 6, 4, 2 }, 
//			{ 5, 3, 9, 8, 6, 4, 2, 7, 1 }, 
//			{ 2, 7, 6, 9, 1, 3, 4, 8, 5 },
//			{ 8, 4, 1, 7, 2, 5, 9, 6, 3 }, 
//			{ 9, 5, 7, 4, 8, 1, 3, 2, 6 }, 
//			{ 1, 2, 3, 6, 5, 7, 8, 9, 4 },
//			{ 6, 8, 4, 3, 9, 2, 1, 5, 7 } };
		return exampleSudoku;
	}

	public static MoveValidator updateAndValidateMove(int x, int y, int number, SudokuBoard board) {
		boolean result = isValidNumberInPosition(x, y, number, board.getBoard());

		if (result) {
			SudokuHelper.updateMove(x, y, number, board);
			
			if (isBoardSolvedCompletly(board.getBoard()))
				return MoveValidator.COMPLETE;
			else
				return MoveValidator.VALID;
		} else
			return MoveValidator.INVALID;
	}

	private static boolean isValidNumberInPosition(int x, int y, int number, int[][] board) {
		if (checkPositionDifZero(x, y, board))          return false;
		if (checkRowContainNumber(x, number, board))    return false;
		if (checkColumnContainNumber(y, number, board)) return false;
		if (checkBoxContainNumber(x, y, number, board)) return false;
		return true;
	}

	private static boolean checkPositionDifZero(int x, int y, int[][] board) {
		return board[x][y] != 0;
	}

	private static boolean isBoardSolvedCompletly(int[][] board) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				if (board[x][y] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkRowContainNumber(int x, int number, int[][] a) {
		for (int column = 0; column <= 8; column++) {
			if (a[x][column] == number) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkColumnContainNumber(int y, int number, int[][] a) {
		for (int row = 0; row <= 8; row++) {
			if (a[row][y] == number) {
				return true;
			}
		}

		return false;
	}

	public static boolean checkBoxContainNumber(int x, int y, int number, int[][] board) {
		int quadrantX[] = new int[3];
		int quadrantY[] = new int[3];
        switch(x) {
            case 0: case 1: case 2: quadrantX = new int[]{1,2,3}; break;
            case 3: case 4: case 5: quadrantX = new int[]{4,5,6}; break;
            case 6: case 7: case 8: quadrantX = new int[]{7,8,9}; break;
        }
        switch(y) {
            case 0: case 1: case 2: quadrantY = new int[]{1,4,7}; break;
            case 3: case 4: case 5: quadrantY = new int[]{2,5,8}; break;
            case 6: case 7: case 8: quadrantY = new int[]{3,6,9}; break;
        }
        
        int boxNumber = getQuadrantNumber(quadrantX, quadrantY);
        int[] boardCoordinate = getBoardCoordinates(boxNumber);

        return isNumberContainedInBox(number, board, boardCoordinate);
	}

	private static boolean isNumberContainedInBox(int number, int[][] board, int[] boardCoordinate) {
		for(int i = boardCoordinate[0]; i <= boardCoordinate[0] + 2; i++)
            for(int j = boardCoordinate[1]; j <= boardCoordinate[1] + 2; j++)
                if (board[i][j] == number) return true;
        return false;
	}

	private static int getQuadrantNumber(int[] quadrantX, int[] quadrantY) {
		for (int a = 0; a < quadrantX.length; a++)
			for (int b = 0; b < quadrantY.length; b++)
				if (quadrantX[a] == quadrantY[b])
					return quadrantX[a];
		return 0;
	}

	public static int[] getBoardCoordinates(int squareNumber) {
        int[] startCoordinates = null;

        switch (squareNumber) {
            case 1: startCoordinates = new int[]{0, 0}; break;
            case 2: startCoordinates = new int[]{0, 3}; break;
            case 3: startCoordinates = new int[]{0, 6}; break;
            case 4: startCoordinates = new int[]{3, 0}; break;
            case 5: startCoordinates = new int[]{3, 3}; break;
            case 6: startCoordinates = new int[]{3, 6}; break;
            case 7: startCoordinates = new int[]{6, 0}; break;
            case 8: startCoordinates = new int[]{6, 3}; break;
            case 9: startCoordinates = new int[]{6, 6}; break;
        }
        return startCoordinates;
    }
	
    public static void printBoard(int[][] a) {
		for (int y = 0; y <= 8; y++) {
			for (int x = 0; x <= 8; x++) {
				System.out.print(a[y][x] + " ");
				if (x == 2 || x == 5) {
					System.out.print("  ");
				}
			}
			System.out.println();
			if (y == 2 || y == 5 || y == 8) {
				System.out.println();
			}
		}
	}

	public static void updateMove(int x, int y, int number, SudokuBoard board) {
		board.setValueOnBoard(x, y, number);
	}
	
	public static void main(String args[]){
		int[] a = new int[]{4,5,6};
		int[] b = new int[]{2,5,8};
		System.out.println(Arrays.stream(a).filter(x -> Arrays.stream(b).anyMatch(y -> y == x)).toArray()[0]);
	}
}