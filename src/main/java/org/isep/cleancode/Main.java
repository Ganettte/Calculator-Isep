package org.isep.cleancode;

import  org.isep.cleancode.calculator.Calculator;
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        String expression = "4.7 + 3 - 2 * 2";
        double restulat = calculator.evaluateMathExpression(expression);

        System.out.println("Expression : " + expression);
        System.out.println("Résultat : " + restulat);
    }
}