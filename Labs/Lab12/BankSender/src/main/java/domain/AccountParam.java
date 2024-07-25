package domain;

public class AccountParam {
    private long accountNumber;
    private double amount;
    private long fromAccountNumber;
    private long toAccountNumber;
    private String description;
    private String customerName;
    private String operation;

    public AccountParam() {
    }

    public AccountParam(long accountNumber, double amount, long fromAccountNumber, long toAccountNumber, String description, String customerName, String operation) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.description = description;
        this.customerName = customerName;
        this.operation = operation;
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getFromAccountNumber() {
        return this.fromAccountNumber;
    }

    public void setFromAccountNumber(long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public long getToAccountNumber() {
        return this.toAccountNumber;
    }

    public void setToAccountNumber(long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "{" +
            " accountNumber='" + getAccountNumber() + "'" +
            ", amount='" + getAmount() + "'" +
            ", fromAccountNumber='" + getFromAccountNumber() + "'" +
            ", toAccountNumber='" + getToAccountNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", operation='" + getOperation() + "'" +
            "}";
    }

}
