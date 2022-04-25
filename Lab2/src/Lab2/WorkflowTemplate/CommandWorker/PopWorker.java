package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Exception.CommandExecuteException;

import java.util.EmptyStackException;

public class PopWorker extends CommandWorker
{
    @Override
    public void ExecuteCommand(ExecutionContext context) throws CommandExecuteException
    {
        if (context.argArray.length == 0)
        {
            try
            {
                context.stackOfNumbers.pop();
            }
            catch(EmptyStackException e)
            {
                throw (CommandExecuteException) new CommandExecuteException("There is no number on stack. Command Pop.").initCause(e);
            }
        } else
        {
            Double tmp = null;
            try
            {
                tmp = Double.valueOf(context.argArray[0]);
            } catch (NumberFormatException e)//normal
            {
                try
                {
                    context.aliasMap.put(context.argArray[0], context.stackOfNumbers.pop());
                }
                catch(EmptyStackException ee)
                {
                    throw (CommandExecuteException) new CommandExecuteException("There is no number on stack. Command Pop.").initCause(ee);
                }
            }
            if (tmp != null)//exception
            {
                throw new CommandExecuteException("It is forbidden to use numbers as variable names in order to avoid situations like 2 + 2 = 6 if the type Pop 3. Command Pop");
            }
        }
    }
}
