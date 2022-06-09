package ru.nsu.ccfit.bakelev.minesweeper.gameviewer;

public interface FrameObservableInterface
{
    public void notifyAllObservers();
    public void addObserver(FrameObserverInterface observer);
    public void deleteObserver(FrameObserverInterface observer);
}
