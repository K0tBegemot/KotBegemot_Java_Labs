package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Const;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric.Fabric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

public class DefineWorkerTest
{

    @Test
    public void test()
    {
        Assertions.assertDoesNotThrow(() -> {
            Logger logger = Logger.getLogger("Lab2");
            Stack<Double> stack = new Stack<>();
            String[] array = {"a", "2"};
            HashMap<String, Double> map = new HashMap<>();
            ExecutionContext context = new ExecutionContext(stack, array, map);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::readConfigFile);
            CommandWorker define = fabric.createCommandWorker("DEFINE");
            define.executeCommand(context);
            Assertions.assertEquals(2, context.aliasMap.get(array[0]));
        });
    }
}