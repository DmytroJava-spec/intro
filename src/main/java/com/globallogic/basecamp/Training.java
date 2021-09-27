package com.globallogic.basecamp;

import com.globallogic.basecamp.model.Grade;
import com.globallogic.basecamp.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * Training interface that declares the main training functionality.
 * <p>
 * Please, do not change this file as it will be restored before each tests run
 */
public interface Training {

    String getName();

    List<Student> getStudents();

    boolean addStudent(Student student);

    boolean removeStudent(Student student);

    boolean rateFirstSemester(Student student, int mark);

    boolean rateSecondSemester(Student student, int mark);

    boolean isPresent(Student student);

    Optional<Grade> getStudentGrade(Student student);

}
