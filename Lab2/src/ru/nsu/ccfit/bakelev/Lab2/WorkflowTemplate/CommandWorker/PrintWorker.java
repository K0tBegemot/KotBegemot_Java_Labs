package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

import java.util.EmptyStackException;

public class PrintWorker extends CommandWorker
{
    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        try
        {
            System.out.println(context.stackOfNumbers.peek());
        }
        catch(EmptyStackException e)
        {
            throw (CommandExecuteException) new CommandExecuteException("There is no numbers on stack. Command Print.").initCause(e);
        }
    }
}
