package by.rublevskaya.model;

import by.rublevskaya.model.matrix.Matrix;
import by.rublevskaya.model.matrix.MatrixPrinter;
import by.rublevskaya.model.operations.FieldSearcher;
import by.rublevskaya.model.operations.LogicOperations;
import by.rublevskaya.model.transformers.BinaryConverter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(16, 16);
        BinaryConverter converter = new BinaryConverter();
        LogicOperations logicOps = new LogicOperations();
        FieldSearcher searcher = new FieldSearcher();

        System.out.println("ЗАПИСЬ СЛОВ:");
        List<Integer> word1 = List.of(1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1);
        matrix.writeWord(3, word1);

        List<Integer> word2 = List.of(0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0);
        matrix.writeWord(7, word2);

        List<Integer> word3 = List.of(1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0);
        matrix.writeWord(11, word3);

        List<Integer> word4 = List.of(0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0);
        matrix.writeWord(4, word4);

        System.out.println(MatrixPrinter.formatMatrix(matrix.getMatrixState()));

        System.out.println("\nЗАПИСЬ РАЗРЯДНОГО СТОЛБЦА:");
        matrix.writeAddressColumn(2, List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1));
        System.out.println(MatrixPrinter.formatMatrix(matrix.getMatrixState()));

        System.out.println("\nЧТЕНИЕ СЛОВ И РАЗРЯДНЫХ СТОЛБЦОВ:");
        System.out.println("Слово №3: " + matrix.readWord(3));
        System.out.println("Разрядный столбец №4: " + matrix.readAddressColumn(4));

        System.out.println("\nЛОГИЧЕСКИЕ ФУНКЦИИ:");

        System.out.println("f7 - дизъюнкция (ИЛИ) для слова 1 и 4 и запись в 15");
        word1 = matrix.readWord(1);
        System.out.println("Word1: " + word1);
        word4 = matrix.readWord(4);
        System.out.println("Word4: " + word4);
        List<Integer> result = logicOps.disjunction(word1, word4);
        System.out.println("Result: " + result);
        matrix.writeWord(15, result);
        System.out.println(MatrixPrinter.formatMatrix(matrix.getMatrixState()));

        System.out.println("f8 - операция Пирса (ИЛИ-НЕ) для слова 2 и 7 и запись в 13");
        word2 = matrix.readWord(2);
        System.out.println("Word2: " + word2);
        List<Integer> word7 = matrix.readWord(7);
        System.out.println("Word7: " + word7);
        result = logicOps.disjunctionNegation(word2, word7);
        System.out.println("Result: " + result);
        matrix.writeWord(13, result);
        System.out.println(MatrixPrinter.formatMatrix(matrix.getMatrixState()));

        System.out.println("f2 - запрет 1-го аргумента (НЕТ) для слова 5 и 3 и запись в 10");
        List<Integer> word5 = matrix.readWord(5);
        System.out.println("Word5: " + word5);
        word3 = matrix.readWord(3);
        System.out.println("Word3: " + word3);
        result = logicOps.firstArgBan(word5, word3);
        System.out.println("Result: " + result);
        matrix.writeWord(10, result);
        System.out.println(MatrixPrinter.formatMatrix(matrix.getMatrixState()));

        System.out.println("f13 - импликация от 1-го элемента ко 2-му (НЕТ-НЕ) для слова 8 и 2 и запись в 11");
        List<Integer> word8 = matrix.readWord(8);
        System.out.println("Word8: " + word8);
        word2 = matrix.readWord(2);
        System.out.println("Word2: " + word2);
        result = logicOps.implication(word8, word2);
        System.out.println("Result: " + result);
        matrix.writeWord(11, result);
        System.out.println(MatrixPrinter.formatMatrix(matrix.getMatrixState()));

        System.out.println("\n\nПоиск величин, заключенных в заданном интервале [0000000000000000] - [0000110000010000]:");
        searcher.searchInRange(
                List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                List.of(0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0),
                List.of(
                        matrix.readWord(0), matrix.readWord(1), matrix.readWord(2), matrix.readWord(3),
                        matrix.readWord(4), matrix.readWord(5), matrix.readWord(6), matrix.readWord(7),
                        matrix.readWord(8), matrix.readWord(9), matrix.readWord(10), matrix.readWord(11),
                        matrix.readWord(12), matrix.readWord(13), matrix.readWord(14), matrix.readWord(15)
                )
        );

        System.out.println("\nСложение полей по заданному V=011:");
        matrix.addFieldsByKey("011");

        System.out.println(MatrixPrinter.formatMatrix(matrix.getMatrixState()));
    }
}