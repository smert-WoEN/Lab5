package com.company.superclass;

import com.company.Difficulty;


public interface LabWork {
    int getId();
    void setID(int id);
    String getName();
    void setName(String name);
    Coordinates getCoordinates();
    void setCoordinates(Coordinates coordinates);
    java.util.Date getCreationDate();
    void setCreationDate(java.util.Date creationDate);
    Integer getMinimalPoint();
    void setMinimalPoint(Integer minimalPoint);
    double getMaximumPoint();
    void setMaximumPoint(double maximumPoint);
    Difficulty getDifficulty();
    void setDifficulty(Difficulty difficulty);
    Discipline getDiscipline();
    void setDiscipline(Discipline discipline);
    String getLabWork(Boolean csv);
}
