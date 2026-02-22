public interface AddOnPricing {
    boolean supports(AddOn addOn);
    Money monthlyFee();
}