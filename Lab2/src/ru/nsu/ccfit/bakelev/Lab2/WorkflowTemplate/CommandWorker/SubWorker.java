package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class SubWorker extends CommandWorker
{
    private static final int NUMBEROFARGS = 2;
    @Override
    public void executeCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context==null || context.stackOfNumbers == null || context.argArray == null || context.aliasMap == null)
        {
            throw new CommandExecuteException("Null ExecutionContext or needed field of ExecutionContext. Command Sub");
        }else {
            if (context.stackOfNumbers.size() < NUMBEROFARGS) {
                throw new CommandExecuteException("Too few arguments on stack. Command Sub");
            } else {
                context.stackOfNumbers.push(context.stackOfNumbers.pop() - context.stackOfNumbers.pop());
            }
        }
    }
}
