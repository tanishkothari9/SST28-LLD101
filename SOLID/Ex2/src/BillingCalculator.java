import java.util.List;
import java.util.Map;

public class BillingCalculator {

    private final PricingCalculator pricing = new PricingCalculator();
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;

    public BillingCalculator(TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    public BillSummary compute(String customerType,
                               List<OrderLine> lines,
                               Map<String, MenuItem> menu) {

        double subtotal = pricing.subtotal(lines, menu);

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(customerType, subtotal, lines);

        double total = subtotal + tax - discount;

        return new BillSummary(subtotal, taxPct, tax, discount, total);
    }
}