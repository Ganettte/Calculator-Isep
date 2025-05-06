package org.isep.cleancode.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public double evaluerExrepessionMath(String expression) {
        if (expression == null || expression.isBlank()) {
            System.out.println("Pas d'expression saisie");
            return 0;
        }

        expression = enleverEspace(expression);

        if (NombreSimple(expression)) {
            return Double.parseDouble(expression);
        }

        List<Double> nombres = extraireNombre(expression);
        List<Character> operateurs = extraireOperateur(expression);

        calculMultiplication(nombres, operateurs);
        return calculAdditionSoustraction(nombres, operateurs);
    }

    private String enleverEspace(String expression) {
        return expression.replaceAll("\\s+", "");
    }

    private boolean NombreSimple(String expression) {
        try {
            Double.parseDouble(expression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Double> extraireNombre(String expression) {
        List<Double> nombres = new ArrayList<>();
        StringBuilder nombreActuel = new StringBuilder();

        for (char caractere : expression.toCharArray()) {
            if (operateur(caractere)) {
                nombres.add(Double.parseDouble(nombreActuel.toString()));
                nombreActuel.setLength(0);
            } else {
                nombreActuel.append(caractere);
            }
        }

        nombres.add(Double.parseDouble(nombreActuel.toString()));
        return nombres;
    }

    private List<Character> extraireOperateur(String expression) {
        List<Character> operateurs = new ArrayList<>();

        for (char caractere : expression.toCharArray()) {
            if (operateur(caractere)) {
                operateurs.add(caractere);
            }
        }

        return operateurs;
    }

    private boolean operateur(char caractere) {
        return caractere == '+' || caractere == '-' || caractere == '*';
    }

    private void calculMultiplication(List<Double> nombres, List<Character> operateurs) {
        for (int i = 0; i < operateurs.size(); ) {
            if (operateurs.get(i) == '*') {
                double result = nombres.get(i) * nombres.get(i + 1);
                nombres.set(i, result);
                nombres.remove(i + 1);
                operateurs.remove(i);
            } else {
                i++;
            }
        }
    }

    private double calculAdditionSoustraction(List<Double> nombres, List<Character> operateurs) {
        double resltat = nombres.get(0);

        for (int i = 0; i < operateurs.size(); i++) {
            char op = operateurs.get(i);
            double next = nombres.get(i + 1);

            if (op == '+') {
                resltat += next;
            } else if (op == '-') {
                resltat -= next;
            }
        }

        return resltat;
    }
}
