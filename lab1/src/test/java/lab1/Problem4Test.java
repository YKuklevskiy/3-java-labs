package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem4Test {

    @Test
    void testIsGeometricProgression_Empty() {
        // given
        String input = " ";

        // when
        boolean actual = Problem4.isGeometricProgression(input);

        // then
        assertTrue(actual);
    }

    @Test
    void testIsGeometricProgression_SortedProgression() {
        // given
        String input = "2, 4, 8, 16, 32, 64, 128";

        // when
        boolean actual = Problem4.isGeometricProgression(input);

        // then
        assertTrue(actual);
    }

    @Test
    void testIsGeometricProgression_ShuffledProgression() {
        // given
        String input = "64, 4, 1, 2, 32, 16, 8";

        // when
        boolean actual = Problem4.isGeometricProgression(input);

        // then
        assertTrue(actual);
    }

    @Test
    void testIsGeometricProgression_ShuffledNonProgression() {
        // given
        String input = "3, 6, 81, 27, 9";

        // when
        boolean actual = Problem4.isGeometricProgression(input);

        // then
        assertFalse(actual);
    }

}