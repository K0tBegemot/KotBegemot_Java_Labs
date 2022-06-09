package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public abstract class DifficultyLevel
{
    protected SizeOfField size;
    protected int minesCount;
    protected int safeCellsCount;

    public DifficultyLevel(SizeOfField size, int mines)
    {
        this.size = size;
        this.minesCount = mines;
        this.safeCellsCount = size.numberOfColumns * size.numberOfLines - mines;
    }

    public SizeOfField getSizeOfField()
    {
        return size;
    }

    public int getMinesCount()
    {
        return minesCount;
    }

    public int getSafeCellsCount()
    {
        return safeCellsCount;
    }
}
