package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

import java.util.ArrayList;

public class GameModel implements MinesweeperActionsInterface, GameStateObserverInterface, GameStateObservableInterface
{
    private ArrayList<GameStateObserverInterface> list;
    private ScoreTableInterface scoreTable;
    private GameEngine engine;
    private GameTimer timer;
    private String nameOfGamer;

    public GameModel()
    {
        reinitialize();
    }

    @Override
    public void startGame()
    {
        engine.startGame();
    }

    @Override
    public void openCell(Dot dot)
    {
        engine.openCell(dot);
    }

    @Override
    public void setFlag(Dot dot)
    {
        engine.setFlag(dot);
    }

    @Override
    public Cell getCell(Dot dot) throws IllegalArgumentException
    {
        return engine.getCell(dot);
    }

    @Override
    public GameStates getGameState()
    {
        return engine.getGameState();
    }

    @Override
    public int getFlagsNumber()
    {
        return engine.getFlagsNumber();
    }

    @Override
    public void setDifficultyLevel(DifficultyLevels level)
    {
        engine.setDifficultyLevel(level);
    }

    @Override
    public DifficultyLevel getDifficultyLevel()
    {
        return engine.getDifficultyLevel();
    }

    @Override
    public void update(GameStates state)
    {
        switch(state)
        {
            case VICTORY -> {scoreTable.updateScoreTable(new ScoreTableRow(Integer.toString(0), nameOfGamer, Long.toString(timer.getElapsedTime())));}
            default -> {}
        }
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers()
    {
        for(GameStateObserverInterface tmp : list)
        {
            tmp.update(engine.getGameState());
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

    public void reinitialize()
    {
        list = new ArrayList<>();
        scoreTable = new GameScoreTable();
        timer = new GameTimer();
        engine = new GameEngine();
        engine.addObserver(this);
        engine.addObserver(timer);
    }

    public String[][] getScoreTable()
    {
        return scoreTable.getAsStringArray();
    }


    public void addTimerObserver(TimerObserverInterface observer)
    {
        timer.addObserver(observer);
    }

    public void setNameOfGamer(String name)
    {
        nameOfGamer = name;
    }
}
