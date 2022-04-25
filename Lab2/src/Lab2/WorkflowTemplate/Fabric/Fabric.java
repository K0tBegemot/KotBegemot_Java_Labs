package Lab2.WorkflowTemplate.Fabric;

import Lab2.WorkflowTemplate.CommandWorker.CommandWorker;
import Lab2.WorkflowTemplate.Exception.ConfigFileException;
import Lab2.WorkflowTemplate.Exception.NonExistentCommandWorkerException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Lab2.WorkflowTemplate.Const.REGEX;

public class Fabric
{
    private static final String CLASSNAME = "Fabric";
    private static final String[] METODNAME = {"ReadConfigFile","CreateCommandWorker"};
    private String configFileName;
    private HashMap<String, String> configMap;
    private Logger logger;

    public Fabric(String configFN, Logger log)
    {
        configFileName = configFN;
        configMap = new HashMap<>();
        logger = log;
    }

    public void ReadConfigFile() throws ConfigFileException
    {
        try(Scanner reader = new Scanner(Path.of(configFileName)))
        {
            while (reader.hasNextLine())
            {
                String line = reader.nextLine();
                String[] wordArr = line.split(REGEX);
                if(wordArr.length != 2)
                {
                    var e = new ConfigFileException("Too many arguments per line.");
                    logger.throwing(CLASSNAME, METODNAME[0], e);
                    throw e;
                }else
                {
                    try
                    {
                        Class tmp = Class.forName(wordArr[1]);
                        configMap.put(wordArr[0], wordArr[1]);
                    }
                    catch(ClassNotFoundException e)
                    {
                        logger.log(Level.FINER, "", e);
                        var ee =  (ConfigFileException) new ConfigFileException("Invalid class name. Class with this name doesn't exist.").initCause(e);
                        logger.throwing(CLASSNAME, METODNAME[0], ee);
                        throw ee;
                    }
                }
            }
        }
        catch(IOException e)
        {
            logger.log(Level.FINER, "", e);
            var ee =  (ConfigFileException) new ConfigFileException("IOException. Can't open this file.").initCause(e);
            logger.throwing(CLASSNAME, METODNAME[0], ee);
            throw ee;
        }
    }

    public CommandWorker CreateCommandWorker(String commandName) throws NonExistentCommandWorkerException
    {
        CommandWorker retValue = null;
        String className = configMap.get(commandName);
        if(className != null)
        {
            try
            {
                Class commandWorkerClass = Class.forName(className);
                try
                {
                    Object commandWorkerObj = commandWorkerClass.getConstructor().newInstance();
                    if (commandWorkerObj instanceof CommandWorker)
                    {
                        retValue = (CommandWorker) commandWorkerObj;
                    }
                }
                catch(NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
                {
                    logger.log(Level.FINER, "", e);
                    var ee = (NonExistentCommandWorkerException) new NonExistentCommandWorkerException("Exception while working with class Class of class: " + className + ". Command: " + commandName).initCause(e);
                    logger.throwing(CLASSNAME, METODNAME[1], ee);
                    throw ee;
                }
            }
            catch(ClassNotFoundException e)
            {
                logger.log(Level.FINER, "", e);
                var ee =  (NonExistentCommandWorkerException) new NonExistentCommandWorkerException("There is no class with name: " + className + ". Command: " + commandName).initCause(e);
                logger.throwing(CLASSNAME, METODNAME[1], ee);
                throw ee;
            }
        }else
        {
            var e =  new NonExistentCommandWorkerException("there is no command with name: " + commandName);
            logger.throwing(CLASSNAME, METODNAME[1], e);
            throw e;
        }
        return retValue;
    }
}
