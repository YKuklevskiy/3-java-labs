package lab1;

import java.util.Arrays;

public class Problem2 {

    /**
     * Метод segregateEvenAndOddNumbers разделяет четные и нечетные числа в массиве array, т.у. возвращает массив,
     * в котором сперва записаны все четные числа массива array в порядке их следования, а затем все нечетные числа
     * массива array в порядке их следования.
     *
     * @param array массив положительных чисел
     * @return массив с разделенными четными и нечетными числами
     *
     * ПРИМЕР:
     * Вход: array = [2, 1, 5, 6, 8]
     * Выход: [2, 6, 8, 1, 5]
     */
    public static int[] segregateEvenAndOddNumbers(int[] array) {
        int[] segregatedArray = Arrays.copyOf(array, array.length);

        for (int i = 1; i < segregatedArray.length; i++) {
            if(segregatedArray[i] % 2 == 0) {
                moveEvenNumberToLeft(i, segregatedArray);
            }
        }
        return segregatedArray;
    }

    private static void moveEvenNumberToLeft(int numberIndex, int[] array)
    {
        if(numberIndex != 0 && array[numberIndex - 1] % 2 == 1)
        {
            int newNumberIndex = numberIndex - 1;
            swapTwoSuccessiveElementsAtIndex(newNumberIndex, array);
            moveEvenNumberToLeft(newNumberIndex, array);
        }
    }

    private static void swapTwoSuccessiveElementsAtIndex(int firstElementIndex, int[] array)
    {
        int swapVariable = array[firstElementIndex];
        array[firstElementIndex] = array[firstElementIndex + 1];
        array[firstElementIndex + 1] = swapVariable;
    }

}
