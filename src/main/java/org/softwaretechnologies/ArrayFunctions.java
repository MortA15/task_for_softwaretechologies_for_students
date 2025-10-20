package org.softwaretechnologies;

public final class ArrayFunctions {

    private ArrayFunctions() {
    }

    /**
     * Функция, меняющая порядок элементов в массиве array на обратный.
     * @param array массив, который будет перевернут.
     */
    public static void reverse(int[] array) {
        int temporary;
        for (int index = 0; index < array.length / 2; index++) {
            temporary = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = temporary;
        }
    }

    /**
     * Функция, заменяющая строки матрицы на столбцы матрицы. Пример:
       1  2  3     1  4  7
       4  5  6     2  5  8
       7  8  9     3  6  9
     * Функция работает только с квадратными матрицами.
     * Если матрица не квадратная, то выведете на экран сообщение:
       Матрица не квадратная
     * @param matrix матрица, в которой столбцы будут заменены на строки.
     */
    public static void rotateMatrix(int[][] matrix) {
        // Проверка на квадратность матрицы
        if (matrix.length != matrix[0].length) {
            System.out.println("Матрица не квадратная");
            return;
        }

        int temporary;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length - row; col++) {
                temporary = matrix[row][col + row];
                matrix[row][col + row] = matrix[col + row][row];
                matrix[col + row][row] = temporary;
            }
        }
    }
}
