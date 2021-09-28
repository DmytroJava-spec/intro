package com.globallogic.basecamp;

import com.globallogic.basecamp.model.Grade;
import com.globallogic.basecamp.model.Student;

import java.util.*;

/**
 * GlobalLogicTraining is an implementation of the {@link com.globallogic.basecamp.Training Training} interface.
 * Contains the training name and student grades.
 */
public class GlobalLogicTraining<name, grades> implements Training {

    /**
     * TODO: implement methods of the Training interface and add the necessary functionality
     */

    private String name;

    private final HashMap<Student, Grade> grades = new HashMap<>();
    private Object Grade;
    private Object Student;


    public GlobalLogicTraining(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<Student>(grades.keySet());
    }

    @Override
    public boolean addStudent(Student student) {
        grades.put(student, new Grade());
        return grades.containsKey(student);
    }

    @Override
    public boolean removeStudent(Student student) {
        grades.remove(student);
        return !grades.containsKey(student);
    }

    @Override
    public boolean rateFirstSemester(Student student, int mark) {
        if (grades.containsKey(student)){
            grades.get(student).setFirstSemester(mark);
            return true;
        }
        return false;
    }

    @Override
    public boolean rateSecondSemester(Student student, int mark) {
        if (grades.containsKey(student)){
            grades.get(student).setSecondSemester(mark);
            return true;
        }
        return false;
    }

    @Override
    public boolean isPresent(Student student) {
        return grades.containsKey(student);
    }

    @Override
    public Optional<Grade> getStudentGrade(Student student) {
            Optional<Grade> grade = Optional.ofNullable(grades.get(student));
        return grade;
    }

}
