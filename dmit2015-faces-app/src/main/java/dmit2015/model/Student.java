package dmit2015.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.datafaker.Faker;

import java.util.UUID;

@Getter
@Setter
@ToString()
public class Student {

    private String id;
    private String firstName;
    private String lastName;

    public Student() {
    }

    // copy constructor
    public Student(Student other) {
        this.id = other.getId();
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
    }
    public static Student copyOf(Student other) {
        return new Student(other);
    }

    public static Student of(Faker faker) {
        Student currentStudent = new Student();
        currentStudent.setId(UUID.randomUUID().toString());
        currentStudent.setFirstName(faker.name().firstName());
        currentStudent.setLastName(faker.name().lastName());
        return currentStudent;
    }
}
