package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

import java.util.EmptyStackException;

public class PrintWorker extends CommandWorker
{
    @Override
    public void executeCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context==null || context.stackOfNumbers == null || context.argArray == null || context.aliasMap == null)
        {
            throw new CommandExecuteException("Null ExecutionContext or needed field of ExecutionContext. Command Print");
        }else {
            try {
                System.out.println(context.stackOfNumbers.peek());
            } catch (EmptyStackException e) {
                throw (CommandExecuteException) new CommandExecuteException("There is no numbers on stack. Command Print.").initCause(e);
            }
        }
    }
}
