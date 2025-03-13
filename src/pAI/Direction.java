package pAI;

import pPiece.Group;

public abstract class Direction {
    protected final static int n = 15;

    protected final static int[][] dirR = { { 0, 0 }, { 1, -1 }, { 1, -1 }, { 1, -1 } };
    protected final static int[][] dirC = { { 1, -1 }, { 0, 0 }, { 1, -1 }, { -1, 1 } };

    protected boolean inBounds(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    protected Group getOpponent(Group group) {
        if (group == Group.WHITE) return Group.BLACK;
        else if (group == Group.BLACK) return Group.WHITE;
        else return null;
    }
}
