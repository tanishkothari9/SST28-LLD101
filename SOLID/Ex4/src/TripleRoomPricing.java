public class TripleRoomPricing implements RoomPricing {
    @Override public boolean supports(int roomType) { return roomType == LegacyRoomTypes.TRIPLE; }
    @Override public Money monthlyBase() { return new Money(12000.0); }
}