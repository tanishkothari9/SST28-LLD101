import java.util.List;

public class InvoiceData {
    public final String invoiceId;
    public final List<OrderLine> lines;
    public final BillSummary summary;

    public InvoiceData(String invoiceId, List<OrderLine> lines, BillSummary summary) {
        this.invoiceId = invoiceId;
        this.lines = lines;
        this.summary = summary;
    }
}