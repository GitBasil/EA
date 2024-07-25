package jms.domain;

public class CalculatorTransaction {
    private String operation;
    private double number;

    public CalculatorTransaction() {
    }

    public CalculatorTransaction(String operation, double number) {
        this.operation = operation;
        this.number = number;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getNumber() {
        return this.number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "{" +
            " operation='" + getOperation() + "'" +
            ", number='" + getNumber() + "'" +
            "}";
    }
    
}
