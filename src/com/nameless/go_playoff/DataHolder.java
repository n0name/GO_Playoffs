package com.nameless.go_playoff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHolder {
    private static DataHolder instance = new DataHolder();
    private DataHolder() {}
    public static DataHolder getInstance() { return instance; }

    private int boardData_prev[] = new int[20 * 20];
    private int boardData[] = new int[20 * 20];
    private int capturedStones[] = new int[2];

    public int[] getBoardData() { return boardData; }

    public String printBoard() {
        String board = "";
        for (int i = 0; i < 20; i ++ ) {
            for (int j = 0; j < 19; j++) {
                board += String.valueOf(boardData[i * 20 + j]) + " ";
            }
            board += String.valueOf(boardData[i * 20 + 19]) + "\n";
        }
        return board;
    }



    public boolean checkCaptured(int x, int y,
                                 int player,
                                 HashMap<Integer, List<Integer>> checked,
                                 int[] boardAfter) {
        // is checked ?
        if (checked.get(x) != null) {
            if ( checked.get(x).contains(y)) {
                return true;
            } else {
                // add to checked list
                checked.get(x).add(y);
            }
        } else {
            // add to checked list
            checked.put(x, new ArrayList<Integer>() {{ add(y); }});
        }

        List<Boolean> results = new ArrayList<>();

        // check left
        if (x > 0) {
            int left = boardAfter[y * 20 + x - 1];
            if (left == player) {
                // Our stone
                results.add(checkCaptured(x - 1, y, player, checked, boardAfter));
            } else if (left != 0) {
                // Opponent stone
                results.add(true);
            } else {
                // Free place
                return false;
            }
        }

        // check right
        if (x < 19) {
            int right = boardAfter[y * 20 + x + 1];
            if (right == player) {
                // Our stone
                results.add(checkCaptured(x + 1, y, player, checked, boardAfter));
            } else if (right != 0) {
                // Opponent stone
                results.add(true);
            } else {
                // Free place
                return false;
            }
        }

        // check up
        if (y > 0) {
            int up = boardAfter[(y - 1) * 20 + x];
            if (up == player) {
                // Our stone
                results.add(checkCaptured(x, y - 1, player, checked, boardAfter));
            } else if (up != 0) {
                // Opponent stone
                results.add(true);
            } else {
                // Free place
                return false;
            }
        }

        // check down
        if (y < 19) {
            int down = boardAfter[(y + 1) * 20 + x];
            if (down == player) {
                // Our stone
                results.add(checkCaptured(x, y + 1, player, checked, boardAfter));
            } else if (down != 0) {
                // Opponent stone
                results.add(true);
            } else {
                // Free place
                return false;
            }
        }

        return !results.contains(false);

    }

    private void removeAndCheckNext(int x, int y, int player, int[] boardAfter) {
        if (boardAfter[y * 20 + x] != player) {
            return;
        }

        boardAfter[y * 20 + x] = 0;
        capturedStones[player] += 1;

        if (x > 0) removeAndCheckNext(x - 1, y, player, boardAfter); // left
        if (x < 19) removeAndCheckNext(x + 1, y, player, boardAfter); // right
        if (y > 0) removeAndCheckNext(x, y - 1, player, boardAfter); // up
        if (y < 19) removeAndCheckNext(x, y + 1, player, boardAfter); // down
    }

    public boolean doMove(int x, int y, int player) {
        System.arraycopy(boardData, 0, boardData_prev, 0, boardData.length);

        int boardAfter[] = new int[20 * 20];
        int opponent = (player == 1) ? 2 : 1;
        System.arraycopy(boardData, 0, boardAfter, 0, boardData.length);

        // Check if place is taken
        if ( boardAfter[y * 20 + x] != 0 ) {
            return false;
        }

        boardAfter[y * 20 + x] = player;

        // Do captures...
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (boardAfter[i * 20 + j] == opponent) {
                    if (checkCaptured(j, i, opponent, new HashMap<>(), boardAfter)) {
                        // Remove captured
                        removeAndCheckNext(j, i, opponent, boardAfter);
                    }
                }
            }
        }

        // Check for suicide..
        boolean suicide = true;
        if (x > 0 ) suicide &= (boardAfter[y * 20 + x - 1] == opponent);
        if (x < 19) suicide &= (boardAfter[y * 20 + x + 1] == opponent);
        if (y > 0 ) suicide &= (boardAfter[(y - 1) * 20 + x] == opponent);
        if (y < 19) suicide &= (boardAfter[(y + 1) * 20 + x] == opponent);

        if (suicide) {
            return false;
        }

        // Check fo ko
        boolean ko = true;
        for (int i = 0; i < 20 * 20; i++) {
            if (boardData_prev[i] != boardAfter[i] ) {
                ko = false;
                break;
            }
        }

        if (ko) {
            System.err.println("Ko!!!");
            return false;
        }

        // Apply move
        System.arraycopy(boardAfter, 0, boardData, 0, boardData.length);
        return true;
    }
}
