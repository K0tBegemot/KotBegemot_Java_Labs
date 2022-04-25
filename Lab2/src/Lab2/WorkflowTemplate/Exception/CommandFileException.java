package Lab2.WorkflowTemplate.Exception;

public class CommandFileException extends Exception
{
    public CommandFileException()
    {
        this("");
    }

    public CommandFileException(String message)
    {
        super("Exception while reading Command File. " + message + "\n");
    }
}
