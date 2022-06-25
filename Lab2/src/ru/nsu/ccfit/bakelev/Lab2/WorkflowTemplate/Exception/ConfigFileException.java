package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception;

public class ConfigFileException extends Exception
{
    public ConfigFileException()
    {
        this("");
    }
    public ConfigFileException(String message)
    {
        super("ConfigFile Exception. " + message + "\n");
    }
}
