public class TaxCalculator {

    public double taxPercent(String customerType) {
        return TaxRules.taxPercent(customerType);
    }

    public double taxAmount(double subtotal, double taxPercent) {
        return subtotal * (taxPercent / 100.0);
    }
}