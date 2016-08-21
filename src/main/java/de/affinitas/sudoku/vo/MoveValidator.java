package de.affinitas.sudoku.vo;

public enum MoveValidator {
    INVALID(-1),
    VALID(0),
    COMPLETE(1);

    MoveValidator(int move) {
        validateMove = move;
    }

    int validateMove;
}
