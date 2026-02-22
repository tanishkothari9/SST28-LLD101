public class LaundryAddOnPricing implements AddOnPricing {
    @Override public boolean supports(AddOn addOn) { return addOn == AddOn.LAUNDRY; }
    @Override public Money monthlyFee() { return new Money(500.0); }
}