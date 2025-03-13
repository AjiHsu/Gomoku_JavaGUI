package pAI;

import pPiece.Group;
import pPiece.Piece;
import pPiece.PieceManager;

import java.util.Arrays;
import java.util.List;

public class SimpleBest extends Direction {
    private static final double opponentWeight = 0.95;

    private final SimpleEvaluate simpleEvaluate;
    private final PieceManager pieceManager;

    public SimpleBest(PieceManager pieceManager) {
        this.pieceManager = pieceManager;
        simpleEvaluate = new SimpleEvaluate(pieceManager);
    }

    public List<Integer> getMove(Group turn) {
        double maxScore = Integer.MIN_VALUE;
        List<Integer> move = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (pieceManager.getPieceAt(i, j) != null) continue;
                Piece piece = new Piece(i, j, turn);
                Piece opponentPiece = new Piece(i, j, getOpponent(turn));
                double score = simpleEvaluate.getResult(piece) + simpleEvaluate.getResult(opponentPiece) * opponentWeight;
                if (score > maxScore) {
                    maxScore = score;
                    move = Arrays.asList(i, j);
                } else if (score == maxScore) {
                    if (Math.abs(move.get(0) - n / 2) + Math.abs(move.get(1) - n / 2) >
                    Math.abs(i - n / 2) + Math.abs(j - n / 2)) {
                        move = Arrays.asList(i, j);
                    }
                }
            }
        }
        return move;
    }
}
