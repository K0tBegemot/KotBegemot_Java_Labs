package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class SqrtWorker extends CommandWorker
{

    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context.stackOfNumbers.size() < 1)
        {
            throw new CommandExecuteException("Too few numbers on stack. Execute command");
        }else
        {
            context.stackOfNumbers.push(Math.sqrt(context.stackOfNumbers.pop()));
        }
    }
}
