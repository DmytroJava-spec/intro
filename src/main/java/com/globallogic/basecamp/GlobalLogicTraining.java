package com.globallogic.basecamp;

import com.globallogic.basecamp.model.Grade;
import com.globallogic.basecamp.model.Student;

import java.util.HashMap;

/**
 * GlobalLogicTraining is an implementation of the {@link com.globallogic.basecamp.Training Training} interface.
 * Contains the training name and student grades.
 */
public class GlobalLogicTraining implements Training {

    /**
     * TODO: implement methods of the Training interface and add the necessary functionality
     */

    private final String name;

    private final HashMap<Student, Grade> grades = new HashMap<>();

    public GlobalLogicTraining(String name) {

    }

}
