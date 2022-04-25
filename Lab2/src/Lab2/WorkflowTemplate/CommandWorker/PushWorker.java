package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class PushWorker extends CommandWorker
{
    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context.argArray.length == 0)
        {
            throw new CommandExecuteException("Too few arguments in Command File. Command Push.");
        }else
        {
            Double tmp = null;
            try
            {
                tmp = Double.valueOf(context.argArray[0]);
            }
            catch(NumberFormatException e)//normal
            {
                Double tmp2 = context.aliasMap.get(context.argArray[0]);
                if(tmp2 != null)//normal
                {
                    context.stackOfNumbers.push(tmp2);
                }else//error
                {
                    throw new CommandExecuteException("There is no variable with name: " + context.argArray[0] + ". Command Push.");
                }
            }
            if(tmp != null)// push number without alias
            {
                context.stackOfNumbers.push(tmp);
            }
        }
    }
}
