package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem3Test {

    @Test
    void testFlattenMatrix_Empty() {
        // given
        int[][] input = {  };

        // when
        int[] actual = Problem3.flattenMatrix(input);

        // then
        int[] assumption = {  };
        assertArrayEquals(assumption, actual);
    }

    @Test
    void testFlattenMatrix_NonEmpty() {
        // given
        int[][] input = {
                {9, 2, 3},
                {6, 11, 12},
                {0, 55, 21}
        };

        // when
        int[] actual = Problem3.flattenMatrix(input);

        // then
        int[] assumption = { 9, 6, 0, 2, 11, 55, 3, 12, 21 };
        assertArrayEquals(assumption, actual);
    }

}