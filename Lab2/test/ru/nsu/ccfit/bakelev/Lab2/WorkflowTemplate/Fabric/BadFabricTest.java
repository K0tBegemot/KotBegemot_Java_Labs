package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Fabric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker.CommandWorker;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.ConfigFileException;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Const;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.NonExistentCommandWorkerException;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BadFabricTest
{
    @Test
    public void test()
    {
        Logger logger = Logger.getLogger("Lab2Test");
        String configFileBad = "configFileBad.txt";
        Fabric fabric = new Fabric(configFileBad, logger);
        Assertions.assertThrows(ConfigFileException.class, fabric::readConfigFile);
        Fabric fabric2 = new Fabric(Const.configFile, logger);
        Assertions.assertThrows(NonExistentCommandWorkerException.class, ()->{
            CommandWorker worker = fabric2.createCommandWorker("BadName");
        });
    }
}
