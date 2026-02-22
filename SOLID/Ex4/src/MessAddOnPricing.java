public class MessAddOnPricing implements AddOnPricing {
    @Override public boolean supports(AddOn addOn) { return addOn == AddOn.MESS; }
    @Override public Money monthlyFee() { return new Money(1000.0); }
}