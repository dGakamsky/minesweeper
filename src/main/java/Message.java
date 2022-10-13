public class Message {
    public static final String errorMsg = "Please input a number";
    public static final String confirmMsg = "please enter the number again to confirm";

    private Message() {
        throw new IllegalStateException("Utility class");
    }

}
