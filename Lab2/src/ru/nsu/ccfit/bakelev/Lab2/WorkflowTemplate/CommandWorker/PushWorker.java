package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class PushWorker extends CommandWorker
{
    private static final int NULLARGS = 0;
    private static final int INDEXOFALIAS = 0;
    @Override
    public void executeCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context==null || context.stackOfNumbers == null || context.argArray == null || context.aliasMap == null)
        {
            throw new CommandExecuteException("Null ExecutionContext or needed field of ExecutionContext. Command Push");
        }else {
            if (context.argArray.length == NULLARGS) {
                throw new CommandExecuteException("Too few arguments in Command File. Command Push.");
            } else {
                Double tmp = null;
                try {
                    tmp = Double.valueOf(context.argArray[INDEXOFALIAS]);
                } catch (NumberFormatException e)//normal
                {
                    Double tmp2 = context.aliasMap.get(context.argArray[INDEXOFALIAS]);
                    if (tmp2 != null)//normal
                    {
                        context.stackOfNumbers.push(tmp2);
                    } else//error
                    {
                        throw new CommandExecuteException("There is no variable with name: " + context.argArray[INDEXOFALIAS] + ". Command Push.");
                    }
                }
                if (tmp != null)// push number without alias
                {
                    context.stackOfNumbers.push(tmp);
                }
            }
        }
    }
}
