import java.util.List;

public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public boolean check(StudentProfile s, List<String> reasons) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            reasons.add("disciplinary flag present");
            return false;
        }
        return true;
    }
}