package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public interface ScoreTableInterface
{
    public String[][] getAsStringArray();
    public void updateScoreTable(ScoreTableRow row);
}
