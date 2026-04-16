package dmit2015.controller;

import dmit2015.entity.TodoItem;
import dmit2015.repository.TodoItemRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/TodoItems")
public class TodoItemController {

    private final TodoItemRepository todoItemRepository;

    public TodoItemController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @PostMapping
    public void createNewItem(@RequestBody TodoItem todoItem) {
        todoItemRepository.save(todoItem);
    }

    @GetMapping
    public List<TodoItem> getItems() {
        return todoItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public TodoItem getItemById(@PathVariable Long id) {
        return todoItemRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        todoItemRepository.save(todoItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable  Long id) {
        todoItemRepository.deleteById(id);
    }

}
