package ru.nsu.ccfit.bakelev.minesweeper;

import ru.nsu.ccfit.bakelev.minesweeper.gamemodel.GameModel;
import ru.nsu.ccfit.bakelev.minesweeper.gameviewer.GraphicMode;

public class Main {
    private static final String GRAPHICPARAMETER = "GRAPHIC";
    private static final String CONSOLEPARAMETER = "CONSOLE";
    private static final int OPTIONINDEX = 0;

    public static void main(String[] args) {
	if(args.length < 1)
    {
        System.out.println("There is no launch option in console. Try again");
    }else
    {
        GameModel model = new GameModel();
        if(args[OPTIONINDEX].equals(GRAPHICPARAMETER))
        {
            GraphicMode game = new GraphicMode(model);
        }else
        {
            if(args[OPTIONINDEX].equals(CONSOLEPARAMETER))
            {
                //ConsoleGame game = new ConsoleGame(model);
            }else
            {
                System.out.println("Syntax error in console's launch option. Try again");
            }
        }
    }
    }
}
