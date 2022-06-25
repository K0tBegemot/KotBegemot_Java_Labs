package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine implements MinesweeperActionsInterface, GameStateObservableInterface
{
    private static final int NUMBEROFNEIGHBOUR = 8;

    private Cell[][] field;
    private SizeOfField size;
    private int numberOfMines;
    private int numberOfSafeCells;

    private int numberOfFlags;
    private GameStates state;
    private int numberOfFoundMines;
    private int numberOfOpenedCells;
    private final ArrayList<GameStateObserverInterface> list;
    private DifficultyLevel level;

    GameEngine()
    {
        numberOfMines = 0;
        numberOfSafeCells = 0;
        numberOfSafeCells = 0;
        numberOfFlags = 0;
        state = GameStates.START;
        numberOfFoundMines = 0;
        numberOfOpenedCells = 0;
        list = new ArrayList<>();
    }

    @Override
    public void startGame()
    {
        notifyAllObservers();
    }

    @Override
    public void openCell(Dot dot)
    {
        if(state == GameStates.START)
        {
            generateField(dot);
        }
        if(state == GameStates.GAME)
        {
            if(isMine(dot))
            {
                showAllMines();
                state = GameStates.DEFEAT;
            }else
            {
                showCellsAround(dot);
                if(numberOfOpenedCells == numberOfSafeCells)
                {
                    state = GameStates.VICTORY;
                }
            }
        }
        notifyAllObservers();
    }

    @Override
    public void setFlag(Dot dot)
    {
        if(state == GameStates.START)
        {
            generateField(dot);
        }
        if(state == GameStates.GAME)
        {
            if(isFlag(dot))
            {
                deleteFlag(dot);
            }else
            {
                if(isHidden(dot))
                {
                    addFlag(dot);
                    if(isAllMinesFound())
                    {
                        state = GameStates.VICTORY;
                    }
                }
            }
        }
        notifyAllObservers();
    }

    @Override
    public Cell getCell(Dot dot) throws IllegalArgumentException
    {
        if(isDotOnField(dot))
        {
            return field[dot.x][dot.y];
        }else
        {
            throw new IllegalArgumentException("Wrong coordinates. Dot is out of bound");
        }
    }

    @Override
    public GameStates getGameState()
    {
        return state;
    }
    @Override
    public int getFlagsNumber()
    {
        return numberOfFlags;
    }

    @Override
    public void setDifficultyLevel(DifficultyLevels level)
    {
        switch(level)
        {
            case STANDARD -> this.level = new StandardLevelDifficulty();
            case HARD -> this.level = new HardLevelDifficulty();
        }
    }

    @Override
    public DifficultyLevel getDifficultyLevel()
    {
        return level;
    }

    @Override
    public void notifyAllObservers()
    {
        for(GameStateObserverInterface tmp : list)
        {
            tmp.update(state);
        }
    }

    @Override
    public void addObserver(GameStateObserverInterface observer)
    {
        list.add(observer);
    }

    @Override
    public void deleteObserver(GameStateObserverInterface observer)
    {
        list.remove(observer);
    }

    private boolean isAllMinesFound()
    {
        if(numberOfFoundMines == numberOfMines)
        {
            return true;
        }
        return false;
    }

    private boolean isDotOnField(Dot dot)
    {
        if(dot.x < size.numberOfLines && dot.y < size.numberOfColumns && dot.x >= 0 && dot.y >= 0)
        {
            return true;
        }
        return false;
    }

    private void generateField(Dot dot)
    {
        initField();
        layMines(dot);
        //layMinesTest();
        setNumbers();
        state = GameStates.GAME;
    }

    private void initField()
    {
        numberOfMines = level.getMinesCount();
        numberOfSafeCells = level.getSafeCellsCount();
        size = level.getSizeOfField();
        field = new Cell[size.numberOfLines][size.numberOfColumns];
        for(int i = 0; i < size.numberOfLines; i++)
        {
            for(int j = 0; j < size.numberOfColumns; j++)
            {
                field[i][j] = new Cell(CellValue.DEFAULT);
                field[i][j].setTrueValue(CellValue.EMPTY);
            }
        }
    }

    private void layMines(Dot dot)
    {
        int minesCount = numberOfMines;
        while(minesCount > 0)
        {
            Dot cordOfMine = generateMineDot(dot);
            if(Math.abs(cordOfMine.x - dot.x) > 1 || Math.abs(cordOfMine.y - dot.y) > 1)
            {
                if (field[cordOfMine.x][cordOfMine.y].getTrueValue() != CellValue.MINE)
                {
                    field[cordOfMine.x][cordOfMine.y].setValue(CellValue.MINE);
                    minesCount -= 1;
                }
            }
        }
    }

    private void layMinesTest()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                field[i][j].setValue(CellValue.MINE);
            }
        }
    }

    private void setNumbers()
    {
        for(int i = 0; i < size.numberOfLines; i++)
        {
            for(int j = 0; j < size.numberOfColumns; j++)
            {
                Dot dot = new Dot(i, j);
                if(!isMine(dot))
                {
                    field[dot.x][dot.y].setValue(this.numberOfNeighbourMines(dot));
                }
            }
        }
    }

    private CellValue numberOfNeighbourMines(Dot dot)
    {
        int numberOfNMines = 0;
        for(int i = 0; i < NUMBEROFNEIGHBOUR; i++)
        {
            Dot neighbourDot = getNeighbourDot(dot, i);
            if(isDotOnField(neighbourDot))
            {
                if (isMine(neighbourDot))
                {
                    numberOfNMines += 1;
                }
            }
        }
        return getCellValueByNumber(numberOfNMines);
    }

    private CellValue getCellValueByNumber(int number)
    {
        CellValue retValue;
        switch(number)
        {
            case 0 -> retValue = CellValue.EMPTY;
            case 1 -> retValue = CellValue.ONE;
            case 2 -> retValue = CellValue.TWO;
            case 3 -> retValue = CellValue.THREE;
            case 4 -> retValue = CellValue.FOUR;
            case 5 -> retValue = CellValue.FIVE;
            case 6 -> retValue = CellValue.SIX;
            case 7 -> retValue = CellValue.SEVEN;
            case 8 -> retValue = CellValue.EIGHT;
            default -> retValue = CellValue.DEFAULT;
        }
        return retValue;
    }

    private Dot generateMineDot(Dot dot)
    {
        Random rand = new Random();
        Dot newDot;
        do {
            int x = rand.nextInt(size.numberOfLines);
            int y = rand.nextInt(size.numberOfColumns);
            newDot = new Dot(x, y);
        }while(!isDotOnField(newDot) || dot.equals(newDot));
        return newDot;
    }

    private boolean isMine(Dot dot)
    {
        if(isDotOnField(dot))
        {
            return getCell(dot).isMine();
        }
        return false;
    }

    private void showAllMines()
    {
        for(int i = 0; i < size.numberOfLines; i++)
        {
            for(int j = 0; j < size.numberOfColumns; j++)
            {
                Dot dot = new Dot(i, j);
                if(isMine(dot))
                {
                    field[i][j].setHidden(false);
                }
            }
        }
    }

    private boolean isFlag(Dot dot)
    {
        if(isDotOnField(dot))
        {
            return field[dot.x][dot.y].isFlag();
        }
        return false;
    }

    private void addFlag(Dot dot)
    {
        if(numberOfFlags < numberOfMines)
        {
            if(isDotOnField(dot))
            {
                field[dot.x][dot.y].setValue(CellValue.FLAG);
                numberOfFlags += 1;
                if (isMine(dot))
                {
                    this.numberOfFoundMines += 1;
                }
            }
        }
    }

    private void deleteFlag(Dot dot)
    {
        if(numberOfFlags > 0)
        {
            if(isDotOnField(dot))
            {
                field[dot.x][dot.y].deleteFlag();
                numberOfFlags -= 1;
                if(isMine(dot))
                {
                    numberOfFoundMines -= 1;
                }
            }
        }
    }

    private void showCellsAround(Dot dot)
    {
        if(isDotOnField(dot))
        {
            showCell(dot);
            if(isEmpty(dot))
            {
                for(int i = 0; i < NUMBEROFNEIGHBOUR; i++)
                {
                    Dot neighbourDot = getNeighbourDot(dot, i);
                    if(isDotOnField(neighbourDot))
                    {
                        if(isEmpty(neighbourDot) && isHidden(neighbourDot))
                        {
                            showCellsAround(neighbourDot);
                        }else
                        {
                            if(!isMine(neighbourDot) && isHidden(neighbourDot))
                            {
                                showCell(neighbourDot);
                                if(isFlag(neighbourDot))
                                {
                                    deleteFlag(neighbourDot);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void showCell(Dot dot)
    {
        if(isDotOnField(dot))
        {
            field[dot.x][dot.y].setHidden(false);
            numberOfOpenedCells += 1;
        }
    }

    private boolean isDigit(Dot dot)
    {
        if(isDotOnField(dot))
        {
            return field[dot.x][dot.y].isDigit();
        }
        return false;
    }

    private Dot getNeighbourDot(Dot dot, int index)
    {
        int xDispl = (int)Math.round(Math.sin(Math.PI / 4 * index));
        int yDispl = (int)Math.round(Math.cos(Math.PI / 4 * index));
        return new Dot(dot.x + xDispl, dot.y + yDispl);
    }

    private boolean isEmpty(Dot dot)
    {
        if(isDotOnField(dot))
        {
            return field[dot.x][dot.y].isEmpty();
        }
        return false;
    }

    private boolean isHidden(Dot dot)
    {
        if(isDotOnField(dot))
        {
            return field[dot.x][dot.y].isHidden();
        }
        return false;
    }
}
