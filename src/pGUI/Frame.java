package pGUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static final int n = 15;
    public final JPanel panel;

    public Frame () {
        panel = new JPanel();
        setTitle("Gomoku! - It's white's turn");
        panel.setLayout(new GridLayout(n, n));
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setVisible(true);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ignored) {
        }
    }
}
