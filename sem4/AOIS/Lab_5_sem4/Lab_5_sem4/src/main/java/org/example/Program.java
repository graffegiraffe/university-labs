package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    private static final int[][] DIGITAL_DEVICE_TRUTH_TABLE = {
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, // q3'
            {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1}, // q2'
            {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1}, // q1'
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, // V
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0}, // q3
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0}, // q2
            {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0}, // q1
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, // h3
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1}, // h2
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}  // h1
    };

    // Вывод таблицы истинности
    private static void printDigitalDeviceTruthTable() {
        System.out.println("Таблица истинности цифрового устройства");
        String[] arguments = {"q3'", "q2'", "q1'", "V  ", "q3 ", "q2 ", "q1 ", "h3 ", "h2 ", "h1 "};
        for (int i = 0; i < DIGITAL_DEVICE_TRUTH_TABLE.length; i++) {
            StringBuilder printString = new StringBuilder(arguments[i] + " |");
            for (int j = 0; j < DIGITAL_DEVICE_TRUTH_TABLE[i].length; j++) {
                printString.append(" ").append(DIGITAL_DEVICE_TRUTH_TABLE[i][j]);
            }
            System.out.println(printString);
        }
    }

    private static String minimizeH(int hIndex, List<String> variables) {
        List<int[]> truthRows = new ArrayList<>();
        for (int col = 0; col < 16; col++) {
            int q3 = (col >> 3) & 1; // q3'
            int q2 = (col >> 2) & 1; // q2'
            int q1 = (col >> 1) & 1; // q1'
            int v = col & 1;         // V
            int result = DIGITAL_DEVICE_TRUTH_TABLE[hIndex][col];
            truthRows.add(new int[]{q3, q2, q1, v, result});
        }

        KarnaughMapProcessor processor = new KarnaughMapProcessor(
                variables,
                truthRows,
                false
        );
        BooleanExpressionMinimizer minimizedExpr = processor.computeMinimizedForm();
        return minimizedExpr.toString();
    }

    private static void printH3Minimization() {
        System.out.println("Минимизация h3:");
        List<String> variables = Arrays.asList("q3'", "q2'", "q1'", "V");
        String minimizedH3 = minimizeH(7, variables);
        System.out.println(minimizedH3);
    }

    private static void printH2Minimization() {
        System.out.println("Минимизация h2:");
        List<String> variables = Arrays.asList("q3'", "q2'", "q1'", "V");
        String minimizedH2 = minimizeH(8, variables);
        System.out.println(minimizedH2);
    }

    private static void printH1Minimization() {
        System.out.println("Минимизация h1:");
        List<String> variables = Arrays.asList("q3'", "q2'", "q1'", "V");
        String minimizedH1 = minimizeH(9, variables);
        System.out.println(minimizedH1);
    }

    public static void main(String[] args) {
        printDigitalDeviceTruthTable();
        printH3Minimization();
        printH2Minimization();
        printH1Minimization();
    }
}