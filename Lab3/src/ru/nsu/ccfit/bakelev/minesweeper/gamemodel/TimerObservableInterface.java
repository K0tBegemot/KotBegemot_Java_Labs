package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public interface TimerObservableInterface
{
    void notifyAllObservers();
    void addObserver(TimerObserverInterface observer);
    void deleteObserver(TimerObserverInterface observer);
}
