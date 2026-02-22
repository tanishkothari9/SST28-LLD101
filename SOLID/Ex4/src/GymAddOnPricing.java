public class GymAddOnPricing implements AddOnPricing {
    @Override public boolean supports(AddOn addOn) { return addOn == AddOn.GYM; }
    @Override public Money monthlyFee() { return new Money(300.0); }
}