package com.globallogic.basecamp.comparator;

import com.globallogic.basecamp.model.Student;

import java.util.Comparator;

/**
 * Custom comparator for the {@link com.globallogic.basecamp.model.Student Student} class
 */
    class StudentSecondNameComparator implements Comparator<Student> {

    /**
     * TODO: implement `compare` method of the Comparator interface
     * Compare students in natural order by the last name. If last names are the same,
     * compare in natural order by the first name
     */
        public int compare(Student a,Student b){
            return a
                    .getLastName()
                    .equals(b.getLastName()) ? a
                    .getFirstName()
                    .compareTo(b.getFirstName()) : a
                    .getLastName()
                    .compareTo(b.getLastName());
        }
}

