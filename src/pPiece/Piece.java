package pPiece;

public class Piece {
    private final int r, c;
    private final Group group;

    public Piece(int r, int c, Group group) {
        this.r = r;
        this.c = c;
        this.group = group;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public Group getGroup() {
        return group;
    }
}
