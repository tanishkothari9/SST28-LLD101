public interface RoomPricing {
    boolean supports(int roomType);
    Money monthlyBase();
}