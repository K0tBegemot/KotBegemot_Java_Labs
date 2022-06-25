package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public class ScoreTableRow {
    private static final int INDEXOFINDEX = 0;
    private static final int INDEXOFNAMEOFGAMER = 1;
    private static final int INDEXOFTIME = 2;

    public static final String REGEX = " ";
    public static final int columns = 3;

    public String indexInTable;
    public String nameOfGamer;
    public String time;//in seconds

    ScoreTableRow(String index, String name, String time) {
        this.indexInTable = index;
        this.nameOfGamer = name;
        this.time = time;
    }

    ScoreTableRow(String[] row) throws IllegalArgumentException {
        if (row.length != columns) {
            throw new IllegalArgumentException("Incorrect length of array in ScoreTableRow constructor call");
        }
        this.indexInTable = row[INDEXOFINDEX];
        this.nameOfGamer = row[INDEXOFNAMEOFGAMER];
        this.time = row[INDEXOFTIME];
    }

    @Override
    public String toString()
    {
        return String.format("%s%s%s%s%s", this.indexInTable, REGEX, this.nameOfGamer, REGEX, this.time);
    }
}
