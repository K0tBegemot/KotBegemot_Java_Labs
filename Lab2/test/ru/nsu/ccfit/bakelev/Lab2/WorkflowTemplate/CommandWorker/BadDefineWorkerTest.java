package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Const;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric.Fabric;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

public class BadDefineWorkerTest
{
    @Test
    public void test()
    {
        Assertions.assertThrows(CommandExecuteException.class, ()->{
            Logger logger = Logger.getLogger("Lab2");
            Stack<Double> stack = new Stack<>();
            String[] array = {"a"};
            HashMap<String, Double> map = new HashMap<>();
            ExecutionContext context = new ExecutionContext(stack, array, map);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::readConfigFile);
            CommandWorker define = fabric.createCommandWorker("DEFINE");
            define.executeCommand(context);
        });
        Assertions.assertThrows(CommandExecuteException.class, ()->{
            Logger logger = Logger.getLogger("Lab2");
            ExecutionContext context = new ExecutionContext(null, null, null);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::readConfigFile);
            CommandWorker define = fabric.createCommandWorker("DEFINE");
            define.executeCommand(context);
        });
    }
}
