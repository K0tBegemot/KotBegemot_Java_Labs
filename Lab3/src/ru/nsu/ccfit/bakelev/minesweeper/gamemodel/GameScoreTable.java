package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class GameScoreTable implements ScoreTableInterface
{
    private final int visibleLines = 7;
    public static final int columns = ScoreTableRow.columns;
    private static final String PLACER = "_";
    private int numberOfInitialisedRows;
    private ArrayList<ScoreTableRow> scoreTable;
    private final String path = "src/resources/scoreTable.txt";

    GameScoreTable()
    {
        scoreTable = new ArrayList<ScoreTableRow>();
        numberOfInitialisedRows = 0;
        readTable();
        for(int i = 0; i < visibleLines; i++)
        {
            scoreTable.add(new ScoreTableRow(Integer.toString(i + 1), PLACER, PLACER));
        }
    }
    @Override
    public String[][] getAsStringArray()
    {

        String[][] table = new String[visibleLines][columns];
        for(int i = 0; i < visibleLines; i++)
        {
            table[i] = scoreTable.get(i).toString().split(ScoreTableRow.REGEX);
        }
        return table;
    }

    @Override
    public void updateScoreTable(ScoreTableRow row)
    {
        long newTime = Long.parseLong(row.time);
        long currentTime = 0;
        if(numberOfInitialisedRows > 0) {
            for (int i = 0; i < numberOfInitialisedRows; i++) {
                currentTime = Long.parseLong(scoreTable.get(i).time);
                if (newTime < currentTime) {
                    row.indexInTable = Integer.toString(i + 1);
                    scoreTable.add(i, row);
                    numberOfInitialisedRows += 1;
                    for(int j = i + 1; j < numberOfInitialisedRows; j++)
                    {
                        int tmp = Integer.parseInt(scoreTable.get(j).indexInTable);
                        tmp += 1;
                        scoreTable.get(j).indexInTable = Integer.toString(tmp);
                    }
                    break;
                }
            }
        }else
        {
            row.indexInTable = Integer.toString(1);
            scoreTable.remove(0);
            scoreTable.add(0, row);
            numberOfInitialisedRows += 1;
        }
        saveTable();
    }
    public void readTable()
    {
        try(Scanner scanner = new Scanner(Path.of(path)))
        {
            int counter = 0;
            while(scanner.hasNextLine())
            {
                String nextLine = scanner.nextLine();
                scoreTable.add(counter, new ScoreTableRow(nextLine.split(ScoreTableRow.REGEX)));
                if(scoreTable.get(counter).time.equals(PLACER))
                {
                    numberOfInitialisedRows = counter;
                }
                counter += 1;
            }
        }
        catch(IOException  | IllegalArgumentException e)
        {
            e.printStackTrace();
        }
    }

    public void saveTable()
    {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.TRUNCATE_EXISTING))
        {
            for(int i = 0; i < visibleLines; i++)
            {
                writer.write(scoreTable.get(i).toString());
                writer.newLine();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
