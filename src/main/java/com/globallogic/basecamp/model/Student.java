package com.globallogic.basecamp.model;

/**
 * Student class, represents the student that attends the training
 */
public class Student {

    public String email;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public Address address;

    /**
     * Allows to get the builder for the Student
     *
     * @param email student's email. Required for each student object construction
     * @return builder
     */
    public static Builder builder(String email) {
        
    }


    /**
     * TODO: implement getters and other methods if necessary
     */

    /**
     * TODO: implement equals() method for this class
     *
     * @param o object to compare the current object to
     * @return true if students have the same emails, false otherwise
     */
    @Override
    public boolean equals(Object o) {

    }

    /**
     * TODO: implement hashCode() method for this class using email field
     *
     * @return object hash code
     */
    @Override
    public int hashCode() {

    }

    /**
     * Builder class is a part of the builder pattern implementation
     * Needed to ease the Student object construction
     * <p>
     * TODO: implement the builder functionality
     */
    public class Builder {
    }

}
