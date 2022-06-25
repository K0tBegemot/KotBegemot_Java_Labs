package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public interface GameStateObservableInterface
{
    public void notifyAllObservers();
    public void addObserver(GameStateObserverInterface observer);
    public void deleteObserver(GameStateObserverInterface observer);
}
