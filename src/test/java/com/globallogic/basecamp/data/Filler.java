package com.globallogic.basecamp.data;

import com.globallogic.basecamp.GlobalLogicTraining;
import com.globallogic.basecamp.Training;
import com.globallogic.basecamp.model.Address;
import com.globallogic.basecamp.model.Student;

import java.util.List;

public class Filler {

    public static List<Training> createTrainings() {
        final var students = createStudents();
        final var training1 = new GlobalLogicTraining("Java Frameworks");
        final var training2 = new GlobalLogicTraining("Java Core");
        final var training3 = new GlobalLogicTraining("Java Advanced");
        training1.addStudent(students.get(0));
        training1.addStudent(students.get(3));
        training2.addStudent(students.get(0));
        training2.addStudent(students.get(1));
        training2.addStudent(students.get(2));
        training3.addStudent(students.get(1));
        training3.addStudent(students.get(2));
        training3.addStudent(students.get(3));
        final List<Training> trainings = List.of(training1, training2, training3);
        addGrades(trainings, students);
        return trainings;
    }

    private static void addGrades(List<Training> trainings, List<Student> students) {
        trainings.get(0).rateFirstSemester(students.get(0), 6);
        trainings.get(0).rateSecondSemester(students.get(0), 8);
        trainings.get(0).rateFirstSemester(students.get(3), 5);
        trainings.get(0).rateSecondSemester(students.get(3), 4);
        trainings.get(1).rateFirstSemester(students.get(0), 8);
        trainings.get(1).rateSecondSemester(students.get(0), 5);
        trainings.get(1).rateFirstSemester(students.get(1), 10);
        trainings.get(1).rateSecondSemester(students.get(1), 3);
        trainings.get(1).rateFirstSemester(students.get(2), 4);
        trainings.get(1).rateSecondSemester(students.get(2), 6);
        trainings.get(2).rateFirstSemester(students.get(1), 9);
        trainings.get(2).rateSecondSemester(students.get(1), 9);
        trainings.get(2).rateFirstSemester(students.get(2), 5);
        trainings.get(2).rateSecondSemester(students.get(2), 7);
        trainings.get(2).rateFirstSemester(students.get(3), 6);
        trainings.get(2).rateSecondSemester(students.get(3), 10);
    }

    private static List<Student> createStudents() {
        final var address1 = Address.builder().setCountry("Country1")
                .setCity("City1").setStreet("Street1").setHouseNumber(1).build();
        final var address2 = Address.builder().setCountry("Country2")
                .setCity("City2").setStreet("Street2").setHouseNumber(2).build();
        final var address3 = Address.builder().setCountry("Country1")
                .setCity("City1").setStreet("Street2").setHouseNumber(2).build();
        final var student1 = Student.builder("jerry.ferdy@email.com")
                .setFirstName("Jerry").setLastName("Ferdy").setAddress(address1)
                .setPhoneNumber("000-000-00-00").build();
        final var student2 = Student.builder("amanda.perry@email.com")
                .setFirstName("Amanda").setLastName("Perry").setAddress(address2)
                .setPhoneNumber("00-000-00-01").build();
        final var student3 = Student.builder("danny.perry@email.com")
                .setFirstName("Danny").setLastName("Perry").setAddress(address2)
                .setPhoneNumber("00-000-00-02").build();
        final var student4 = Student.builder("rob.johnson@email.com")
                .setFirstName("Rob").setLastName("Johnson").setAddress(address3)
                .setPhoneNumber("00-000-00-03").build();
        return List.of(student1, student2, student3, student4);
    }

}
