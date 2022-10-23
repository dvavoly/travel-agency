package org.example.travelagency.model.enums;

public enum RoomType {
    ECONOMY("Economy"),
    STANDARD("Standard"),
    BUSINESS("Business");

    private final String displayValue;

    RoomType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
