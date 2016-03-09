package com.nameless.go_playoff;

public class DataHolder {
    private static DataHolder instance = new DataHolder();
    private DataHolder() {}
    public static DataHolder getInstance() { return instance; }

    private int boardData[] = new int[20 * 20];

    public int[] getBoardData() { return boardData; }
}
