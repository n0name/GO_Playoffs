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

        Player p1;
        Player p2;
        try {
            p1 = new Player("player 1", "dummy.py");
            p2 = new Player("player 2", "dummy.py");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            System.out.println("Send board...");
            System.out.println(p1.run(DataHolder.getInstance().printBoard()));

//            System.out.println("Send test...");
//            System.out.println(p2.run("test"));
        } catch (Player.TimeoutException e) {
            System.out.println("player: " + e.getPlayerName() + " timed out !");
            if (p1.getName().equals(e.getPlayerName())) {
                System.out.println("player: " + p2.getName() + " Wins");
            } else {
                System.out.println("player: " + p1.getName() + " Wins");
            }
        }

        p1.killProcess();
        p2.killProcess();


//
//        EventQueue.invokeLater(() -> {
//            Main m = new Main();
//            m.setVisible(true);
//        });
    }
}
