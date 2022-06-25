package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public abstract class CommandWorker
{
    public abstract void executeCommand(ExecutionContext context) throws CommandExecuteException;
}
