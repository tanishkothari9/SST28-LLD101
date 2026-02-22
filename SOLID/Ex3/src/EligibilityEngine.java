import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this(store, defaultRules(new RuleInput()));
    }

    // injection-friendly: you can pass a different rule list
    public EligibilityEngine(FakeEligibilityStore store, List<EligibilityRule> rules) {
        this.store = store;
        this.rules = rules;
    }

    private static List<EligibilityRule> defaultRules(RuleInput input) {
        // IMPORTANT: keep same order as old else-if chain
        return List.of(
                new DisciplinaryFlagRule(),
                new MinCgrRule(input.minCgr),
                new MinAttendanceRule(input.minAttendance),
                new MinCreditsRule(input.minCredits)
        );
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        // OCP: no giant conditional chain, just iterate rules
        for (EligibilityRule rule : rules) {
            boolean ok = rule.check(s, reasons);
            if (!ok) {
                status = "NOT_ELIGIBLE";
                break; // preserve old behavior: first failing rule only
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}