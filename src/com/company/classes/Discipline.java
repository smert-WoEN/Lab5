package com.company.classes;

import java.util.regex.PatternSyntaxException;

public class Discipline implements com.company.superclass.Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long lectureHours; //Поле может быть null
    private int practiceHours;
    private long selfStudyHours;
    private Integer labsCount; //Поле не может быть null

    public Discipline() {}

    public Discipline(String fromString){
        new Coordinates(fromString, false);
    }

    public Discipline(String fromString, boolean csv) throws IllegalArgumentException{
        String[] arguments;
        try {
            arguments = fromString.split(csv? ", ": " " , 5);
        } catch (PatternSyntaxException | NullPointerException e) {
            throw new IllegalArgumentException("Error in argument: " + e.getMessage() + ".");
        }
        try {
            setName(arguments[0]);
            setLectureHours(Long.valueOf(arguments[1]));
            setPracticeHours(Integer.parseInt(arguments[2]));
            setSelfStudyHours(Long.parseLong(arguments[3]));
            setLabsCount(Integer.valueOf(arguments[4]));
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Can't parse discipline from \"" + fromString + "\": " + e.getMessage() + ".");
        }
    }

    public Discipline(String name, Long lectureHours, int practiceHours, long selfStudyHours, Integer labsCount) {
        setName(name);
        setLectureHours(lectureHours);
        setPracticeHours(practiceHours);
        setSelfStudyHours(selfStudyHours);
        setLabsCount(labsCount);
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
    public Long getLectureHours() {
        return lectureHours;
    }

    @Override
    public void setLectureHours(Long lectureHours) {
        if (lectureHours >= 0) {
            this.lectureHours = lectureHours;
        }
        else {
            throw new IllegalArgumentException("only positive value");
        }
    }

    @Override
    public int getPracticeHours() {
        return practiceHours;
    }

    @Override
    public void setPracticeHours(int practiceHours) {
        if (practiceHours >= 0) {
            this.practiceHours = practiceHours;
        }
        else {
            throw new IllegalArgumentException("only positive value");
        }
    }

    @Override
    public long getSelfStudyHours() {
        return selfStudyHours;
    }

    @Override
    public void setSelfStudyHours(long selfStudyHours) {
        if (selfStudyHours >= 0) {
            this.selfStudyHours = selfStudyHours;
        }
        else {
            throw new IllegalArgumentException("only positive value");
        }
    }

    @Override
    public Integer getLabsCount() {
        return labsCount;
    }

    @Override
    public void setLabsCount(Integer labsCount) {
        if (labsCount != null) {
            if (labsCount >= 0) {
                this.labsCount = labsCount;
            }
            else {
                throw new IllegalArgumentException("only positive value");
            }
        }
        else {
            throw new IllegalArgumentException("labsCount is empty.");
        }
    }

    @Override
    public String getDiscipline(Boolean csv) {
        String string;
        if (!csv) {
            string = getName() + " " + getLectureHours() + " " + getPracticeHours() + " " + getSelfStudyHours() + " " + getSelfStudyHours();
        } else {

            string = getName() + ", " + getLectureHours() + ", " + getPracticeHours() + ", " + getSelfStudyHours() + ", " + getSelfStudyHours();
        }
        return string;
    }
}
