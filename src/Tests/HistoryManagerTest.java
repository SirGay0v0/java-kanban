package Tests;

import Manager.Managers;
import Manager.TaskManager;
import Tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerTest {
    TaskManager taskManager = Managers.getDefault();

    @Test
    public void add() {
        Task task = new Task("a", "a");
        taskManager.createTask(task);
        taskManager.getTaskById(0);
        assertEquals(Collections.singletonList(task), taskManager.getTasks());
    }

    @Test
    public void remove() {
        Task taskFirst = new Task("a", "a");
        Task taskSecond = new Task("b", "b");
        taskManager.createTask(taskFirst);
        taskManager.createTask(taskSecond);
        taskManager.getTaskById(0);
        taskManager.getTaskById(1);
        taskManager.deleteTaskById(0);
        assertEquals(Collections.singletonList(taskSecond), taskManager.getHistory());
    }

    @Test
    public void getHistory() {
        Task taskFirst = new Task("a", "a");
        Task taskSecond = new Task("b", "b");
        taskManager.createTask(taskFirst);
        taskManager.createTask(taskSecond);
        taskManager.getTaskById(1);
        taskManager.getTaskById(0);
        List<Task> testList = new ArrayList<>(Arrays.asList(taskSecond, taskFirst));
        assertEquals(testList, taskManager.getHistory());
    }

    @Test
    public void linklast() {
        Task task0 = new Task("0", "0");
        Task task1 = new Task("1", "1");
        Task task2 = new Task("2", "2");
        taskManager.createTask(task2);
        taskManager.createTask(task1);
        taskManager.createTask(task0);
        taskManager.getTaskById(2);
        taskManager.getTaskById(1);
        taskManager.getTaskById(0);
        List<Task> testList = new ArrayList<>(Arrays.asList(task0,task1,task2));
        assertEquals(testList, taskManager.getHistory());
    }
    @Test
    public void getTasks(){
        Task task0 = new Task("0", "0");
        Task task1 = new Task("1", "1");
        taskManager.createTask(task1);
        taskManager.createTask(task0);
    }
}
