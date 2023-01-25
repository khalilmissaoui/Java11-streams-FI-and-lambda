import java.time.LocalDateTime;


public class ErrorLogMessage {

    LocalDateTime date;
    String message;


    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
