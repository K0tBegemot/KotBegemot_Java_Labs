package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class SqrtWorker extends CommandWorker
{
    private static final int NUMBEROFARGS = 1;
    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context.stackOfNumbers.size() < NUMBEROFARGS)
        {
            throw new CommandExecuteException("Too few numbers on stack. Execute command");
        }else
        {
            context.stackOfNumbers.push(Math.sqrt(context.stackOfNumbers.pop()));
        }
    }
}
