package pGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonManager {
    private final static int n = 15;
    private final JButton[][] buttons;

    public ButtonManager(JPanel panel, ActionListener actionListener) {
        buttons = new JButton[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(40, 40));
                buttons[i][j].addActionListener(actionListener);
                buttons[i][j].setIcon(new ImageIcon(getClass().getResource("/Images/NULL.png")));
                buttons[i][j].setBorder(new LineBorder(Color.BLACK));

                panel.add(buttons[i][j]);
            }
        }
    }

    public JButton getButton(int r, int c) {
        return buttons[r][c];
    }
}
