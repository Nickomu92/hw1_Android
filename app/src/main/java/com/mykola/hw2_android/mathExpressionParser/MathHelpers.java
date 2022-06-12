package com.mykola.hw2_android.mathExpressionParser;

public class MathHelpers {
    /**
     * Метод для знаходження факторіала числа.
     * @param mathExpression - математичний вираз (текстовий рядок).
     * @return - факторіал числа.
     * @throws Exception - помилка.
     */
    public static int factorial(String mathExpression) throws Exception {
        try {
            int result = 1;
            int number = Integer.parseInt(mathExpression);

            for(int i = 1; i <= number; i++) {
                result *= i;
            }
            return result;
        } catch(Exception ex) {
            throw new Exception("Ошибка");
        }
    }

    /**
     * Метод для отримання
     * @param doubleValue
     * @return
     */
    public static String getStringResult(double doubleValue) {
        int intValue = (int)doubleValue;

        if(doubleValue == intValue) {
            return Integer.toString(intValue);
        } else {
            return Double.toString(doubleValue);
        }
    }
}
