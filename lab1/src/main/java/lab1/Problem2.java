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
        copyEvenThenOddNumbersIntoArray(segregatedArray, array);
        return segregatedArray;
    }

    private static void copyEvenThenOddNumbersIntoArray(int[] newArray, int[] sourceArray){
        int oddNumbersStartingIndex = copyEvenNumbersIntoArrayAndReturnTheirCount(newArray, sourceArray);
        copyOddNumbersIntoArray(newArray, sourceArray, oddNumbersStartingIndex);
    }

    private static int copyEvenNumbersIntoArrayAndReturnTheirCount(int[] newArray, int[] sourceArray){
        int evenNumberCount = 0;
        for (int number : sourceArray) {
            if(number % 2 == 0){
                newArray[evenNumberCount] = number;
                evenNumberCount++;
            }
        }
        return evenNumberCount;
    }

    private static void copyOddNumbersIntoArray(int[] newArray, int[] sourceArray, int startingIndex){
        for (int number : sourceArray) {
            if(number % 2 == 1){
                newArray[startingIndex] = number;
                startingIndex++;
            }
        }
    }

}
