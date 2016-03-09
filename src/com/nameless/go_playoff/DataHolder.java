package com.nameless.go_playoff;

public class DataHolder {
    private static DataHolder instance = new DataHolder();
    private DataHolder() {}
    public static DataHolder getInstance() { return instance; }

    private int boardData[] = new int[20 * 20];

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
}
