package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric;

import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Const;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class FabricTest {

    @Test
    void createCommandWorker()
    {
        Logger logger = Logger.getLogger("Lab2");
        Fabric fabric = new Fabric(Const.configFile, logger);
        assertDoesNotThrow(fabric::readConfigFile);
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker PopW = fabric.createCommandWorker("POP");
            Assertions.assertTrue(PopW instanceof PopWorker);
                                            });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker PushW = fabric.createCommandWorker("PUSH");
            Assertions.assertTrue(PushW instanceof PushWorker);
                                            });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker AddW = fabric.createCommandWorker("+");
            Assertions.assertTrue(AddW instanceof AddWorker);
                                            });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker SubW = fabric.createCommandWorker("-");
            Assertions.assertTrue(SubW instanceof SubWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker MulW = fabric.createCommandWorker("*");
            Assertions.assertTrue(MulW instanceof MulWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker DivW = fabric.createCommandWorker("/");
            Assertions.assertTrue(DivW instanceof DivWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker SqrtW = fabric.createCommandWorker("SQRT");
            Assertions.assertTrue(SqrtW instanceof SqrtWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
                    CommandWorker PrintW = fabric.createCommandWorker("PRINT");
            Assertions.assertTrue(PrintW instanceof PrintWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker DefineW = fabric.createCommandWorker("DEFINE");
            Assertions.assertTrue(DefineW instanceof DefineWorker);
        });
    }
}