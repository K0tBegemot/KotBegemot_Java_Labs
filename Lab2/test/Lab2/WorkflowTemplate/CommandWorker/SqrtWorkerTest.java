package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Const;
import Lab2.WorkflowTemplate.Fabric.Fabric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class SqrtWorkerTest {

    @Test
    public void executeCommand()
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
            Assertions.assertDoesNotThrow(fabric::ReadConfigFile);
            CommandWorker sqrt = fabric.CreateCommandWorker("SQRT");
            sqrt.ExecuteCommand(context);
            Assertions.assertEquals(Math.sqrt(number), context.stackOfNumbers.pop());
        });
    }
}