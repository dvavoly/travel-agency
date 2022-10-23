package org.example.travelagency.model.enums;

public enum Role {
    ROLE_GUEST("Guest"),
    ROLE_MANAGER("Manager");
    private final String displayValue;

    Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
