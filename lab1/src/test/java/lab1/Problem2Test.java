package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void testSegregateEvenAndOddNumbers_ShuffledArray() {
        // given
        int[] input = { 9, 2, 3, 6, 11, 12, 0 };

        // when
        int[] actual = Problem2.segregateEvenAndOddNumbers(input);

        // then
        int[] assumption = { 2, 6, 12, 0, 9, 3, 11 };
        assertArrayEquals(assumption, actual);
    }

    @Test
    void testSegregateEvenAndOddNumbers_SegregatedArray_ShouldLeaveUnchanged() {
        // given
        int[] input = { 4, 0, 22, 13, 1, 91, 3 };

        // when
        int[] actual = Problem2.segregateEvenAndOddNumbers(input);

        // then
        int[] assumption = { 4, 0, 22, 13, 1, 91, 3 }; // same as input
        assertArrayEquals(assumption, actual);
    }

    @Test
    void testSegregateEvenAndOddNumbers_InvertedlySegregatedArray() {
        // given
        int[] input = { 71, 5, 12, 56, 256 };

        // when
        int[] actual = Problem2.segregateEvenAndOddNumbers(input);

        // then
        int[] assumption = { 12, 56, 256, 71, 5 };
        assertArrayEquals(assumption, actual);
    }

}