package com.company;

import com.company.superclass.Coordinates;
import com.company.superclass.LabWork;

public class Main {
    public static void main(String[] args) {
        try {
            LabWork labWork = new com.company.classes.LabWork();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
