import java.util.List;

public class MinAttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public MinAttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public boolean check(StudentProfile s, List<String> reasons) {
        if (s.attendancePct < minAttendance) {
            reasons.add("attendance below 75"); // must match output exactly
            return false;
        }
        return true;
    }
}