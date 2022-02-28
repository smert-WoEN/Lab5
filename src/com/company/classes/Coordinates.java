package com.company.classes;

import java.util.regex.PatternSyntaxException;

public class Coordinates implements com.company.superclass.Coordinates {
    private int x;
    private double y;

    public Coordinates() {}

    public Coordinates(String fromString) {
        new Coordinates(fromString, false);
    }

    public Coordinates(String fromString, boolean csv) {
        String[] arguments;
        try {
            arguments = fromString.split(csv? ", ": " " , 2);
        } catch (PatternSyntaxException | NullPointerException e) {
            throw new IllegalArgumentException("Error in argument: " + e.getMessage() + ".");
        }
        try {
            setX(Integer.parseInt(arguments[0]));
            setY(Double.parseDouble(arguments[1]));
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Can't parse Coordinates from \"" + fromString + "\": " + e.getMessage() + ".");
        }
    }

    public Coordinates(int x, double y) throws IllegalArgumentException {
        setX(x);
        setY(y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        if (x > -497) {
            this.x = x;
        }
        else {
            throw new IllegalArgumentException("Out of range (min -497)");
        }
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        if (y < 268.0) {
            this.y = y;
        }
        else {
            throw new IllegalArgumentException("Out of range (max 268.0)");
        }
    }

    @Override
    public String getCoordinates(Boolean csv) {
        return getX() + (csv? ", ": " ") + getY();
    }
}
