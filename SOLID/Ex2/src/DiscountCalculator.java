import java.util.List;

public class DiscountCalculator {

    public double discountAmount(String customerType, double subtotal, List<OrderLine> lines) {
        return DiscountRules.discountAmount(customerType, subtotal, lines.size());
    }
}