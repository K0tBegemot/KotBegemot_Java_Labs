package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception;

public class CommandExecuteException extends Exception
{
    public CommandExecuteException()
    {
        this("");
    }

    public CommandExecuteException(String message)
    {
        super("Command wasn't executed. " + message + "\n");
    }
}
