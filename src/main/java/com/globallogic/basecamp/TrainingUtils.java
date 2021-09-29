package com.globallogic.basecamp;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import com.globallogic.basecamp.comparator.StudentComparator;
import com.globallogic.basecamp.model.Grade;
import com.globallogic.basecamp.model.Student;
import com.sun.source.doctree.SeeTree;

import java.sql.Array;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
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
                                                           Predicate<Student> predicate) {
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
                    if(!email.containsKey(student.getEmail())) {
                        email.put(student.getEmail(), new ArrayList<>());
                    }
                    if(training.getStudentGrade(student).isPresent()) {
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
        trainings.forEach(training ->
            training.getStudents().stream().forEach(student ->
                    training.getStudentGrade(student).stream().forEach(action)));
    }

    /**
     * Get students with the highest mark received during any semester
     *
     * @param trainings stream of trainings
     * @return list of students emails
     */
    public static List<String> getStudentsWithMaxMark(Stream<Training> trainings) {

        class CustomPair {
            private Integer key;
            private Set<String> value;
            public Integer getKey() {
                return key;
            }
            public void setKey(Integer key) {
                this.key = key;
            }
            public Set<String> getValue() {
                return value;
            }
            public void setValue(Set<String> value) {
                this.value = value;
            }
        }

        CustomPair max = new CustomPair();
        max.setKey(0);
        trainings.forEach(training -> {
            training.getStudents().stream().forEach(student -> {
                if(training.getStudentGrade(student).isPresent()){
                    if(max.getKey() < training.getStudentGrade(student).get().getFirstSemester()){
                        max.setKey(training.getStudentGrade(student).get().getFirstSemester());
                        max.setValue(new HashSet<>());
                    }
                    if(max.getKey() < training.getStudentGrade(student).get().getSecondSemester()){
                        max.setKey(training.getStudentGrade(student).get().getSecondSemester());
                        max.setValue(new HashSet<>());
                    }
                    if(max.getKey() == training.getStudentGrade(student).get().getSecondSemester() ||
                        max.getKey() == training.getStudentGrade(student).get().getFirstSemester())
                    {
                        max.getValue().add(student.getEmail());
                    }
                }
            });
        });
        return max.getValue().stream().collect(Collectors.toList());
    }

    /**
     * Remove students from all provided trainings by the specified condition
     *
     * @param trainings stream of trainings
     * @param predicate condition whether to remove a student
     */
    public static void removeStudentsIf(Stream<Training> trainings, Predicate<Student> predicate) {
        trainings.forEach(
            training -> training.getStudents().stream().filter(predicate).forEach(student ->
                training.removeStudent(student))
        );
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
        Set<Student> students = new HashSet<>();
        trainings.forEach(training ->students.addAll(training.getStudents()));
        return students.stream().sorted(new StudentComparator()).map(student -> {
          return   student.getFirstName().concat(" ").concat(student.getLastName());
        }).collect(toList());
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
        Set<String> students = new HashSet<>();
        trainings.forEach(training -> {
            training.getStudents().stream().forEach(student -> {
                if(training.getStudentGrade(student).isPresent() &&(
                    training.getStudentGrade(student).get().getFirstSemester() < mark ||
                    training.getStudentGrade(student).get().getSecondSemester() < mark
                    ))
                    students.add(student.getEmail());
            });
        });
        return students.stream().collect(Collectors.toList());
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
        return trainings.anyMatch(training -> training.isPresent(student));
    }

    /**
     * Get average students mark for each of the provided trainings. Average mark is calculated as a sum of
     * all marks for both semesters divided by the number of marks
     *
     * @param trainings stream of trainings
     * @return map where key is a training name and value is an average mark
     */
    public static Map<String, Double> getAverageMarkPerTraining(Stream<Training> trainings) {
        Map<String, List<Integer>> average = new HashMap<>();
        trainings.forEach(training -> {
            average.put(training.getName(), new ArrayList<>());
            training.getStudents().stream().forEach(student -> {
                if(training.getStudentGrade(student).isPresent()){
                    average.get(training.getName()).add(training.getStudentGrade(student).get().getFirstSemester());
                    average.get(training.getName()).add(training.getStudentGrade(student).get().getSecondSemester());
                }
            });});
        return average.entrySet().stream()
            .collect(Collectors.toMap(  e -> e.getKey(),
                e -> (Double) (e.getValue().stream().collect(Collectors.averagingInt(Integer::intValue)))
            ));
    }

}
