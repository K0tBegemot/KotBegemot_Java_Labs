package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker.CommandWorker;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker.ExecutionContext;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric.Fabric;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

public class WorkflowTemplate
{
    private static final int MAXCOMMANDQUEUESIZE = 1000;
    private static final String CLASSNAME = "WorkflowTemplate";
    private static final String[] METODNAME = {"ReadQueueFromFile", "ExecuteWorkflowTemplate"};

    private int numberOfReadedCommandInCF;

    private Fabric fabric;
    private String commandFileName;
    private Queue<Command> commandQueue;
    private Stack<Double> stackOfNumbers;
    private HashMap<String , Double> aliasMap;
    private HashMap<String, CommandWorker> commandWorkerHashMap;
    private Logger logger;

    public WorkflowTemplate(String configFN, String commandFN, Logger log)
    {
        fabric = new Fabric(configFN, log);
        commandFileName = commandFN;
        commandQueue = new LinkedList<>();
        stackOfNumbers = new Stack<>();
        aliasMap = new HashMap<>();
        commandWorkerHashMap = new HashMap<>();
        logger = log;
        numberOfReadedCommandInCF = 0;
    }

    private void readQueueFromFile(Scanner reader) throws CommandFileException
    {
        if(reader == null)
        {
            var e = new CommandFileException("Null reader parameter.");
            logger.throwing(CLASSNAME, METODNAME[0], e);
            throw e;
        }
        try
        {
            int counter = 0;
            while(reader.hasNextLine() && counter < MAXCOMMANDQUEUESIZE)
            {
                String line = reader.nextLine();
                String[] wordArr = line.split(Const.REGEX);
                if(wordArr.length < 1)
                {
                    var e = new CommandFileException("Bad string without any command. Only delimiters");
                    logger.throwing(CLASSNAME, METODNAME[0], e);
                    throw e;
                }else
                {
                    String commandName = wordArr[0];
                    if(commandName.charAt(0) != '#')
                    {
                        wordArr = Arrays.copyOfRange(wordArr, 1, wordArr.length);
                        commandQueue.add(new Command(commandName, wordArr));
                        counter += 1;
                        numberOfReadedCommandInCF += 1;
                    }
                }
            }
        }
        catch(PatternSyntaxException e)
        {
            logger.log(Level.FINER, "", e);
            var ee = new CommandFileException("Regular Expression Exception.");
            logger.throwing(CLASSNAME, METODNAME[0], ee);
            throw ee;
        }
    }

    public void executeWorkflowTemplate() throws WorkflowTemplateException
    {
        try {
            fabric.readConfigFile();
        }
        catch(ConfigFileException e)
        {
            logger.log(Level.FINER, "", e);
            var ee = new WorkflowTemplateException();
            logger.throwing(CLASSNAME, METODNAME[1], ee);
            throw ee;
        }
        try(Scanner reader = new Scanner(Path.of(commandFileName)))
        {
            while(reader.hasNextLine())
            {
                try {
                    readQueueFromFile(reader);
                }
                catch(CommandFileException e)
                {
                    logger.log(Level.FINER, "", e);
                    var ee = new WorkflowTemplateException();
                    logger.throwing(CLASSNAME, METODNAME[1], ee);
                    throw ee;
                }
                while(commandQueue.size() > 0)
                {
                    Command executedCommand = commandQueue.poll();
                    CommandWorker executedWorker = commandWorkerHashMap.get(executedCommand.commandName);
                    if(executedWorker == null)
                    {
                        try {
                            executedWorker = fabric.createCommandWorker(executedCommand.commandName);
                        }
                        catch(NonExistentCommandWorkerException e)
                        {
                            logger.log(Level.WARNING, "", e);
                            continue;
                        }
                        commandWorkerHashMap.put(executedCommand.commandName, executedWorker);
                    }
                    try {
                        executedWorker.executeCommand(new ExecutionContext(stackOfNumbers, executedCommand.argArray, aliasMap));
                    }
                    catch(CommandExecuteException e)
                    {
                        logger.log(Level.WARNING, "", e);
                    }
                }
            }
        }
        catch(IOException ee)
        {
            System.out.println(ee.getMessage());
            throw new WorkflowTemplateException();
        }
    }
}
