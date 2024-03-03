package Manager;


import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;


import java.util.Collection;


/**
 * Интерфейс с основными командами по взаимодействию с программой.
 */

public interface TaskManager {

    Collection<Task> getTasks();

    Collection<Epic> getEpics();

    Collection<Subtask> getSubtasks();

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    void deleteTasks();

    void deleteEpics();

    void deleteSubtasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    void updateTask(Task task, int id);

    void updateEpic(Epic epic, int id);

    void updateSubtask(Subtask subtask, int id);

    void deleteTaskById(int id);

    void deleteEpicById(int id);

    void deleteSubtaskById(int id);

    Collection<Integer> getAllSubtasksFromEpic(int id);

    Collection<Task> getHistory();

    Collection<? extends Task> getPrioritizedTasks();

}

