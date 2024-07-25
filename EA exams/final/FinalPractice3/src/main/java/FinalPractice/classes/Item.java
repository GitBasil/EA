package FinalPractice.classes;

public class Item {
    private String message;

    public Item() {
    }

    public Item(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Item message(String message) {
        setMessage(message);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            "}";
    }
}
