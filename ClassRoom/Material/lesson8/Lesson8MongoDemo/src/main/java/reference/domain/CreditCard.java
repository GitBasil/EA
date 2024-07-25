package reference.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CreditCard {
    @Id
    private String cardNumber;
    private String type;
    private String validationDate;

    public CreditCard(String cardNumber, String type, String validationDate) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.validationDate = validationDate;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", type='" + type + '\'' +
                ", validationDate='" + validationDate + '\'' +
                '}';
    }
}
