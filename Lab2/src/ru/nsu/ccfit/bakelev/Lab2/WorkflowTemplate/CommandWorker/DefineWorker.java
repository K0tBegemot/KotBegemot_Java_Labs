package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class DefineWorker extends CommandWorker
{
    private static final int NUMBEROFARGS = 2;

    private static final int INDEXOFALIAS = 0;
    private static final int INDEXOFVALUE = 1;

    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context.argArray.length < NUMBEROFARGS)
        {
            throw new CommandExecuteException("Too few argument for command Define. ");
        }else
        {
            Double tmp = null;
            try
            {
                tmp = Double.valueOf(context.argArray[INDEXOFALIAS]);
            }
            catch(NumberFormatException e)//normal
            {
                context.aliasMap.put(context.argArray[INDEXOFALIAS], Double.valueOf(context.argArray[INDEXOFVALUE]));
            }
            if(tmp != null)//error
            {
                throw new CommandExecuteException("It is forbidden to use numbers as variable names in order to avoid situations like 2 + 2 = 6. Command Define");
            }
        }
    }
}
