package ru.nsu.ccfit.bakelev.minesweeper.gameviewer;

import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.Dot;
import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.GameModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

public class GridListener extends MouseAdapter
{
    private final GameModel model;

    public GridListener(GameModel model)
    {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        JButton button = (JButton) e.getSource();
        switch(e.getButton())
        {
            case BUTTON1 -> model.openCell(new Dot(button.getName()));
            case BUTTON3 -> model.setFlag(new Dot(button.getName()));
        }
    }
}
