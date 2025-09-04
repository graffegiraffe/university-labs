package org.example;


import java.util.ArrayList;
import java.util.List;

public class BooleanExpressionMinimizer {
    private final boolean isConjunctive;
    private final List<List<Object[]>> expressionComponents;

    public BooleanExpressionMinimizer(boolean isConjunctive) {
        this.isConjunctive = isConjunctive;
        this.expressionComponents = new ArrayList<>();
    }

    public void appendComponent(List<Object[]> variableSet) {
        expressionComponents.add(variableSet);
    }

    @Override
    public String toString() {
        String outerOperator = isConjunctive ? " ∧ " : " ∨ ";
        String innerOperator = isConjunctive ? " ∨ " : " ∧ ";

        List<String> formattedComponents = new ArrayList<>();
        for (List<Object[]> component : expressionComponents) {
            List<String> variableList = new ArrayList<>();
            for (Object[] var : component) {
                String varName = (String) var[0];
                boolean varState = (boolean) var[1];
                variableList.add(varState ? varName : "¬" + varName);
            }
            formattedComponents.add("(" + String.join(innerOperator, variableList) + ")");
        }
        return String.join(outerOperator, formattedComponents);
    }
}