package jms.domain;

public class Calculator {
    private static double result = 0;
    public static void performAction(CalculatorTransaction calculatorTransaction){
        switch (calculatorTransaction.getOperation()) {
            case "+":
                result += calculatorTransaction.getNumber();
            break;
            case "-":
                result -= calculatorTransaction.getNumber();
            break;
            case "*":
                result *= calculatorTransaction.getNumber();
            break;
            default:
                break;
        }
    }
    public static double getResult() {
        return result;
    }
}
