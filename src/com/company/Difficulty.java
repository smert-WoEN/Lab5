package com.company;

public enum Difficulty{
    VERY_EASY("VERY_EASY"),
    EASY("EASY"),
    HARD("HARD"),
    HOPELESS("HOPELESS"),
    TERRIBLE("TERRIBLE");

    private final String label;

    Difficulty (String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
