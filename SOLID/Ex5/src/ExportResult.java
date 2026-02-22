public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean success;
    public final String errorMsg;

    // success
    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.success = true;
        this.errorMsg = null;
    }

    // failure
    public ExportResult(String errorMsg) {
        this.contentType = null;
        this.bytes = new byte[0];
        this.success = false;
        this.errorMsg = errorMsg;
    }
}
