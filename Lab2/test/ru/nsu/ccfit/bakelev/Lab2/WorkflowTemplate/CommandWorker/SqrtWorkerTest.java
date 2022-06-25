package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Const;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric.Fabric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

public class SqrtWorkerTest {

    @Test
    public void test()
    {
        Assertions.assertDoesNotThrow(() -> {
            Logger logger = Logger.getLogger("Lab2");
            Stack<Double> stack = new Stack<>();
            String[] array = new String[0];
            HashMap<String, Double> map = new HashMap<>();
            ExecutionContext context = new ExecutionContext(stack, array, map);
            Double number = 100.0;
            stack.push(number);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::readConfigFile);
            CommandWorker sqrt = fabric.createCommandWorker("SQRT");
            sqrt.executeCommand(context);
            Assertions.assertEquals(Math.sqrt(number), context.stackOfNumbers.pop());
        });
    }
}