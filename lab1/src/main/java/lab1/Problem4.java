package lab1;

import java.util.Arrays;

public class Problem4 {

    /**
     * Метод isGeometricProgression определяет, является ли данная последовательность чисел numbers геометрической
     * прогрессией (возможно, при перестановке элементов)
     *
     * @param numbers строка, содержащая n положительных целых чисел, разделенных запятой
     * @return true, если последовательность является геометрической прогрессией
     *         false, если последовательность не является геометрической прогрессией
     *
     * ПРИМЕР1:
     * Вход: numbers = "1,2,4,8,16"
     * Выход: true
     *
     * ПРИМЕР2:
     * Вход: numbers = "16,2,8,1,4"
     * Выход: true (так как в результате перестановки элементов можно получить геометрическую прогрессию [1,2,4,8,16])
     *
     * ПРИМЕР3:
     * Вход: numbers = "2,3,5"
     * Выход: false
     */
    public static boolean isGeometricProgression(String numbers) {
        if(numbers.isBlank())
            return true;
        int[] sequence = getNumberSequenceFromString(numbers);
        Arrays.sort(sequence); // to check progression independently of permutation
        boolean result = isSortedGeometricProgression(sequence);
        return result;
    }

    private static int[] getNumberSequenceFromString(String numbers){
        String[] splitNumberStrings = numbers.strip().split(",+ *");
        int[] resultingSequence = new int[splitNumberStrings.length];

        for (int i = 0; i < resultingSequence.length; i++)
            resultingSequence[i] = Integer.parseInt(splitNumberStrings[i]);

        return resultingSequence;
    }

    private static boolean isSortedGeometricProgression(int[] sequence){
        if(sequence.length <= 2)
            return true;

        int commonRatio = sequence[1] / sequence[0];

        for (int i = 2; i < sequence.length; i++) {
            if(sequence[i] != sequence[i-1] * commonRatio)
                return false;
        }

        return true;
    }

}
