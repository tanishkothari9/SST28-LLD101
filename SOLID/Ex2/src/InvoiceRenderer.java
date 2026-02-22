import java.util.List;
import java.util.Map;

public class InvoiceRenderer {

    public String render(InvoiceData data, Map<String, MenuItem> menu) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(data.invoiceId).append("\n");

        for (OrderLine l : data.lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        BillSummary s = data.summary;
        out.append(String.format("Subtotal: %.2f\n", s.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", s.taxPercent, s.tax));
        out.append(String.format("Discount: -%.2f\n", s.discount));
        out.append(String.format("TOTAL: %.2f\n", s.total));


        return InvoiceFormatter.identityFormat(out.toString());
    }
}