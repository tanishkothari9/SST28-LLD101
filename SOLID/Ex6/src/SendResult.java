public class SendResult {
    public final boolean success;
    public final String errorMsg;

    private SendResult(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public static SendResult ok() { return new SendResult(true, null); }
    public static SendResult error(String msg) { return new SendResult(false, msg); }
}
