package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Exception.CommandExecuteException;

public class DefineWorker extends CommandWorker
{
    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if(context.argArray.length < 2)
        {
            throw new CommandExecuteException("Too few argument for command Define. ");
        }else
        {
            Double tmp = null;
            try
            {
                tmp = Double.valueOf(context.argArray[0]);
            }
            catch(NumberFormatException e)//normal
            {
                context.aliasMap.put(context.argArray[0], Double.valueOf(context.argArray[1]));
            }
            if(tmp != null)//error
            {
                throw new CommandExecuteException("It is forbidden to use numbers as variable names in order to avoid situations like 2 + 2 = 6 if the type Pop 3. Command Define");
            }
        }
    }
}
