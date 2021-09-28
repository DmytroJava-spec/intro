package com.globallogic.basecamp;

import com.globallogic.basecamp.model.Grade;
import com.globallogic.basecamp.model.Student;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TrainingUtils contains a set of operations to perform with Stream of trainings.
 * You will need to implement the methods using <strong>Stream API</strong>. Do not use for or while loops
 * for implementation
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html">Stream</a>
 * documentation for more details
 */
public class TrainingUtils {

    private TrainingUtils() {

    }

    /**
     * Get emails of the students from provided trainings that satisfy the condition
     *
     * @param trainings stream of trainings
     * @param predicate condition whether we return this student or not
     * @return list of unique students emails
     */
    public static List<String> getStudentEmailsByCondition(Stream<Training> trainings,
                                                           Predicate<Student> predicate){
    Set<String> result = new HashSet<>();
        trainings.forEach(a -> a.getStudents().stream().
    filter(predicate).forEach( student -> {
        result.add(student.getEmail());}));

        return new ArrayList<String>(result);
    }

    /**
     * For each student from the provided trainings get a list of trainings that he/she attends
     *
     * @param trainings stream of trainings
     * @return map where keys are student emails and values are List of training names that this student attends
     */
    public static Map<String, List<String>> getTrainingsPerStudent(Stream<Training> trainings) {
        Map<String, List<String>> result = new HashMap<>();
        trainings.forEach(training -> {
            training.getStudents().stream().forEach(
                    student -> {
                        result.putIfAbsent(student.getEmail(), new ArrayList<>());
                        result.get(student.getEmail()).add(training.getName());
                    }
            );
        });
        return result;
    }
    /**
     * For each student from the provided trainings get an average mark. Average mark is calculated as a sum of all
     * marks for both semesters divided by the number of marks
     *
     * @param trainings stream of trainings
     * @return map where keys are student emails and values are student average mark calculating using both semesters
     */
    public static Map<String, Double> getAverageMarkPerStudent(Stream<Training> trainings) {
        Map<String, List<Integer>> email = new HashMap<>();
        trainings.forEach(training -> {
            training.getStudents().stream().forEach(
                    student -> {
                        if (!email.containsKey(student.getEmail())) {
                            email.put(student.getEmail(), new ArrayList<>());
                        }
                        if (training.getStudentGrade(student).isPresent()) {
                            email
                                    .get(student.getEmail())
                                    .add(training
                                            .getStudentGrade(student)
                                            .get()
                                            .getFirstSemester());
                            email
                                    .get(student.getEmail())
                                    .add(training
                                            .getStudentGrade(student)
                                            .get()
                                            .getSecondSemester());
                        }
                    }
            );
        });
        return email.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream()
                                .mapToDouble(a -> a)
                                .average().getAsDouble())
                );
    }
    /**
     * Perform an action for all grades in the provided trainings
     *
     * @param trainings stream of trainings
     * @param action    action to perform
     */
    public static void forEachGrade(Stream<Training> trainings, Consumer<Grade> action) {

    }

    /**
     * Get students with the highest mark received during any semester
     *
     * @param trainings stream of trainings
     * @return list of students emails
     */
    public static List<String> getStudentsWithMaxMark(Stream<Training> trainings) {
        return null;
    }

    /**
     * Remove students from all provided trainings by the specified condition
     *
     * @param trainings stream of trainings
     * @param predicate condition whether to remove a student
     */
    public static void removeStudentsIf(Stream<Training> trainings, Predicate<Student> predicate) {

    }

    /**
     * Get distinct students' full names from all trainings sorted by the `StudentComparator`
     * <p>
     * For example, full name for the student with the first name "Jerry" and the last name "Ferdy"
     * will be "Jerry Ferdy"
     *
     * @param trainings stream of trainings
     * @return list of sorted
     */
    public static List<String> getStudentsSorted(Stream<Training> trainings) {
        return null;
    }

    /**
     * Get students that received mark lower than provided on any training during any semester. Trainings
     * are also provided as a stream
     *
     * @param trainings stream of trainings
     * @param mark      student mark
     * @return list of students' emails
     */
    public static List<String> getStudentsWithMarkLowerThan(Stream<Training> trainings, int mark) {
        return null;
    }

    /**
     * Check whether the student attends any of the provided trainings
     * Note that students are equal if and only if their emails are the same
     *
     * @param trainings stream of trainings
     * @param student   student to check presence
     * @return true if the student attends any training, false otherwise
     */
    public static boolean isStudentPresentOnTrainings(Stream<Training> trainings, Student student) {
        return true;
    }

    /**
     * Get average students mark for each of the provided trainings. Average mark is calculated as a sum of
     * all marks for both semesters divided by the number of marks
     *
     * @param trainings stream of trainings
     * @return map where key is a training name and value is an average mark
     */
    public static Map<String, Double> getAverageMarkPerTraining(Stream<Training> trainings) {
        return null;
    }

}
