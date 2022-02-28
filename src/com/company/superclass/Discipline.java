package com.company.superclass;

public interface Discipline {
    String getName();
    void setName(String name);
    Long getLectureHours();
    void setLectureHours(Long lectureHours);
    int getPracticeHours();
    void setPracticeHours(int practiceHours);
    long getSelfStudyHours();
    void setSelfStudyHours(long selfStudyHours);
    Integer getLabsCount();
    void setLabsCount(Integer labsCount);
    String getDiscipline(Boolean csv);
}
