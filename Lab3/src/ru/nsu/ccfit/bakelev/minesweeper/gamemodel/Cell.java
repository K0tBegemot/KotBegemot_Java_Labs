package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public class Cell
{
    private CellValue trueValue;
    private CellValue visibleValue;
    private boolean isHiddenFlag = true;
    private boolean isDigitFlag = false;

    public Cell(CellValue value)
    {
        this.setValue(value);
    }

    public Cell(Cell anotherCell)
    {
        this.trueValue = anotherCell.trueValue;
        this.visibleValue = anotherCell.visibleValue;
        this.isHiddenFlag = anotherCell.isHiddenFlag;
        this.isDigitFlag = anotherCell.isDigitFlag;
    }

    public void setValue(CellValue value)
    {
        switch(value)
        {
            case MINE -> setMine();
            case ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT -> setDigit(value);
            case FLAG ->setFlag();
            case EMPTY -> setEmpty();
            case DEFAULT -> setDefault();
        }
    }

    public CellValue getTrueValue()
    {
        return trueValue;
    }

    public void setTrueValue(CellValue value)
    {
        trueValue = value;
    }

    public CellValue getVisibleValue()
    {
        return visibleValue;
    }

    public void setVisibleValue(CellValue value)
    {
        visibleValue = value;
    }

    public boolean isDigit()
    {
        return isDigitFlag;
    }

    public void setDigit(CellValue value)
    {
        trueValue = value;
        isHiddenFlag = true;
        isDigitFlag = true;
    }

    public boolean isMine()
    {
        return (trueValue == CellValue.MINE);
    }

    public boolean isFlag()
    {
        return (visibleValue == CellValue.FLAG);
    }

    public boolean isEmpty()
    {
        return (trueValue == CellValue.EMPTY);
    }

    public boolean isHidden()
    {
        return isHiddenFlag;
    }

    public void setHidden(boolean value)
    {
        isHiddenFlag = value;
    }

    public void deleteFlag()
    {
        if(isFlag())
        {
            visibleValue = CellValue.DEFAULT;
        }
    }

    private void setMine()
    {
        trueValue = CellValue.MINE;
        isHiddenFlag = true;
    }

    private void setFlag()
    {
        visibleValue = CellValue.FLAG;
        isHiddenFlag = true;
    }

    private void setEmpty()
    {
        trueValue = CellValue.EMPTY;
        isHiddenFlag = true;
    }

    private void setDefault()
    {
        visibleValue = CellValue.DEFAULT;
        isHiddenFlag = true;
    }
}
