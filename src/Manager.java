import pGUI.ButtonManager;
import pGUI.Frame;
import pPiece.Group;
import pPiece.Piece;
import pPiece.PieceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager implements ActionListener {
    private final static int n = 15;

    private final Frame frame;
    private final ButtonManager buttonManager;
    private final PieceManager pieceManager;

    private Group turn;
    private boolean end;

    Manager() {
        frame = new Frame();
        buttonManager = new ButtonManager(frame.panel, this);
        pieceManager = new PieceManager();
        frame.pack();
        turn = Group.WHITE;
        end = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (end) return;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (e.getSource() == buttonManager.getButton(i, j)) {
                    if (pieceManager.setPieceAt(i, j, new Piece(i, j, turn))) {
                        // set image
                        setButtonImage(i, j);

                        // change turn
                        changeTurn();

                        // end check
                        if (pieceManager.checkWin(i, j)) {
                            endInfo();
                            end = true;
                        }
                    }
                    break;
                }
            }
        }
    }

    private void setButtonImage(int r, int c) {
        if (turn == Group.WHITE) {
            buttonManager.getButton(r, c).setIcon(
                    new ImageIcon(getClass().getResource("/Images/White.png")));
        } else {
            buttonManager.getButton(r, c).setIcon(
                    new ImageIcon(getClass().getResource("/Images/Black.png")));
        }
    }

    private void changeTurn() {
        if (turn == Group.BLACK) {
            turn = Group.WHITE;
            frame.setTitle("Gomoku! - It's white's turn");
        } else {
            turn = Group.BLACK;
            frame.setTitle("Gomoku! - It's black's turn");
        }
    }

    private void endInfo() {
        JFrame endFrame = new JFrame();
        endFrame.add(new JLabel("GAME OVER"));
        endFrame.setSize(100, 50);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setResizable(false);
        endFrame.setVisible(true);
    }
}
