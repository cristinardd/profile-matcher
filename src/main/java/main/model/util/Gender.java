package main.model.util;

public enum Gender {
    MALE, FEMALE, OTHER;

    public static Gender fromString(String dbValue) {
        return switch (dbValue.toLowerCase()) {
            case "male", "man", "m" -> MALE;
            case "female", "woman", "f" -> FEMALE;
            default -> OTHER;
        };
    }
}