package org.isep.cleancode.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public double evaluateMathExpression(String expression) {
        if (expression == null || expression.isBlank()) {
            System.out.println("Pas d'expression saisie");
            return 0;
        }

        expression = enleveEspace(expression);

        if (simpleNombre(expression)) {
            return Double.parseDouble(expression);
        }

        List<Double> numbers = extractionNombre(expression);
        List<Character> operators = extractionOperateur(expression);

        multiplication(numbers, operators);
        return addictionSoustraction(numbers, operators);
    }

    private String enleveEspace(String expression) {
        return expression.replaceAll("\\s+", "");
    }

    private boolean simpleNombre(String expression) {
        try {
            Double.parseDouble(expression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Double> extractionNombre(String expression) {
        List<Double> numbers = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (operateur(c)) {
                numbers.add(Double.parseDouble(currentNumber.toString()));
                currentNumber.setLength(0);
            } else {
                currentNumber.append(c);
            }
        }

        numbers.add(Double.parseDouble(currentNumber.toString()));
        return numbers;
    }

    private List<Character> extractionOperateur(String expression) {
        List<Character> operators = new ArrayList<>();

        for (char c : expression.toCharArray()) {
            if (operateur(c)) {
                operators.add(c);
            }
        }

        return operators;
    }

    private boolean operateur(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private void multiplication(List<Double> numbers, List<Character> operators) {
        for (int i = 0; i < operators.size(); ) {
            if (operators.get(i) == '*') {
                double result = numbers.get(i) * numbers.get(i + 1);
                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
            } else {
                i++;
            }
        }
    }

    private double addictionSoustraction(List<Double> numbers, List<Character> operators) {
        double result = numbers.get(0);

        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            double next = numbers.get(i + 1);

            if (op == '+') {
                result += next;
            } else if (op == '-') {
                result -= next;
            }
        }

        return result;
    }
}
