package bank.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TraceLog {
    private LocalDateTime dateTime;
    private long accountNumber;
    private String operation;
    private Double amount;

    public TraceLog() {}

    public TraceLog(LocalDateTime dateTime, long accountNumber, String operation, Double amount) {
        this.dateTime = dateTime;
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
            ", dateTime='" + getDateTime() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", operation='" + getOperation() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }
}