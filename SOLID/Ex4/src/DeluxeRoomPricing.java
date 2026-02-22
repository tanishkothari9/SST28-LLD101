public class DeluxeRoomPricing implements RoomPricing {
    @Override public boolean supports(int roomType) { return true; } // default fallback
    @Override public Money monthlyBase() { return new Money(16000.0); }
}