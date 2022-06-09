package ru.nsu.ccfit.bakelev.minesweeper.gameviewer;

import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.DifficultyLevels;
import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonListener implements ActionListener
{
    private final StartMenuFrame frame;
    private final GameModel model;
    private final DifficultyLevels level;

    MenuButtonListener(StartMenuFrame frame, GameModel model, DifficultyLevels level)
    {
        this.frame = frame;
        this.model = model;
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String text = frame.getPlayerName();
        if(!text.isEmpty())
        {
            model.setDifficultyLevel(level);
            model.setNameOfGamer(text);
            //frame.notifyAllObservers();
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
