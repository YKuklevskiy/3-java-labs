package lab1;

public class Problem3 {

    /**
     * Метод flattenMatrix преобразует матрицу размера nxm в одномерный массив, записывая сперва элементы первого столбца,
     * затем элементы второго столбца и т.д.
     *
     * @param matrix матрица размера nxm
     * @return одномерный массив
     *
     * ПРИМЕР:
     * Вход: matrix = [[1, 2, 3],
     *                 [4, 5, 6],
     *                 [7, 8, 9]]
     * Выход: [1, 4, 7, 2, 5, 8, 3, 6, 9]
     */
    public static int[] flattenMatrix(int[][] matrix) {
        if(matrix.length == 0)
            return new int[0];

        int matrixHeight = matrix.length;
        int matrixWidth = matrix[0].length;
        int[] resultingSequence = new int[matrixHeight * matrixWidth];

        for(int currentIndex = 0; currentIndex < resultingSequence.length; currentIndex++)
        {
            int rowIndex = currentIndex % matrixHeight;
            int columnIndex = currentIndex / matrixHeight;
            resultingSequence[currentIndex] = matrix[rowIndex][columnIndex];
        }

        return resultingSequence;
    }

}
