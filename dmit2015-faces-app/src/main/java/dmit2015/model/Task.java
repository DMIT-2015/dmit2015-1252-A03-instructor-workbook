package dmit2015.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.datafaker.Faker;

import java.util.UUID;
import java.util.random.RandomGenerator;

//@ToString()
//@Getter
//@Setter
@Data   // includes @Getter,@Setter,@ToString, and more
@NoArgsConstructor
public class Task {

    private String id;      // unique identifer

    @NotBlank(message = "Task description is required")
    @Size(min=3,max=100,message = "Task description must contain {min} and {max} characters")
    private String description;

//    @Pattern(regexp = "^(Low|Medium|High)$", message = "Priority must be Low, Medium, or High")
//    private String priority;    // Low,Medium,High
    @NotNull(message = "Priority must be Low, Medium, or High")
    private TaskPriority priority;

    private boolean done;

    public static Task of(Faker faker) {
//        String[] priorities = {"Low","Medium","High"};
        TaskPriority[] priorities = TaskPriority.values();

        Task currentTask = new Task();
        currentTask.setId(UUID.randomUUID().toString());
        currentTask.setDescription("Nuke " + faker.fallout().location());
        currentTask.setPriority(priorities[RandomGenerator.getDefault().nextInt(priorities.length)]);
        currentTask.setDone(faker.bool().bool());
        return currentTask;
    }

    public Task(Task other) {
        setId(other.getId());
        setDescription(other.getDescription());
        setPriority(other.getPriority());
        setDone(other.isDone());
    }

    public static Task copyOf(Task other) {
        return new Task(other);
    }

}
