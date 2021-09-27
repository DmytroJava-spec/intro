package com.globallogic.basecamp;

import com.globallogic.basecamp.data.Filler;
import com.globallogic.basecamp.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TrainingUtilsTest {

    final List<Training> trainings = Filler.createTrainings();

    @Test
    void testGetTrainingsPerStudent() {
        final var result = TrainingUtils.getTrainingsPerStudent(trainings.stream());
        testListsEqual(result.get("danny.perry@email.com"), List.of("Java Advanced", "Java Core"));
        testListsEqual(result.get("jerry.ferdy@email.com"), List.of("Java Frameworks", "Java Core"));
        testListsEqual(result.get("amanda.perry@email.com"), List.of("Java Advanced", "Java Core"));
        testListsEqual(result.get("rob.johnson@email.com"), List.of("Java Advanced", "Java Frameworks"));
    }

    @Test
    void testGetStudentsByCondition() {
        final var result = TrainingUtils.getStudentEmailsByCondition(trainings.stream(),
                (student -> student.getAddress().getCountry().equals("Country1")
                        && student.getAddress().getCity().equals("City1")));
        testListsEqual(result, List.of("jerry.ferdy@email.com", "rob.johnson@email.com"));
    }

    @Test
    void testGetAverageMarkPerStudent() {
        final var result = TrainingUtils.getAverageMarkPerStudent(trainings.stream());
        Assertions.assertEquals(5.5, result.get("danny.perry@email.com"), 0.01);
        Assertions.assertEquals(6.75, result.get("jerry.ferdy@email.com"), 0.01);
        Assertions.assertEquals(7.75, result.get("amanda.perry@email.com"), 0.01);
        Assertions.assertEquals(6.25, result.get("rob.johnson@email.com"), 0.01);
    }

    @Test
    void testForEachGrade() {
        final var trainingsList = Filler.createTrainings();

        TrainingUtils.forEachGrade(trainingsList.stream(), grade -> {
            grade.setFirstSemester(0);
        });

        for (final var training : trainingsList) {
            for (final var student : training.getStudents()) {
                Assertions.assertTrue(training.getStudentGrade(student).isPresent());
                Assertions.assertEquals(0, training.getStudentGrade(student).get().getFirstSemester());
            }
        }
    }

    @Test
    void testRemoveStudentsIf() {
        final var trainingsList = Filler.createTrainings();
        TrainingUtils.removeStudentsIf(trainingsList.stream(),
                student -> student.getEmail().equals("amanda.perry@email.com"));
        for (final var training : trainingsList) {
            for (final var student : training.getStudents()) {
                Assertions.assertNotEquals("amanda.perry@email.com", student.getEmail());
            }
        }
    }

    @Test
    void testGetStudentsWithMaxMark() {
        final var result = TrainingUtils.getStudentsWithMaxMark(trainings.stream());
        testListsEqual(result, List.of("amanda.perry@email.com", "rob.johnson@email.com"));
    }

    @Test
    void testGetStudentsSorted() {
        final var result = TrainingUtils.getStudentsSorted(trainings.stream());
        final var expectedList = List.of("Jerry Ferdy", "Rob Johnson", "Amanda Perry", "Danny Perry");
        Assertions.assertEquals(result, expectedList);
    }

    @Test
    void testGetStudentsWithMarkLowerThan() {
        final var result = TrainingUtils.getStudentsWithMarkLowerThan(trainings.stream(), 4);
        Assertions.assertEquals(result, List.of("amanda.perry@email.com"));
    }

    @Test
    void testIsStudentPresentOnTrainingsWhenNotPresent() {
        final var student = Student.builder("non.existing@email.com").build();
        Assertions.assertFalse(TrainingUtils.isStudentPresentOnTrainings(trainings.stream(), student));
    }

    @Test
    void testIsStudentPresentOnTrainingsWhenIsPresent() {
        final var student = Student.builder("amanda.perry@email.com").build();
        Assertions.assertTrue(TrainingUtils.isStudentPresentOnTrainings(trainings.stream(), student));
    }

    @Test
    void testGetAverageMarkPerTraining() {
        final var result = TrainingUtils.getAverageMarkPerTraining(trainings.stream());
        Assertions.assertEquals(5.75, result.get("Java Frameworks"), 0.01);
        Assertions.assertEquals(6, result.get("Java Core"), 0.01);
        Assertions.assertEquals(7.667, result.get("Java Advanced") , 0.01);
    }

    private <T> void testListsEqual(List<T> list1, List<T> list2) {
        Assertions.assertEquals(list1.size(), list2.size());
        Assertions.assertTrue(list1.containsAll(list2));
    }

}
