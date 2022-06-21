package ru.nsu.ccfit.bakelev.Lab2.WorkflowTemplate.CommandWorker;

import java.util.HashMap;
import java.util.Stack;

public class ExecutionContext
{
    public Stack<Double> stackOfNumbers;
    public HashMap<String, Double> aliasMap;
    public String[] argArray;

    public ExecutionContext(Stack<Double> sOF, String[] aA, HashMap<String, Double> aM)
    {
        stackOfNumbers = sOF;
        argArray = aA;
        aliasMap = aM;
    }
}
