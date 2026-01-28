package dmit2015.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@ToString()
//@Getter
//@Setter
@Data   // includes @Getter,@Setter,@ToString, and more
public class Task {

    @NotBlank(message = "Task description is required")
    @Size(min=3,max=100,message = "Task description must contain {min} and {max} characters")
    private String description;

    @Pattern(regexp = "^(Low|Medium|High)$", message = "Priority must be Low, Medium, or High")
    private String priority;    // Low,Medium,High

    private boolean done;

}
