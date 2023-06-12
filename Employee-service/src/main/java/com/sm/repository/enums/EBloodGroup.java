package com.sm.repository.enums;

public enum EBloodGroup {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String label;

    private EBloodGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}