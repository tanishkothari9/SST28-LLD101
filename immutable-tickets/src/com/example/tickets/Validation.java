package com.example.tickets;

import java.util.regex.Pattern;

/**
 * Central place for validation helpers.
 *
 * Students can extend this as needed.
 */
public final class Validation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern TICKET_CODE_FORMAT = Pattern.compile("^[A-Z0-9-]+$");

    private Validation() {}

    public static void requireNonBlank(String input, String fieldLabel) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldLabel + " must not be blank");
        }
    }

    public static void requireMaxLen(String input, int upperLimit, String fieldLabel) {
        if (input != null && input.length() > upperLimit) {
            throw new IllegalArgumentException(fieldLabel + " must be <= " + upperLimit + " chars");
        }
    }

    public static void requireEmail(String emailAddr, String fieldLabel) {
        requireNonBlank(emailAddr, fieldLabel);
        if (!EMAIL_PATTERN.matcher(emailAddr).matches()) {
            throw new IllegalArgumentException(fieldLabel + " must be a valid email");
        }
    }

    public static void requireTicketId(String code) {
        requireNonBlank(code, "id");
        requireMaxLen(code, 20, "id");
        if (!TICKET_CODE_FORMAT.matcher(code).matches()) {
            throw new IllegalArgumentException("id must match " + TICKET_CODE_FORMAT.pattern());
        }
    }

    public static void requireOneOf(String input, String fieldLabel, String... validOptions) {
        if (input == null) return; // optional
        for (String opt : validOptions) {
            if (opt.equals(input)) return;
        }
        throw new IllegalArgumentException(fieldLabel + " must be one of: " + String.join(", ", validOptions));
    }

    public static void requireRange(Integer input, int lowerBound, int upperLimit, String fieldLabel) {
        if (input == null) return; // optional
        if (input < lowerBound || input > upperLimit) {
            throw new IllegalArgumentException(fieldLabel + " must be between " + lowerBound + " and " + upperLimit);
        }
    }
}