package Lab2.WorkflowTemplate.CommandWorker;

import Lab2.WorkflowTemplate.Const;
import Lab2.WorkflowTemplate.Fabric.Fabric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class PopWorkerTest {

    @Test
    public void executeCommand()
    {
        Assertions.assertDoesNotThrow(() -> {
            Logger logger = Logger.getLogger("Lab2");
            Stack<Double> stack = new Stack<>();
            String[] array = new String[0];
            HashMap<String, Double> map = new HashMap<>();
            ExecutionContext context = new ExecutionContext(stack, array, map);
            Double[] numbers = {1.2, 99.0};
            stack.push(numbers[0]);
            stack.push(numbers[1]);
            Fabric fabric = new Fabric(Const.configFile, logger);
            Assertions.assertDoesNotThrow(fabric::ReadConfigFile);
            CommandWorker pop = fabric.CreateCommandWorker("POP");
            pop.ExecuteCommand(context);
            Assertions.assertEquals(numbers[0], context.stackOfNumbers.pop());
        });
    }
}