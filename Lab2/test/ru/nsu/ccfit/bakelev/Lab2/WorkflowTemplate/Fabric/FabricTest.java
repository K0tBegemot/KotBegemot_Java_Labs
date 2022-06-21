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
        assertDoesNotThrow(fabric::ReadConfigFile);
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker PopW = fabric.CreateCommandWorker("POP");
            Assertions.assertTrue(PopW instanceof PopWorker);
                                            });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker PushW = fabric.CreateCommandWorker("PUSH");
            Assertions.assertTrue(PushW instanceof PushWorker);
                                            });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker AddW = fabric.CreateCommandWorker("+");
            Assertions.assertTrue(AddW instanceof AddWorker);
                                            });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker SubW = fabric.CreateCommandWorker("-");
            Assertions.assertTrue(SubW instanceof SubWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker MulW = fabric.CreateCommandWorker("*");
            Assertions.assertTrue(MulW instanceof MulWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker DivW = fabric.CreateCommandWorker("/");
            Assertions.assertTrue(DivW instanceof DivWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker SqrtW = fabric.CreateCommandWorker("SQRT");
            Assertions.assertTrue(SqrtW instanceof SqrtWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
                    CommandWorker PrintW = fabric.CreateCommandWorker("PRINT");
            Assertions.assertTrue(PrintW instanceof PrintWorker);
        });
        Assertions.assertDoesNotThrow(() -> {
            CommandWorker DefineW = fabric.CreateCommandWorker("DEFINE");
            Assertions.assertTrue(DefineW instanceof DefineWorker);
        });
    }
}