package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public interface TimerInterface
{
    void start();
    void end();
    long getElapsedTime();
}
