package com.nameless.go_playoff;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public static boolean isValidMove(int x, int y, int player) {
        // TODO: implement me
        return true;
    }

    static Main _main;

    public static void main(String[] args) {

//        DataHolder.getInstance().getBoardData()[0] = 1;
//        DataHolder.getInstance().getBoardData()[1] = 2;
//        DataHolder.getInstance().getBoardData()[19] = 2;
//        DataHolder.getInstance().getBoardData()[20 * 19] = 2;
//        DataHolder.getInstance().getBoardData()[20 * 19 + 19] = 1;


        EventQueue.invokeLater(() -> {
            _main = new Main();
            _main.setVisible(true);
        });

        Pattern result_pattern = Pattern.compile("(\\d+) (\\d+)");

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
            boolean p1_turn = true;
            String last_result = "";
            while (true) {
                String result;
                if (p1_turn) {
                    result = p1.run(DataHolder.getInstance().printBoard());
                } else  {
                    result = p2.run(DataHolder.getInstance().printBoard());
                }


                if (result.equals("pass") ) {
                    if (last_result.equals("pass")) {
                        System.err.println("Game Over");
                        break;
                    }
                    last_result = result;
                    continue;
                }

                Matcher m = result_pattern.matcher(result.trim());
                if (!m.matches()) {
                    if (p1_turn)
                        System.err.println("Player1: invalid output -> " + result);
                    else
                        System.err.println("Player2: invalid output -> " + result);
                    break;
                }

                int x = Integer.parseInt(m.group(1));
                int y = Integer.parseInt(m.group(2));

                if (isValidMove(x, y, p1_turn ? 1 : 2)) {
                    DataHolder.getInstance().applyMove(x, y, p1_turn ? 1 : 2);

                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (_main != null) {
                    _main.repaint();
                }
                last_result = result;
                p1_turn = !p1_turn;

            }


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

    }
}
