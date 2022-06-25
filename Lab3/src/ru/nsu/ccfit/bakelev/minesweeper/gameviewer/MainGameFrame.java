package ru.nsu.ccfit.bakelev.minesweeper.gameviewer;

import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class MainGameFrame extends JFrame implements GameModelObserverInterface, FrameObserverInterface
{
    private static final int DHEIGHT = 100;
    private static final int CELLSIZE = 32;

    private static final int SCROLLLENGTH = 100;

    private static final int INFOHEIGHT = 50;
    private static final String TIMESTRING = "time - ";

    private static final int TIMELENGTH = 100;
    private static final int TIMEHEIGHT = 80;

    private static final int ZEROFRAMELENGTH = 0;

    private static final int FLAGLABELLENGTH = 150;
    private static final int FLAGLABELHEIGHT = 50;

    private GameModel model;
    private int width;
    private int length;
    private ArrayList<JButton> arrayOfCells;
    private JPanel grid;
    private JPanel info;
    private JPanel main;
    private JTable scoreTable;
    private JScrollPane scrollTable;
    private JLabel timeLabel;
    private JLabel numberOfFlagsLabel;
    private StartMenuFrame startFrame;
    private EndMenuFrame endFrame;
    private long time;
    private boolean mainGameFrameInitialisation;

    public MainGameFrame(GameModel model)
    {
        super("Minesweeper");
        this.model = model;
        reinitialize();
    }

    @Override
    public void update(GameStates state)
    {
        switch(state)
        {
            case START ->
                    {
                        initStartMenu();
                    }

            case GAME -> {
                draw();
            }
            case VICTORY ->
                    {
                        draw();
                        endFrame = new EndMenuFrame("Congratulations. You win. Your time - " + Long.toString(time), model, this);
                    }
            case DEFEAT ->
                    {
                        draw();
                        endFrame = new EndMenuFrame("You stepped on mine. Game over", model, this);
                    }
        }
    }

    @Override
    public void update(long time)
    {
        this.time = time;
        drawTime(time);
    }

    @Override
    public void update(FrameStates state)
    {
        switch(state)
        {
            case MENUCLOSED ->
            {
                if(!mainGameFrameInitialisation)
                {
                    initFrameElements();
                    SizeOfField tmp = model.getDifficultyLevel().getSizeOfField();
                    setSize(tmp.numberOfLines * CELLSIZE + DHEIGHT, tmp.numberOfColumns * CELLSIZE + 3 * DHEIGHT);
                    mainGameFrameInitialisation = true;
                    setVisible(true);
                }
            }
            default -> {}
        }
    }

    public void reinitialize()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        arrayOfCells = new ArrayList<>();
        mainGameFrameInitialisation = false;
        time = 0;
    }

    public void removeMainPanel()
    {
        remove(main);
    }

    private void draw()
    {
        drawGrid();
        drawFlagLabel();
    }

    private void initStartMenu()
    {
        startFrame = new StartMenuFrame(model);
        startFrame.addObserver(this);
    }

    private void drawTime(long time)
    {
        timeLabel.setText(TIMESTRING + Long.toString(time));
    }

    private void drawGrid()
    {
        int height = model.getDifficultyLevel().getSizeOfField().numberOfLines;
        int width = model.getDifficultyLevel().getSizeOfField().numberOfColumns;
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                Cell cell = model.getCell(new Dot(i, j));
                JButton button = arrayOfCells.get(i * width + j);
                if(cell.isHidden())
                {
                    drawButton(button, cell.getVisibleValue());
                }else
                {
                    drawButton(button, cell.getTrueValue());
                }
            }
        }
    }

    private void drawButton(JButton button, CellValue value)
    {
        switch(value)
        {
            case ONE -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/1.jpg"))));
            case TWO -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/2.jpg"))));
            case THREE -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/3.jpg"))));
            case FOUR -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/4.jpg"))));
            case FIVE -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/5.jpg"))));
            case SIX -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/6.jpg"))));
            case SEVEN -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/7.jpg"))));
            case EIGHT -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/8.jpg"))));
            case MINE -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/mine.jpg"))));
            case EMPTY -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/empty.jpg"))));
            case FLAG -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/flag.jpg"))));
            case DEFAULT -> button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/def.jpg"))));
        }
    }

    private void drawFlagLabel()
    {
        int numberOfFlags = model.getFlagsNumber();
        int numberOfAllFlags = model.getDifficultyLevel().getMinesCount();
        numberOfFlagsLabel.setText(String.format("You set %d flags from %d .", numberOfFlags, numberOfAllFlags));
    }

    private void initFrameElements()
    {
        initGrid();
        initTable();
        initInfoPanel();
        initMainPanel();
    }

    private void initGrid()
    {
        int height = 0;
        int width = 0;
        if(model.getDifficultyLevel() != null)
        {
             height = model.getDifficultyLevel().getSizeOfField().numberOfLines;
             width = model.getDifficultyLevel().getSizeOfField().numberOfColumns;
        }
        grid = new JPanel();
        grid.setLayout(new GridLayout(height, width, 1, 1));
        grid.setPreferredSize(new Dimension(height * CELLSIZE, width * CELLSIZE));
        for(int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                JButton cell = new JButton();
                cell.setName(Integer.toString(i) + " " + Integer.toString(j));
                cell.addMouseListener(new GridListener(model));
                cell.setPreferredSize(new Dimension(CELLSIZE, CELLSIZE));
                cell.setBackground(Color.WHITE);
                cell.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/default.jpg"))));
                arrayOfCells.add(cell);
                grid.add(cell);
            }
        }
    }

    private void initTable()
    {
        String[][] data = model.getScoreTable();
        String[] names = {"№", "Name", "Time"};
        scoreTable = new JTable(data, names);
        scrollTable = new JScrollPane(scoreTable);
        scrollTable.setPreferredSize(new Dimension(width, SCROLLLENGTH));
        scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void initInfoPanel()
    {
        initTimeLabel();
        initFlagLabel();
        info = new JPanel();
        info.setPreferredSize(new Dimension(width, INFOHEIGHT));
        numberOfFlagsLabel.setPreferredSize(new Dimension(FLAGLABELLENGTH, FLAGLABELHEIGHT));
        timeLabel.setPreferredSize(new Dimension(FLAGLABELLENGTH, FLAGLABELHEIGHT));
        info.add(timeLabel);
        info.add(numberOfFlagsLabel);
    }

    private void initTimeLabel()
    {
        timeLabel = new JLabel(TIMESTRING + Long.toString(time));
        timeLabel.setPreferredSize(new Dimension(TIMELENGTH, TIMEHEIGHT));
    }

    private void initFlagLabel()
    {
        int numberOfAllMines = model.getDifficultyLevel().getMinesCount();
        numberOfFlagsLabel = new JLabel(String.format("You set %d flags from %d .", 0, numberOfAllMines));
        //numberOfFlagsLabel.setPreferredSize(new Dimension(TIMELENGTH, TIMEHEIGHT));
    }

    private void initMainPanel()
    {
        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(scrollTable);
        main.add(grid);
        main.add(info);
        add(main);
    }
}
