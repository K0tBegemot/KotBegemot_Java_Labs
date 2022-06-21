import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.Exception.WorkflowTemplateException;
import ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.WorkflowTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static final String configFile = "config.txt";
    public static final String commandFile = "command.txt";
    public static final String loggerName = "Lab2";

    public static void main(String[] args)
    {
        Logger logger = Logger.getLogger(loggerName);
        logger.setLevel(Level.FINER);
        WorkflowTemplate template = new WorkflowTemplate(configFile, commandFile, logger);
        try {
            template.ExecuteWorkflowTemplate();
        }
        catch(WorkflowTemplateException e)
        {
            logger.log(Level.WARNING, "", e);
        }
    }
}
