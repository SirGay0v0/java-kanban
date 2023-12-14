package Manager;

import ManagerBusinessLogic.*;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Интерфейс с основными командами по взаимодействию с программой.
 */

public interface TaskManager {

    ArrayList getTasks();

    ArrayList getEpics();

    ArrayList getSubtasks();

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

    ArrayList getAllSubtasksFromEpic(int id);

}

