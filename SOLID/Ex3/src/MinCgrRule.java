import java.util.List;

public class MinCgrRule implements EligibilityRule {
    private final double minCgr;

    public MinCgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public boolean check(StudentProfile s, List<String> reasons) {
        if (s.cgr < minCgr) {
            reasons.add("CGR below 8.0"); // must match output exactly
            return false;
        }
        return true;
    }
}