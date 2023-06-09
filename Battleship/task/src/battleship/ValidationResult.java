package battleship;


public enum ValidationResult {
    SUCCESS(""),
    WRONG_LENGTH("") {
        @Override
        public String getMessage(String name) {
            return "\nError! Wrong length of the " + name + "! Try again:\n";
        }
    },
    WRONG_POSITION("\nError! Wrong ship location! Try again:\n"),
    WRONG_OVERALL(""),
    WRONG_OOB("");
    private final String message;

    ValidationResult(String message) {

        this.message = message;
    }

    public String getMessage(String name) {
        return message;
    }

    public String getMessage() {
        return message;
    }
}
