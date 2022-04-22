public class Counter
{
    public Counter()
    {
        counter = 0;
    }
    public Counter(int number)
    {
        counter = number;
    }
    public void Add() throws OverflowException
    {
        if(counter < Integer.MAX_VALUE)
        {
            counter += 1;
        }else
        {
            throw new OverflowException("Overflow of Counter class object");
        }
    }
    public void Sub() throws OverflowException
    {
        if(counter > Integer.MIN_VALUE)
        {
            counter -= 1;
        }else
        {
            throw new OverflowException("Underflow of Counter class object");
        }
    }

    public int GetCounter()
    {
        return counter;
    }
    private int counter;
}
