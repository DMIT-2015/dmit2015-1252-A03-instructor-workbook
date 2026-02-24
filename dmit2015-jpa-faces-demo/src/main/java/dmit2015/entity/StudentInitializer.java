package dmit2015.entity;

import dmit2015.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class StudentInitializer {

    @Inject
    private StudentRepository studentRepository;

    public void initialize(@Observes @Initialized(ApplicationScoped.class) Object event) {
        // Seed database with 3 sample records
        if (studentRepository.count() == 0) {
            var student1 = new Student();
            student1.setFirstName("Talveer");
            student1.setLastName("Bath");
            studentRepository.add(student1);

            var student2 = new Student();
            student2.setFirstName("Nematullah");
            student2.setLastName("Mohibi");
            studentRepository.add(student2);

            var student3 = new Student();
            student3.setFirstName("Carlos");
            student3.setLastName("Valencia");
            studentRepository.add(student3);

        }
    }
}
