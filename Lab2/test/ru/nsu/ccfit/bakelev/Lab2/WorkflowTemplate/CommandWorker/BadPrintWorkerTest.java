package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Const;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.CommandExecuteException;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric.Fabric;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

public class BadPrintWorkerTest
{
    @Test
    public void test()
    {
        Assertions.assertThrows(CommandExecuteException.class, ()->{
            Logger logger = Logger.getLogger("Lab2");
            Stack<Double> stack = new Stack<>();
            String[] array = new String[0];
            HashMap<String, Double> map = new HashMap<>();
            ExecutionContext context = new ExecutionContext(stack, array, map);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::readConfigFile);
            CommandWorker print = fabric.createCommandWorker("PRINT");
            print.executeCommand(context);
        });
        Assertions.assertThrows(CommandExecuteException.class, ()->{
            Logger logger = Logger.getLogger("Lab2");
            ExecutionContext context = new ExecutionContext(null, null, null);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::readConfigFile);
            CommandWorker print = fabric.createCommandWorker("PRINT");
            print.executeCommand(context);
        });
    }
}
