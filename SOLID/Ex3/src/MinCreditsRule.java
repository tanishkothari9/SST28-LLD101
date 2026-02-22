import java.util.List;

public class MinCreditsRule implements EligibilityRule {
    private final int minCredits;

    public MinCreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public boolean check(StudentProfile s, List<String> reasons) {
        if (s.earnedCredits < minCredits) {
            reasons.add("credits below 20"); // must match output exactly
            return false;
        }
        return true;
    }
}