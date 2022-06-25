package ru.nsu.ccfit.bakelev.minesweeper.gameviewer;

import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.GameModel;

public class GraphicMode
{
    private MainGameFrame frame;

    public GraphicMode(GameModel model)
    {
        frame = new MainGameFrame(model);
        model.addObserver(frame);
        model.addTimerObserver(frame);
        model.startGame();
    }
}
