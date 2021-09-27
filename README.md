# Java BaseCamp Intro Task
## Description
This task is an emulation of the training management system. Key concepts here are:
1. `Grade` - objects of this class represent the grade that student received on the training. 
   It consists of the first and second semester marks. Each mark must not exceed 10 and must be greater or equal to 0.
2. `Address` - objects of this class represent the home address of the student.
3. `Student` - objects of this class represent the student that participates in the training.
4. `Training` is an interface that declares the main training functionality like adding the student
   to the training, changing the grade, etc.
5. `GlobalLogicTraining` is an implementation of the `Training` class. It is given to you without any 
   method implementations.
6. `TrainingUtils` is a utility class needed to perform operations with the stream of trainings. All methods must be implemented by you using the Stream API. 

Also, you will find the `StudentComparator` class - a custom comparator that will be used in the `TrainingUtils`.   
You can find additional details in the documentation provided for each class.

## Task
Implement the training management system's logic and make as many unit tests as possible pass. Use the information from comments and unit tests to understand the requirements.

## List of files allowed to be modified
1. `src/main/java/com/globallogic/basecamp/comparator/StudentComparator.java`
2. `src/main/java/com/globallogic/basecamp/model/Address.java`
3. `src/main/java/com/globallogic/basecamp/model/Student.java`
4. `src/main/java/com/globallogic/basecamp/GlobalLogicTraining.java`
5. `src/main/java/com/globallogic/basecamp/TrainingUtils.java`

Other files will be restored before the Jenkins build. 

## Environment
- Java 11
- Gradle Wrapper is provided as a part of the project. Please, refer to the documentation for more details: https://docs.gradle.org/current/userguide/gradle_wrapper.html


---
Good luck! \
GL BaseCamp Team.