package org.isep.cleancode;

import  org.isep.cleancode.calculator.Calculator;
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        String expression = "12 + 3 * 2 - 5";
        double restulat = calculator.evaluerExrepessionMath(expression);

        System.out.println("Expression : " + expression);
        System.out.println("Résultat : " + restulat);
    }
}