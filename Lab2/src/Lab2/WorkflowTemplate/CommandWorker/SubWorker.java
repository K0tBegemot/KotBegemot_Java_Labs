package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Exception.CommandExecuteException;

import java.util.EmptyStackException;

public class SubWorker extends CommandWorker
{
    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context.stackOfNumbers.size() < 2)
        {
            throw new CommandExecuteException("Too few arguments on stack. Command Sub");
        }else
        {
            context.stackOfNumbers.push(context.stackOfNumbers.pop() - context.stackOfNumbers.pop());
        }
    }
}
