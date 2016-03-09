package com.nameless.go_playoff;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    private void initUI() {
        add(new BoardSurface());

        setTitle("Go Playoffs");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        DataHolder.getInstance().getBoardData()[0] = 1;
        DataHolder.getInstance().getBoardData()[1] = 2;
        DataHolder.getInstance().getBoardData()[19] = 2;
        DataHolder.getInstance().getBoardData()[20 * 19] = 2;
        DataHolder.getInstance().getBoardData()[20 * 19 + 19] = 1;

        EventQueue.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
    }
}
