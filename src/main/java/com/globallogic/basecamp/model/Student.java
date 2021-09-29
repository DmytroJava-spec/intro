package com.globallogic.basecamp.model;

import com.globallogic.basecamp.model.Address.Builder;

import java.util.Objects;

/**
 * Student class, represents the student that attends the training
 */
public class Student {

    public final String email;

    public String firstName;

    public String lastName;

    public String phoneNumber;

    public Address address;

    public String getEmail() {
        return email;
    }

    /**
     * Allows to get the builder for the Student
     *
     * @param email student's email. Required for each student object construction
     * @return builder
     */
    public Student(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email);
    }

    public static Student.Builder builder(String email) {
        return new Student.Builder(email);

    }

    /**
     * TODO: implement hashCode() method for this class using email field
     *
     * @return object hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Builder class is a part of the builder pattern implementation
     * Needed to ease the Student object construction
     * <p>
     * TODO: implement the builder functionality
     */
    public static class Builder {
        public  String email;
        public String firstName;
        public String lastName;
        public String phoneNumber;
        public Address address;

        private Builder(String email){
            this.email = email;
        }

        public Builder(String email, String firstName, String lastName, String phoneNumber, Address address) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }


        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Student build() {
            Student student = new Student(this.email);
            student.firstName = this.firstName;
            student.address = this.address;
            student.lastName = this.lastName;
            student.phoneNumber = this.phoneNumber;
            return student;

        }
    }

}
