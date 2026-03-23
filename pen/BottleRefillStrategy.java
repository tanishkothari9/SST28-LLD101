package pen;

public class BottleRefillStrategy implements RefillStrategy {
    @Override
    public void refill() {
        System.out.println("Drawing ink from a bottle using a converter.");
    }
}