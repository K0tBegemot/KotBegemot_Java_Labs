package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public abstract class CommandWorker
{
    public abstract void ExecuteCommand(ExecutionContext context) throws CommandExecuteException;
}
