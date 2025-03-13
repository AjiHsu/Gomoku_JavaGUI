package pAI;

import pPiece.Piece;
import pPiece.PieceManager;

import java.util.Random;

public class SimpleEvaluate extends Direction {
    private static final int[] sConsecutive = { 2, 8, 18, 32, 100 };
    private static final int[] sBlock = { 1, 4, 9, 16, 25 };
    private static final int sJump = 7;
    private static final double sRandom = 0;
    private final Random rand;

    private final PieceManager pieceManager;

    SimpleEvaluate(PieceManager pieceManager) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        this.pieceManager = pieceManager;
    }

    public double getResult(Piece piece) {
        int total = 0;
        for (int i = 0; i < 4; i++) {
            int length = 0;
            boolean hasJump = false;
            int blockCount = 0;
            for (int j = 0; j < 2; j++) {
                int rr = piece.r() + dirR[i][j];
                int cc = piece.c() + dirC[i][j];
                while (inBounds(rr, cc) && pieceManager.getPieceAt(rr, cc) != null &&
                        pieceManager.getPieceAt(rr, cc).group() == piece.group()) {
                    length++;
                    rr += dirR[i][j];
                    cc += dirC[i][j];
                }
                if (inBounds(rr, cc) && pieceManager.getPieceAt(rr, cc) == null) {
                    rr += dirR[i][j];
                    cc += dirC[i][j];
                    if (inBounds(rr, cc) && pieceManager.getPieceAt(rr, cc) != null) {
                        if (pieceManager.getPieceAt(rr, cc).group() == piece.group())
                            hasJump = true;
                        else if (getOpponent(pieceManager.getPieceAt(rr, cc).group()) == piece.group())
                            blockCount++;
                    }
                }
            }
            if (length < 3 && hasJump) { // jump is useful
                total += sJump;
            }
            total += sConsecutive[length];
            total -= sBlock[length] * blockCount;
        }
        return total * (1 - sRandom) + rand.nextInt(total) * sRandom;
    }
}
