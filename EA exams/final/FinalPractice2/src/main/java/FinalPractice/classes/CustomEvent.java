package FinalPractice.classes;

public class CustomEvent {
    private String message;

    public CustomEvent() {
    }

    public CustomEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            "}";
    }
    
}
