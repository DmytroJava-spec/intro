package com.globallogic.basecamp;

import com.globallogic.basecamp.model.Address;
import com.globallogic.basecamp.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void testBuilder() {
        final var address = Address.builder()
                .setCountry("Country")
                .setCity("City")
                .setStreet("Street")
                .setHouseNumber(1)
                .build();
        final var student = Student.builder("sample@email.com")
                .setFirstName("Jerry")
                .setLastName("Ferdy")
                .setAddress(address)
                .setPhoneNumber("000-000-00-00")
                .build();
        Assertions.assertEquals("Jerry", student.getFirstName());
        Assertions.assertEquals("Ferdy", student.getLastName());
        Assertions.assertEquals("000-000-00-00", student.getPhoneNumber());
        Assertions.assertEquals("sample@email.com", student.getEmail());
        Assertions.assertSame(address, student.getAddress());
    }

    @Test
    void testEquality() {
        final var email = "sample@email.com";
        final var student1 = Student.builder(email).build();
        final var student2 = Student.builder(email).build();
        Assertions.assertEquals(student1, student2);
        Assertions.assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    void testInequality() {
        final var email1 = "sample1@email.com";
        final var email2 = "sample2@email.com";
        final var student1 = Student.builder(email1).build();
        final var student2 = Student.builder(email2).build();
        Assertions.assertNotEquals(student1, student2);
    }

}