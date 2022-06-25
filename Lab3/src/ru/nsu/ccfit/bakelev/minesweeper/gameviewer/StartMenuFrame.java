package ru.nsu.ccfit.bakelev.minesweeper.gameviewer;

import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.DifficultyLevels;
import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.GameModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StartMenuFrame extends JFrame implements FrameObservableInterface
{
    private final GameModel model;
    private final int width = 300;
    private final int height = 300;
    private final ArrayList<FrameObserverInterface> list = new ArrayList<>();
    private JTextField nameField;
    private FrameStates state;

    public StartMenuFrame(GameModel model)
    {
        this.model = model;
        state = FrameStates.MENUOPEN;
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        paintStartMenu();
        setVisible(true);
    }

    @Override
    public void dispose()
    {
        state = FrameStates.MENUCLOSED;
        notifyAllObservers();
        super.dispose();
    }

    @Override
    public void notifyAllObservers()
    {
        for(FrameObserverInterface tmp : list)
        {
            tmp.update(state);
        }
    }

    @Override
    public void addObserver(FrameObserverInterface observer)
    {
        list.add(observer);
    }

    @Override
    public void deleteObserver(FrameObserverInterface observer)
    {
        list.remove(observer);
    }

    public String getPlayerName()
    {
        return nameField.getText();
    }

    private void paintStartMenu()
    {
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel("Enter your name: ");
        nameLabel.setPreferredSize(new Dimension(100, 50));
        nameField = new JTextField(40);
        JButton startStandardGame = new JButton("Standard game");
        JButton startHardGame = new JButton("Hard game");
        startStandardGame.addActionListener(new MenuButtonListener(this, model, DifficultyLevels.STANDARD));
        startHardGame.addActionListener(new MenuButtonListener(this, model, DifficultyLevels.HARD));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(startStandardGame, BorderLayout.CENTER);
        buttonPanel.add(startHardGame, BorderLayout.CENTER);
        boxPanel.add(nameLabel, SwingConstants.CENTER);
        boxPanel.add(nameField);
        boxPanel.add(buttonPanel);
        add(boxPanel);
    }
}
