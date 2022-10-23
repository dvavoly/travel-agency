package org.example.travelagency.model.enums;

public enum Country {
    FRANCE("France"),
    ITALY("Italy"),
    SPAIN("Spain"),
    GREECE("Greece");

    private final String displayValue;

    Country(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
