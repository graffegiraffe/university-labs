package by.rublevskaya.matrix;

import by.rublevskaya.matrix.core.Matrix;
import by.rublevskaya.matrix.service.MatrixService;
import by.rublevskaya.matrix.utils.MatrixPrinter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        MatrixService matrixService = new MatrixService();
        System.out.println("Инициализация матрицы случайными числами:");
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                matrix.getMatrixState()[i][j] = (int) (Math.random() * 2);
            }
        }
        MatrixPrinter.print(matrix);
        System.out.println("\nЗапись слов в диагональную структуру:");
        List<Integer> word1 = List.of(1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0);
        matrixService.writeWord(matrix, 3, word1);
        System.out.println("Записано слово 1 в диагональ 3: " + word1);
        List<Integer> word2 = List.of(0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1);
        matrixService.writeWord(matrix, 7, word2);
        System.out.println("Записано слово 2 в диагональ 7: " + word2);
        List<Integer> word3 = List.of(1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0);
        matrixService.writeWord(matrix, 11, word3);
        System.out.println("Записано слово 3 в диагональ 11: " + word3);
        System.out.println("\nМатрица после записи слов:");
        MatrixPrinter.print(matrix);
        System.out.println("\nЗапись разрядного столбца:");
        List<Integer> addressColumn = List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1);
        matrixService.writeAddressColumn(matrix, 2, addressColumn);
        System.out.println("Разрядный столбец 2 записан: " + addressColumn);
        System.out.println("\nМатрица после записи разрядного столбца:");
        MatrixPrinter.print(matrix);
        System.out.println("\nЧтение слов и разрядных столбцов:");
        System.out.println("Слово 3: " + matrixService.readWord(matrix, 3));
        System.out.println("Разрядный столбец 2: " + matrixService.readAddressColumn(matrix, 2));
        System.out.println("\nСложение полей с ключом '101':");
        matrixService.addFieldsByKey(matrix, "101");
        System.out.println("Матрица после выполнения сложения полей:");
        MatrixPrinter.print(matrix);
        System.out.println("\nРабота логических операций:");
        List<Integer> logicWord1 = matrixService.readWord(matrix, 3);
        List<Integer> logicWord2 = matrixService.readWord(matrix, 7);
        System.out.println("f7 - Дизъюнкция (ИЛИ) слов 3 и 7, результат записан в диагональ 15:");
    }
}