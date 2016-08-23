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
		return exampleSudoku;
	}

	/**
	 * <p>Return false or true depends whether the number in position is valid</p>
	 * 
	 * */
	public static boolean validateMove(int x, int y, int number, SudokuBoard board) {
		boolean result = isValidNumberInPosition(x, y, number, board.getBoard());
		return result;
	}
	
	/**
	 * There are four validation that is important to consider a number valid.
	 * 
	 * isPositionDifZero - check the position is different of zero
	 * isRowContainNumber - check whether a row contain a number passed by param
	 * isColumnContainNumber - check whether a columns contain a number passed by param
	 * isBoxContainNumber - check whether a box contain a number passed by param
	 * 
	 * @see isPositionDifZero
	 * @see isRowContainNumber
	 * @see isColumnContainNumber
	 * @see isBoxContainNumber
	 * */
	private static boolean isValidNumberInPosition(int x, int y, int number, int[][] board) {
		if (isPositionDifZero(x, y, board))          return false;
		if (isRowContainNumber(x, number, board))    return false;
		if (isColumnContainNumber(y, number, board)) return false;
		if (isBoxContainNumber(x, y, number, board)) return false;
		return true;
	}

	/**
	 * <p>Validate if a coordinates x and y in a Cartesian Coordinates is different of zero.</p>
	 * 
	 * */
	private static boolean isPositionDifZero(int x, int y, int[][] board) {
		return board[x][y] != 0;
	}

	/**
	 * <p>Return false if at least one position inside a board. Return true if all positions is filled, 
	 * it means all positions different at zero.</p>
	 * 
	 * */
	public static boolean isBoardSolvedCompletly(int[][] board) {
		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board.length; y++)
				if (board[x][y] == 0)
					return false;
		return true;
	}

	/**
	 * <p>Find inside an array at specific row <b>x</b>, emulating a Cartesian Coordinates, passed by param whether a number exists in a board.</p> 
	 * */
	public static boolean isRowContainNumber(int x, int number, int[][] a) {
		for (int column = 0; column <= 8; column++) {
			if (a[x][column] == number) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * <p>Find inside an array at specific column <b>y</b>, emulating a Cartesian Coordinates, passed by param whether a number exists in a board.</p> 
	 * */
	public static boolean isColumnContainNumber(int y, int number, int[][] a) {
		for (int row = 0; row <= 8; row++) {
			if (a[row][y] == number) {
				return true;
			}
		}

		return false;
	}

	/**
	 * <p>Return true if a number is contained in a board. Return false instead.<p>
	 * 
	 * <p>Define which quadrant it's necessary to find the information and get coordinates </p>
	 * <p>In a specific array of array or 2D Array, has <b>x</b> and <b>y</b> coordinates or quadrants, this emulate a Cartesian Coordinates.
	 *  
	 *  @see getQuadrantNumber
	 *  @see getBoardCoordinates
	 *  @see isNumberContainedInBox
	 * */
	public static boolean isBoxContainNumber(int x, int y, int number, int[][] board) {
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

	/**
	 * <p>Verify if a number <b>number</b> exists inside a array of array <b>board></b> given follow coordinates <b>boardCoordinates</b>.</p>
	 * 
	 * */
	private static boolean isNumberContainedInBox(int number, int[][] board, int[] boardCoordinates) {
		for(int i = boardCoordinates[0]; i <= boardCoordinates[0] + 2; i++)
            for(int j = boardCoordinates[1]; j <= boardCoordinates[1] + 2; j++)
                if (board[i][j] == number) return true;
        return false;
	}

	/**
	 * <p>Return a quadrant number where the position is encountered.</p>
	 * 
	 * */
	private static int getQuadrantNumber(int[] quadrantX, int[] quadrantY) {
		for (int a = 0; a < quadrantX.length; a++)
			for (int b = 0; b < quadrantY.length; b++)
				if (quadrantX[a] == quadrantY[b])
					return quadrantX[a];
		return 0;
	}

	/**
	 * <p>Return the position in Cartesian Coordinates based on <b>squareNumber</b>. 
	 * Follow a given 9x9 sudoku board. 
	 * */
	public static int[] getBoardCoordinates(int squareNumber) {
        //FIXME Improve getBoardCoordinates method to show coordinates depends on the SIZE defined on board creation
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
	
	/**
	 * <p>Enables the developer to see whats is going on inside the board.</p>
	 *   
	 * */
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

    /**
     * <p>Update the board with a given number and position.</p>
     * 
     * */
	public static void updateMove(int x, int y, int number, SudokuBoard board) {
		board.setValueOnBoard(x, y, number);
	}
}