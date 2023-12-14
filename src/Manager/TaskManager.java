package Manager;

import ManagerBusinessLogic.*;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс управляет всем взаимодействием с Task, Epic и Subtask.
 * При инициализации создает один Список и три HashMap.
 * Каждая HashMap хранит в себе объекты соответствующего типа.
 * Список содержит в себе все HashMap под определенными индексами.
 * Индексы используются в качестве параметров передаваемыми в методы по типу getTasks -
 * метод получает индекс нужной HashMap и достает объекты конкретно из нее, не затрагивая
 * остальные HashMap.
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

    ArrayList getHistory();
}

