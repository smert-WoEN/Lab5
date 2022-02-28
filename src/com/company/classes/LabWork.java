package com.company.classes;

import com.company.Difficulty;
import com.company.superclass.Coordinates;
import com.company.superclass.Discipline;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;


public class LabWork implements com.company.superclass.LabWork {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private double maximumPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле не может быть null

    public LabWork(String fromString) throws IllegalArgumentException {
        new LabWork(fromString, false);
    }

    public LabWork(String fromString, boolean csv) throws IllegalArgumentException{
        String[] arguments;
        try {
            arguments = fromString.split(csv? ", ": " ", 13);
        } catch (PatternSyntaxException | NullPointerException e) {
            throw new IllegalArgumentException("Error in argument: " + e.getMessage() + ".");
        }
        try {
            try {
                setID(Integer.parseInt(arguments[0]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Can't parse LabWork id from " + arguments[0] + ".");
            }
            setName(arguments[1]);
            try {
                setCoordinates(new com.company.classes.Coordinates(Integer.parseInt(arguments[2]), Double.parseDouble(arguments[3])));
            } catch (RuntimeException e){
                throw new IllegalArgumentException("Can't parse LabWork coordinates from " + arguments[2] + "," + arguments[3] + ".");
            }
            if (arguments[4].isEmpty())
                setCreationDate(new Date());
            else
                try {
                    setCreationDate(DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()).parse(arguments[4]));
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Can't parse creation date from " + arguments[4] + ".");
                }
            try {
                setMinimalPoint(Integer.getInteger(arguments[5]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Can't parse minimalPoint from " + arguments[5] + ".");
            }
            try {
                setMaximumPoint(Double.parseDouble(arguments[6]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Can't parse maximumPoint from " + arguments[6] + ".");
            }
            try {
                setDifficulty(Difficulty.valueOf(arguments[7]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Can't parse difficulty from " + arguments[7] + ".");
            }
            try {
                setDiscipline(new com.company.classes.Discipline(arguments[8], Long.valueOf(arguments[9]), Integer.parseInt(arguments[10]),
                        Long.parseLong(arguments[11]), Integer.valueOf(arguments[12])));
            } catch (RuntimeException e){
                throw new IllegalArgumentException("Can't parse discipline.");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Can't parse Coordinates from \"" + fromString + "\": " + e.getMessage() + ".");
        }
    }

    public LabWork() {}

    public LabWork(int id, String name, com.company.classes.Coordinates coordinates,
                   java.util.Date creationDate, Integer minimalPoint, double maximumPoint,
                   Difficulty difficulty, Discipline discipline) {
        setID(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setMinimalPoint(minimalPoint);
        setMaximumPoint(maximumPoint);
        setDifficulty(difficulty);
        setDiscipline(discipline);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("name is empty.");
        }
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates != null) {
            this.coordinates = coordinates;
        }
        else {
            throw new IllegalArgumentException("coordinates is empty.");
        }
    }

    @Override
    public java.util.Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(java.util.Date creationDate) {
        if (creationDate != null) {
            this.creationDate = creationDate;
        }
        else {
            throw new IllegalArgumentException("creationDate is empty.");
        }
    }

    @Override
    public Integer getMinimalPoint() {
        return minimalPoint;
    }

    @Override
    public void setMinimalPoint(Integer minimalPoint) {
        if (minimalPoint != null) {
            this.minimalPoint = minimalPoint;
        }
        else {
            throw new IllegalArgumentException("minimalPoint is empty.");
        }
    }

    @Override
    public double getMaximumPoint() {
        return 0;
    }

    @Override
    public void setMaximumPoint(double maximumPoint) {
        if (maximumPoint > 0) {
            this.maximumPoint = maximumPoint;
        }
        else {
            throw new IllegalArgumentException("only positive value");
        }
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        if (difficulty != null) {
            this.difficulty = difficulty;
        }
        else {
            throw new IllegalArgumentException("difficulty is empty.");
        }
    }

    @Override
    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public void setDiscipline(Discipline discipline) {
        if (discipline != null) {
            this.discipline = discipline;
        }
        else {
            throw new IllegalArgumentException("discipline is empty.");
        }
    }

    @Override
    public String getLabWork(Boolean csv) {
        String string;
        if (!csv) {
            string = getId() + " " + getName() + " " + coordinates.getCoordinates(false) + " " + getCreationDate() + " " +
                    getMinimalPoint() + " " + getMaximumPoint() + " " + getDifficulty() + " " + discipline.getDiscipline(false);
        } else {
            string = getId() + ", " + getName() + ", " + coordinates.getCoordinates(true) + ", " + getCreationDate() + ", " +
                    getMinimalPoint() + ", " + getMaximumPoint() + ", " + getDifficulty() + ", " + discipline.getDiscipline(true);
        }
        return string;
    }
}
