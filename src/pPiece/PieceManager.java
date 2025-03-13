package pPiece;

import java.util.ArrayList;

public class PieceManager {
    // 4 pairs
    private final static int[][] dirR = { { 0, 0 }, { 1, -1 }, { 1, -1 }, { 1, -1 } };
    private final static int[][] dirC = { { 1, -1 }, { 0, 0 }, { 1, -1 }, { -1, 1 } };

    private final static int n = 15;
    private final Piece[][] board;

    public PieceManager() {
        board = new Piece[n][n];
    }

    public Piece getPieceAt(int r, int c) {
        return board[r][c];
    }

    public void removePieceAt(int r, int c) {
        board[r][c] = null;
    }

    public boolean setPieceAt(int r, int c, Piece piece) {
        if (board[r][c] == null) {
            board[r][c] = piece;
            return true;
        } else {
            return false;
        }
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public boolean checkWin(int ii, int jj) {
        Piece piece = board[ii][jj];
        for (int i = 0; i < 4; i++) {
            int total = 1;
            for (int j = 0; j < 2; j++) {
                int rr = piece.r() + dirR[i][j];
                int cc = piece.c() + dirC[i][j];
                while (inBounds(rr, cc) && board[rr][cc] != null &&
                        board[rr][cc].group() == piece.group()) {
                    total++;
                    rr += dirR[i][j];
                    cc += dirC[i][j];
                }
            }
            if (total >= 5) return true;
        }
        return false;
    }
}