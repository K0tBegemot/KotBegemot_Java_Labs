
import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Lab1
{
    public static void main(String[] argv)
    {
        if(argv.length < 1)
        {
            System.err.println("You didn't provide a working filename. Restart the program and specify it");
        }
        Scanner reader = null;
        PrintWriter writer = null;
        try
        {
            reader = new Scanner(Path.of(argv[0]));
            writer = new PrintWriter(AddMethods.CSVName(argv[0]));
            Counter wordCounter = new Counter(0);
            ArrayList<String> words = new ArrayList<String>();
            HashMap<String, Integer> wordMap = AddMethods.ScanFile(reader, wordCounter, words);
            AddMethods.WriteCSVFile(writer, wordMap, wordCounter, words);
        }
        catch(IOException e)
        {
            System.err.println("File read error. " + e.getMessage());
        }
        catch(IndexOutOfBoundsException ee)
        {
            System.err.println("There is error in file name. Try again ");
        }
        catch(NullPointerException oe)
        {
            System.err.println("Memory allocation error. " + oe.getMessage());
        }
        catch(OverflowException oee)
        {
            System.err.println(oee.getMessage());
        }
        finally
        {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}
