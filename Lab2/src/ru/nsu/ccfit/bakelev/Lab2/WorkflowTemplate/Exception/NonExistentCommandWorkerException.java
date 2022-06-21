package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception;

public class NonExistentCommandWorkerException extends Exception
{
    public NonExistentCommandWorkerException()
    {
        this("");
    }

    public NonExistentCommandWorkerException(String message)
    {
        super("Command worker is not created. " + message + "\n");
    }
}
