package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

import java.util.ArrayList;

public class GameTimer implements TimerInterface, TimerObservableInterface, GameStateObserverInterface
{
    private long timeElapsed;//in seconds
    private boolean isWorking;
    private final ArrayList<TimerObserverInterface> list;

    public GameTimer()
    {
        initPrivateValue();
        list = new ArrayList<>();
    }

    public void initPrivateValue()
    {
        timeElapsed = 0;
        isWorking = false;
    }

    @Override
    public void start()
    {
        Thread timerThread = new Thread(()->{work();});
        timerThread.start();
    }

    @Override
    public void end()
    {
        isWorking = false;
    }

    @Override
    public long getElapsedTime()
    {
        return timeElapsed;
    }

    @Override
    public void notifyAllObservers()
    {
        for(TimerObserverInterface tmp : list)
        {
            tmp.update(timeElapsed);
        }
    }

    @Override
    public void addObserver(TimerObserverInterface observer)
    {
        list.add(observer);
    }

    @Override
    public void deleteObserver(TimerObserverInterface observer)
    {
        list.remove(observer);
    }

    @Override
    public void update(GameStates state)
    {
        switch(state)
        {
            case DEFEAT, VICTORY -> end();
            case GAME -> {if(!isWorking){ start();}}
            default -> {}
        }
    }

    private void work()
    {
        long startTime = System.currentTimeMillis() / 1000;
        long currentTime;
        isWorking = true;
        while(isWorking)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return;
            }
            currentTime = System.currentTimeMillis() / 1000;
            timeElapsed = currentTime - startTime;
            notifyAllObservers();
        }

    }
}
