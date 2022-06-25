package ru.nsu.ccfit.bakelev.minesweeper.gamemodel;

import java.util.Objects;

public class Dot
{
    private static final int XCOORDINDEX = 0;
    private static final int YCOORDINDEX = 1;
    private static final int DEFAULTVALUE = 0;

    public int x;
    public int y;

    public Dot(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Dot(String coordinates) throws IllegalArgumentException
    {
        String[] coords = coordinates.split(" ");
        if(coords.length != 2)
        {
            throw new IllegalArgumentException("Incorrect format of input");
        }else
        {
            int x, y;
            try {
                x = Integer.parseInt(coords[XCOORDINDEX]);
                y = Integer.parseInt(coords[YCOORDINDEX]);
            }
            catch(NumberFormatException e)
            {
                throw new IllegalArgumentException(e);
            }
            this.x = x;
            this.y = y;
        }
    }

    public Dot()
    {
        this.x = DEFAULTVALUE;
        this.y = DEFAULTVALUE;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }else
        {
            if(o == null || this.getClass() != o.getClass())
            {
                return false;
            }else
            {
                Dot tmp = (Dot)o;
                return (tmp.x == this.x && tmp.y == this.y);
            }
        }
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
