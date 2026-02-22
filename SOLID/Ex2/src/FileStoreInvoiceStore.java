public class FileStoreInvoiceStore implements InvoiceStore {
    private final FileStore store;

    public FileStoreInvoiceStore(FileStore store) {
        this.store = store;
    }

    @Override
    public void save(String invoiceId, String content) {
        store.save(invoiceId, content);
    }

    @Override
    public int countLines(String invoiceId) {
        return store.countLines(invoiceId);
    }
}