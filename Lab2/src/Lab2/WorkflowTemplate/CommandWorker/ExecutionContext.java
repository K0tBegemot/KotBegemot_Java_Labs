package Lab2.WorkflowTemplate.CommandWorker;

import java.util.HashMap;
import java.util.Stack;

public class ExecutionContext
{
    Stack<Double> stackOfNumbers;
    HashMap<String, Double> aliasMap;
    String[] argArray;

    public ExecutionContext(Stack<Double> sOF, String[] aA, HashMap<String, Double> aM)
    {
        stackOfNumbers = sOF;
        argArray = aA;
        aliasMap = aM;
    }
}
