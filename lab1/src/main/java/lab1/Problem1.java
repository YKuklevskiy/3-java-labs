package lab1;

public class Problem1 {

    /**
     * Метод containsDigitAInHexadecimalRepresentation определяет, содержится ли символ A в шестнадцатиричном
     * представлении числа number.
     *
     * @param number целое положительное число
     * @return true, если шестнадцатиричная запись numbers содержит A
     *         false, если шестнадцатиричная запись numbers не содержит A
     *
     * ПРИМЕР1:
     * Вход: number = 10
     * Выход: true (10 = 0xA, содержит A)
     *
     * ПРИМЕР2:
     * Вход: number = 9
     * Выход: false (9 = 0x9, не содержит A)
     */
    public static boolean containsDigitAInHexadecimalRepresentation(int number) {
        if(number < 0)
            return false;

        int A = 0xA;
        int hexNotationBase = 0x10;

        while(number > 0)
        {
            int rightDigit = number % hexNotationBase;

            if(rightDigit == A)
                return true;

            number /= hexNotationBase;
        }

        return false;
    }









}
