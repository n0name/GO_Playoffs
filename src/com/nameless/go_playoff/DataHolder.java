package com.nameless.go_playoff;

public class DataHolder {
    private static DataHolder instance = new DataHolder();
    private DataHolder() {}
    public static DataHolder getInstance() { return instance; }

    private int boardData_prev2[] = new int[20 * 20];
    private int boardData_prev[] = new int[20 * 20];
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

    public void applyMove(int x, int y, int player) {
        System.arraycopy(boardData_prev, 0, boardData_prev2, 0, boardData.length);
        System.arraycopy(boardData, 0, boardData_prev, 0, boardData.length);
        boardData[y * 20 + x] = player;
    }
}
