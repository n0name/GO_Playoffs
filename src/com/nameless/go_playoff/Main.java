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
        Player p1 = null;
        Player p2 = null;
        try {
            p1 = new Player("player 1", "dummy.py");
            p2 = new Player("player 2", "dummy.py");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            System.out.println("Send test...");
            System.out.println(p1.run("test"));

            System.out.println("Send test...");
            System.out.println(p2.run("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }


//        DataHolder.getInstance().getBoardData()[0] = 1;
//        DataHolder.getInstance().getBoardData()[1] = 2;
//        DataHolder.getInstance().getBoardData()[19] = 2;
//        DataHolder.getInstance().getBoardData()[20 * 19] = 2;
//        DataHolder.getInstance().getBoardData()[20 * 19 + 19] = 1;
//
//        EventQueue.invokeLater(() -> {
//            Main m = new Main();
//            m.setVisible(true);
//        });
    }
}
