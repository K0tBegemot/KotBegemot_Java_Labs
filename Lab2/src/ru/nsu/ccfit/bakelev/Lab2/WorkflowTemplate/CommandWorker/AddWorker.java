package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class AddWorker extends CommandWorker
{
    private static final int NUMBEROFARGS = 2;

    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context.stackOfNumbers.size() < NUMBEROFARGS)
        {
            throw new CommandExecuteException("Too few arguments on stack. Command Add.");
        }else
        {
            context.stackOfNumbers.push(context.stackOfNumbers.pop() + context.stackOfNumbers.pop());
        }
    }
}
