package dmit2015.faces;

import dmit2015.model.Task;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;

import lombok.Getter;
import lombok.Setter;
import net.datafaker.Faker;
import org.omnifaces.util.Messages;

/**
 * View-scoped backing bean: lives across postbacks on the SAME view.
 * Destroyed when navigating away to a different view.
 */
@Named("example3DataTableView")
@ViewScoped // Survives postbacks (including AJAX) on this view; Serializable required
public class Example3DataTableView implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(Example3DataTableView.class.getName());

    @Getter
    private List<Task> tasks = new ArrayList<>();

    @Getter
    private List<Task> completedTasks = new ArrayList<>();

    @Getter
    @Setter
    private Task newTask = new Task();

    @PostConstruct // Runs after @Inject fields are initialized (once per view instance)
    public void init() {
        // Seed the list of tasks with 5 random tasks
        var faker = new Faker();
        String[] priorities = {"Low","Medium","High"};
        for (int count = 1; count <= 5; count++) {
            Task currentTask = new Task();
            currentTask.setDescription("Nuke " + faker.fallout().location());
            currentTask.setPriority(priorities[RandomGenerator.getDefault().nextInt(priorities.length)]);
            currentTask.setDone(faker.bool().bool());

            if (currentTask.isDone()) {
                completedTasks.add(currentTask);
            } else {
                tasks.add(currentTask);
            }
        }
    }

    public void onSubmitAddTask() {
        try {
            // Add newTask to our list of tasks
            tasks.add(newTask);
            // Add GlobalInfo message
            Messages.addGlobalInfo("Added task {0}", newTask);
            // Create a new Task to add
            newTask = new Task();
        } catch (Exception ex) {
            handleException(ex, "Unable to process your request.");
        }
    }

    public void onClear() {
        newTask = new Task();
        tasks.clear();;
    }

    public void onRemoveTask(Task selectedTask) {
        completedTasks.remove(selectedTask);
    }
    public void onDoneTask(Task selectedTask) {
        selectedTask.setDone(true);
        completedTasks.add(selectedTask);
        tasks.remove(selectedTask);
    }
    public void onUndoneTask(Task selectedTask) {
        selectedTask.setDone(false);
        tasks.add(selectedTask);
        completedTasks.remove(selectedTask);
    }

    /**
     * Log server-side and show a concise root-cause chain in the UI.
     * Assumes the page includes <p:messages id="error" />.
     */
    protected void handleException(Throwable ex, String userMessage) {
        LOG.log(Level.SEVERE, userMessage != null ? userMessage : "Unhandled error", ex);

        StringBuilder details = new StringBuilder();
        Throwable t = ex;
        while (t != null) {
            String msg = t.getMessage();
            if (msg != null && !msg.isBlank()) {
                details.append(t.getClass().getSimpleName())
                        .append(": ")
                        .append(msg);
                if (t.getCause() != null) details.append("  Caused by: ");
            }
            t = t.getCause();
        }

        try {
            Messages.create(userMessage != null ? userMessage : "An unexpected error occurred.")
                    .detail(details.toString())
                    .error()
                    .add("messages");
        } catch (Throwable ignored) {
            // No FacesContext available; skip UI message safely.
        }
    }
}