package regex;

public class InvalidStudentIdException extends Exception {
    private String invalidId;

    public InvalidStudentIdException(String invalidId) {
        this.invalidId = invalidId;
    }

    public InvalidStudentIdException(String message, String invalidId) {
        super(message);
        this.invalidId = invalidId;
    }

    public String getInvalidId() {
        return invalidId;
    }
}
