package Lab2.WorkflowTemplate;

import Lab2.WorkflowTemplate.CommandWorker.CommandWorker;
import Lab2.WorkflowTemplate.CommandWorker.ExecutionContext;

import java.util.ArrayList;

public class Command
{
    String commandName;
    String[] argArray;

    public Command(String cN, String[] aA)
    {
        commandName = cN;
        argArray = aA;
    }
}
