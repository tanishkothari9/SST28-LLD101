package pen;

public class PenFactory {
    public static Pen createPen(PenType type, String color, MechanismType mechanism) {
        WriteStrategy writeStrategy;
        RefillStrategy refillStrategy;
        OpenCloseStrategy openCloseStrategy;

        switch (type) {
            case BALLPOINT:
                writeStrategy = new BallpointWriteStrategy();
                refillStrategy = new TubeRefillStrategy();
                break;
            case GEL:
                writeStrategy = new GelWriteStrategy();
                refillStrategy = new TubeRefillStrategy();
                break;
            case INK:
                writeStrategy = new InkWriteStrategy();
                refillStrategy = new BottleRefillStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown Pen Type");
        }

        switch (mechanism) {
            case CAP:
                openCloseStrategy = new CapStrategy();
                break;
            case CLICK:
                openCloseStrategy = new ClickStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown Mechanism Type");
        }

        return new Pen(color, writeStrategy, refillStrategy, openCloseStrategy);
    }
}
