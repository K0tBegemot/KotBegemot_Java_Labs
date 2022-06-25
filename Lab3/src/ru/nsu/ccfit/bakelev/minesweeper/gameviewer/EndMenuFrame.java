package ru.nsu.ccfit.bakelev.minesweeper.gameviewer;

import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EndMenuFrame extends JFrame
{
    private final GameModel model;
    private final MainGameFrame frame;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private static final int TEXTLABELWIDTH = 100;
    private static final int TEXTLABELHEIGHT = 200;

    private static final int FINISHBUTTONWIDTH = 50;
    private static final int FINISHBUTTONHEIGHT = 100;

    public EndMenuFrame(String text, GameModel model, MainGameFrame frame)
    {
        this.model = model;
        this.frame = frame;
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        paintFrame(text);
        setVisible(true);
    }

    private void paintFrame(String text)
    {
        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());
        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(TEXTLABELWIDTH, TEXTLABELHEIGHT));
        JButton finishButton = new JButton("Exit to main menu");
        finishButton.setPreferredSize(new Dimension(FINISHBUTTONWIDTH, FINISHBUTTONHEIGHT));
        finishButton.addActionListener((ActionEvent e)-> {
            model.reinitialize();
            frame.reinitialize();
            frame.removeMainPanel();
            model.addObserver(frame);
            model.addTimerObserver(frame);
            model.startGame();
            dispose();
        });
        borderPanel.add(textLabel, BorderLayout.PAGE_START);
        borderPanel.add(finishButton, BorderLayout.CENTER);
        add(borderPanel);
    }
}
