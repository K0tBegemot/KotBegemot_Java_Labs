package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Const;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric.Fabric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

public class PushWorkerTest {

    @Test
    public void test()
    {
        Assertions.assertDoesNotThrow(() -> {
            Logger logger = Logger.getLogger("Lab2");
            Stack<Double> stack = new Stack<>();
            Double number = 10.4;
            String alias = "wasd";
            String[] array = {alias};
            HashMap<String, Double> map = new HashMap<>();
            map.put(alias, number);
            ExecutionContext context = new ExecutionContext(stack, array, map);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::readConfigFile);
            CommandWorker push = fabric.createCommandWorker("PUSH");
            push.executeCommand(context);
            Assertions.assertEquals(number, context.stackOfNumbers.pop());
        });
    }
}