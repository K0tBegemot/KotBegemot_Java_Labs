package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.WorkflowTemplateException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BadWorkflowTemplateTest
{
    @Test
    public void test()
    {
        Logger logger = Logger.getLogger("Lab2Test");
        logger.setLevel(Level.FINER);
        String configFile1 = "config.txt";
        String configFile2 = "configBad.txt";
        String commandFile1 = "command.txt";
        String commandFile2 = "commandBad.txt";
        WorkflowTemplate templateGood = new WorkflowTemplate(configFile1, commandFile1, logger);
        WorkflowTemplate templateBad1 = new WorkflowTemplate(configFile2, commandFile1, logger);
        WorkflowTemplate templateBad2 = new WorkflowTemplate(configFile1, commandFile2, logger);
        WorkflowTemplate templateBad3 = new WorkflowTemplate(configFile2, commandFile2, logger);
        Assertions.assertDoesNotThrow(templateGood::executeWorkflowTemplate);
        Assertions.assertThrows(WorkflowTemplateException.class, templateBad1::executeWorkflowTemplate);
        Assertions.assertThrows(WorkflowTemplateException.class, templateBad2::executeWorkflowTemplate);
        Assertions.assertThrows(WorkflowTemplateException.class, templateBad3::executeWorkflowTemplate);
    }
}
