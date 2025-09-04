package by.rublevskaya.matrix.logic;

import by.rublevskaya.matrix.core.Matrix;
import by.rublevskaya.matrix.service.MatrixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class RangeSearchTest {

    private RangeSearch rangeSearch;
    private MatrixService matrixService;
    private Matrix matrix;

    @BeforeEach
    void setUp() {
        matrixService = mock(MatrixService.class);
        rangeSearch = new RangeSearch(matrixService);
        matrix = new Matrix();
    }

    @Test
    void testFindWordsInRange() {
        when(matrix.getSize()).thenReturn(3);
        when(matrixService.readWord(matrix, 0)).thenReturn(List.of(0, 0, 1, 1)); // 3
        when(matrixService.readWord(matrix, 1)).thenReturn(List.of(1, 0, 0, 1)); // 9
        when(matrixService.readWord(matrix, 2)).thenReturn(List.of(0, 1, 1, 0)); // 6
        rangeSearch.findWordsInRange(matrix, 4, 10);
        verify(matrixService, times(3)).readWord(eq(matrix), anyInt());
        verify(matrixService).readWord(matrix, 1);
        verify(matrixService).readWord(matrix, 2);
    }
}