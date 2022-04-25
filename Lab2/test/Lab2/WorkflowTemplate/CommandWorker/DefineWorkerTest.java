package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Const;
import Lab2.WorkflowTemplate.Fabric.Fabric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class DefineWorkerTest
{

    @Test
    public void executeCommand()
    {
        Assertions.assertDoesNotThrow(() -> {
            Logger logger = Logger.getLogger("Lab2");
            Stack<Double> stack = new Stack<>();
            String[] array = {"a", "2"};
            HashMap<String, Double> map = new HashMap<>();
            ExecutionContext context = new ExecutionContext(stack, array, map);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::ReadConfigFile);
            CommandWorker define = fabric.CreateCommandWorker("DEFINE");
            define.ExecuteCommand(context);
            Assertions.assertEquals(2, context.aliasMap.get(array[0]));
        });
    }
}