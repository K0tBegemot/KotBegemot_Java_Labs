import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;


public class AddMethods
{
    public static final int DOT = 46;
    public static final String csvFileExtension = ".csv";
    public static final char SEPARATOR = ',';
    public static final String REGEX = "[\\W]+";

    public static String CSVName(String oldTxtName)
    {
        int dotIndex = 0;
        for (int i = oldTxtName.codePointCount(0, oldTxtName.length()) - 1; i > 0; i--)
        {
            int index = oldTxtName.offsetByCodePoints(0, i);
            int cp = oldTxtName.codePointAt(index);
            if (cp == DOT)
            {
                dotIndex = index;
                break;
            }
        }
        String newTxtName = oldTxtName.substring(0, dotIndex) + csvFileExtension;
        return newTxtName;
    }

    public static HashMap<String, Integer> ScanFile(Scanner reader, Counter WordCounter, ArrayList<String> words)
    {
        var WordCounterMap = new HashMap<String, Integer>();
        while (reader.hasNextLine())
        {
            String line = reader.nextLine();
            String[] wordArr = line.split(REGEX);
            for(String i: wordArr)
            {
                if (!WordCounterMap.containsKey(i))
                {
                    words.add(i);
                }
                WordCounter.Add();
                WordCounterMap.put(i, WordCounterMap.getOrDefault(i, 0) + 1);
            }
        }
        return WordCounterMap;
    }

    public static void WriteCSVFile(PrintWriter writer, HashMap<String, Integer> wordMap, Counter wordCounter, ArrayList<String> words)
    {
        ArrayList<Entry> listOfEntries = new ArrayList<Entry>();
        for (String word : words)
        {
            listOfEntries.add(new Entry(word, wordMap.get(word)));
        }
        listOfEntries.sort((Entry a, Entry b) -> {return b.GetNumber() - a.GetNumber();});
        for(Entry entry : listOfEntries)
        {
            writer.printf("%s%c%d%c%.2f\n", entry.GetString(), SEPARATOR, entry.GetNumber(), SEPARATOR, (double)entry.GetNumber() / wordCounter.GetCounter());
        }
    }
}