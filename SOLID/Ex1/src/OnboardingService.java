import java.util.*;

public class OnboardingService {
    private final StudentStore store;
    private final StudentInputParser parser = new StudentInputParser();
    private final StudentValidator validator = new StudentValidator();
    private final OnBoardingPrinter printer = new OnBoardingPrinter();

    public OnboardingService(StudentStore store) { this.store = store; }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);
        StudentInput input = parser.parse(raw);
        String name = input.name;
        String email = input.email;
        String phone = input.phone;
        String program = input.program;

        // validation inline, printing inline
        List<String> errors = validator.validate(input);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        store.save(rec);
        printer.printSuccess(rec, store.count());
    }
}
