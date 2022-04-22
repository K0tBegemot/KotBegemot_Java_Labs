public class Entry
{
    public Entry(String str, int num)
    {
        string = str;
        number = num;
    }
    public String GetString()
    {
        return string;
    }
    public int GetNumber()
    {
        return number;
    }
    private String string = null;
    private int number = 0;
}
