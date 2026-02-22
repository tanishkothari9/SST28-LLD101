import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final MonthlyFeeCalculator monthlyCalc;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this(repo, defaultMonthlyCalculator());
    }

    public HostelFeeCalculator(FakeBookingRepo repo, MonthlyFeeCalculator monthlyCalc) {
        this.repo = repo;
        this.monthlyCalc = monthlyCalc;
    }

    private static MonthlyFeeCalculator defaultMonthlyCalculator() {
        List<RoomPricing> room = List.of(
                new SingleRoomPricing(),
                new DoubleRoomPricing(),
                new TripleRoomPricing(),
                new DeluxeRoomPricing() // fallback last
        );

        List<AddOnPricing> addOns = List.of(
                new MessAddOnPricing(),
                new LaundryAddOnPricing(),
                new GymAddOnPricing()
        );

        return new MonthlyFeeCalculator(room, addOns);
    }

    public void process(BookingRequest req) {
        Money monthly = monthlyCalc.calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // keep exactly
        repo.save(bookingId, req, monthly, deposit);
    }
}