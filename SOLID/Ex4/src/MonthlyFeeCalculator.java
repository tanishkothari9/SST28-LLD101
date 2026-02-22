import java.util.List;

public class MonthlyFeeCalculator {
    private final List<RoomPricing> roomPricings;
    private final List<AddOnPricing> addOnPricings;

    public MonthlyFeeCalculator(List<RoomPricing> roomPricings, List<AddOnPricing> addOnPricings) {
        this.roomPricings = roomPricings;
        this.addOnPricings = addOnPricings;
    }

    public Money calculateMonthly(BookingRequest req) {
        Money base = resolveRoomBase(req.roomType);
        Money addOns = calculateAddOns(req.addOns);
        return base.plus(addOns);
    }

    private Money resolveRoomBase(int roomType) {
        for (RoomPricing rp : roomPricings) {
            if (rp.supports(roomType)) return rp.monthlyBase();
        }
        // Should never happen because DeluxeRoomPricing supports all
        return new Money(0.0);
    }

    private Money calculateAddOns(List<AddOn> addOns) {
        Money total = new Money(0.0);
        for (AddOn a : addOns) {
            total = total.plus(resolveAddOnFee(a));
        }
        return total;
    }

    private Money resolveAddOnFee(AddOn addOn) {
        for (AddOnPricing ap : addOnPricings) {
            if (ap.supports(addOn)) return ap.monthlyFee();
        }
        // unknown add-on => 0 (preserves current behavior of ignoring unknowns)
        return new Money(0.0);
    }
}