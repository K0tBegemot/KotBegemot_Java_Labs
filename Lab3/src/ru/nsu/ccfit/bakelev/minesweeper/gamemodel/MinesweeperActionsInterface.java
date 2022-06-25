package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

public interface MinesweeperActionsInterface
{
    void startGame();
    void openCell(Dot dot);
    void setFlag(Dot dot);
    Cell getCell(Dot dot);
    GameStates getGameState();
    int getFlagsNumber();
    void setDifficultyLevel(DifficultyLevels level);
    DifficultyLevel getDifficultyLevel();
}
