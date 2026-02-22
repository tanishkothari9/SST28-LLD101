import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private int invoiceSeq = 1000;

    private final InvoiceStore store;
    private final BillingCalculator calculator;
    private final InvoiceRenderer renderer = new InvoiceRenderer();

    // Default wiring constructor (so Main stays simple)
    public CafeteriaSystem() {
        this(new FileStoreInvoiceStore(new FileStore()),
                new BillingCalculator(new DefaultTaxPolicy(), new DefaultDiscountPolicy()));
    }

    // Injectable constructor (for testing / future)
    public CafeteriaSystem(InvoiceStore store, BillingCalculator calculator) {
        this.store = store;
        this.calculator = calculator;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        BillSummary summary = calculator.compute(customerType, lines, menu);

        String printable = renderer.render(new InvoiceData(invId, lines, summary), menu);

        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}