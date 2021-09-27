package com.globallogic.basecamp;

import com.globallogic.basecamp.model.Grade;
import com.globallogic.basecamp.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.HashMap;

class GlobalLogicTrainingTest {

    private static final Class<GlobalLogicTraining> TRAINING_CLASS = GlobalLogicTraining.class;
    private static final String NAME_FIELD = "name";
    private static final String GRADES_FIELD = "grades";

    @Test
    void testFields() throws NoSuchFieldException {
        final var name = TRAINING_CLASS.getDeclaredField(NAME_FIELD);
        final var grades = TRAINING_CLASS.getDeclaredField(GRADES_FIELD);
        final var nameModifiers = name.getModifiers();
        final var gradesModifiers = grades.getModifiers();

        Assertions.assertTrue(Modifier.isFinal(nameModifiers));
        Assertions.assertTrue(Modifier.isPrivate(nameModifiers));
        Assertions.assertTrue(Modifier.isFinal(gradesModifiers));
        Assertions.assertTrue(Modifier.isPrivate(gradesModifiers));

        final var nameType = name.getType();
        final var gradesType = grades.getType();

        Assertions.assertTrue(String.class.isAssignableFrom(nameType));
        Assertions.assertTrue(HashMap.class.isAssignableFrom(gradesType));
    }

    @Test
    void testImplementation() {
        final var trainingClass = GlobalLogicTraining.class;
        Assertions.assertTrue(Training.class.isAssignableFrom(trainingClass));
    }

    @Test
    void testConstruct() {
        final var name = "Java Camp";
        final var training = new GlobalLogicTraining(name);
        Assertions.assertEquals(name, training.getName());
    }

    @Test
    void testConstructWithWrongName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new GlobalLogicTraining(null));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new GlobalLogicTraining(""));
    }

    @Test
    void testAddStudent() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var grades = getInternalMap(training);
        Assertions.assertTrue(training.addStudent(student));
        Assertions.assertTrue(grades.containsKey(student));
    }

    @Test
    void testAddIfStudentExists() {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        Assertions.assertTrue(training.addStudent(student));
        Assertions.assertFalse(training.addStudent(student));
    }

    @Test
    void testRemoveStudent() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var grades = getInternalMap(training);
        grades.put(student, null);
        Assertions.assertTrue(training.removeStudent(student));
        Assertions.assertFalse(grades.containsKey(student));
    }

    @Test
    void testRemoveIfStudentAbsent() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        Assertions.assertFalse(training.removeStudent(student));
        final var grades = getInternalMap(training);
        Assertions.assertFalse(grades.containsKey(student));
    }

    @Test
    void testRateFirstSemester() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var grades = getInternalMap(training);
        grades.put(student, null);
        Assertions.assertTrue(training.rateFirstSemester(student, 5));
        final var grade = grades.get(student);
        Assertions.assertNotNull(grade);
        Assertions.assertEquals(5, grade.getFirstSemester());
    }

    @Test
    void testRateFirstSemesterIfStudentAbsent() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        Assertions.assertFalse(training.rateFirstSemester(student, 5));
        final var grades = getInternalMap(training);
        Assertions.assertFalse(grades.containsKey(student));
    }

    @Test
    void tesRateFirstSemesterWithWrongGrade() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student1 = Student.builder("jerry.ferdy@email.com").build();
        final var student2 = Student.builder("amanda.perry@email.com").build();
        final var grades = getInternalMap(training);
        grades.put(student1, null);
        grades.put(student2, null);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> training.rateFirstSemester(student1, -1)
        );
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> training.rateFirstSemester(student2, 11)
        );
        Assertions.assertNull(grades.get(student1));
        Assertions.assertNull(grades.get(student2));
    }

    @Test
    void testRateSecondSemester() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var grades = getInternalMap(training);
        grades.put(student, null);
        Assertions.assertTrue(training.rateSecondSemester(student, 5));
        final var grade = grades.get(student);
        Assertions.assertNotNull(grade);
        Assertions.assertEquals(5, grade.getSecondSemester());
    }

    @Test
    void testRateSecondSemesterIfStudentAbsent() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        Assertions.assertFalse(training.rateSecondSemester(student, 5));
        final var grades = getInternalMap(training);
        Assertions.assertFalse(grades.containsKey(student));
    }

    @Test
    void tesRateSecondSemesterWithWrongGrade() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student1 = Student.builder("jerry.ferdy@email.com").build();
        final var student2 = Student.builder("amanda.perry@email.com").build();
        final var grades = getInternalMap(training);
        grades.put(student1, null);
        grades.put(student2, null);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> training.rateSecondSemester(student1, -1)
        );
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> training.rateSecondSemester(student2, 11)
        );
        Assertions.assertNull(grades.get(student1));
        Assertions.assertNull(grades.get(student2));
    }

    @Test
    void testIsPresentWhenExists() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var grades = getInternalMap(training);
        grades.put(student, null);
        Assertions.assertTrue(training.isPresent(student));
    }

    @Test
    void testIsPresentWhenAbsent() {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        Assertions.assertFalse(training.isPresent(student));
    }

    @Test
    void testGetStudentGradeIfExists() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var grades = getInternalMap(training);
        final var existingGrade = new Grade();
        existingGrade.setFirstSemester(2);
        existingGrade.setSecondSemester(9);
        grades.put(student, existingGrade);

        final var grade = training.getStudentGrade(student);
        Assertions.assertTrue(grade.isPresent());
        Assertions.assertEquals(2, grade.get().getFirstSemester());
        Assertions.assertEquals(9, grade.get().getSecondSemester());
    }

    @Test
    void testGetStudentGradeIfNoGrade() throws ReflectiveOperationException {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var grades = getInternalMap(training);
        grades.put(student, null);
        final var grade = training.getStudentGrade(student);
        Assertions.assertTrue(grade.isEmpty());
    }

    @Test
    void testGetStudentGradeIfZero() {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        training.addStudent(student);
        training.rateFirstSemester(student, 0);
        training.rateSecondSemester(student, 0);
        final var grade = training.getStudentGrade(student);
        Assertions.assertTrue(grade.isPresent());
        Assertions.assertEquals(0, grade.get().getFirstSemester());
        Assertions.assertEquals(0, grade.get().getSecondSemester());
    }

    @Test
    void testModifyStudentUsingField() {
        final var training = new GlobalLogicTraining("Java Camp");
        final var student = Student.builder("jerry.ferdy@email.com").build();
        final var studentClass = Student.class;
        try {
            final var grades = getInternalMap(training);
            final var email = studentClass.getDeclaredField("email");
            grades.put(student, null);
            email.set(student, "amanda.perry@email.com");
            Assertions.assertTrue(grades.containsKey(student));
        } catch (ReflectiveOperationException e) {
            // NOP
        }
    }

    @SuppressWarnings("unchecked")
    private HashMap<Student, Grade> getInternalMap(GlobalLogicTraining training)
            throws ReflectiveOperationException {
        final var gradesField = TRAINING_CLASS.getDeclaredField(GRADES_FIELD);
        gradesField.setAccessible(true);
        final var gradesMap = gradesField.get(training);
        return (HashMap<Student, Grade>) gradesMap;
    }

}