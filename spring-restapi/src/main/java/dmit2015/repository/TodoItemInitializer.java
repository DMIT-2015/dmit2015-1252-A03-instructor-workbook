package dmit2015.repository;

import dmit2015.entity.TodoItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class TodoItemInitializer implements CommandLineRunner {
    private final Logger LOGGER = Logger.getLogger(TodoItemInitializer.class.getName());

    private final TodoItemRepository todoItemRepository;

    public TodoItemInitializer(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (todoItemRepository.count() == 0) {
            try {
                TodoItem todo1 = new TodoItem();
                todo1.setTask("Create JAX-RS demo project");
                todo1.setDone(true);
                todo1.setCreateTime(LocalDateTime.now());
                todoItemRepository.save(todo1);

                TodoItem todo2 = new TodoItem();
                todo2.setTask("Run and verify all Integration Test pass");
                todo2.setDone(false);
                todo2.setCreateTime(LocalDateTime.now());
                todoItemRepository.save(todo2);

                TodoItem todo3 = new TodoItem();
                todo3.setTask("Create DTO version of TodoResource");
                todo3.setDone(false);
                todo3.setCreateTime(LocalDateTime.now());
                todoItemRepository.save(todo3);

            } catch (Exception ex) {
                LOGGER.fine(ex.getMessage());
            }

            LOGGER.info("Created " + todoItemRepository.count() + " records.");
        }
    }
}
