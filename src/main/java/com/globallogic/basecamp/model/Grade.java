package com.globallogic.basecamp.model;

/**
 * Grade is a representation of the marks received by {@link com.globallogic.basecamp.model.Student Student}
 * during first and second semesters
 * <p>
 * Please, do not change this file as it will be restored before each tests run
 */
public class Grade {

    private int firstSemester;

    private int secondSemester;

    public int getFirstSemester() {
        return firstSemester;
    }

    public void setFirstSemester(int firstSemester) {
        this.firstSemester = firstSemester;
    }

    public int getSecondSemester() {
        return secondSemester;
    }

    public void setSecondSemester(int secondSemester) {
        this.secondSemester = secondSemester;
    }

}
