
import Manager.InMemoryTaskManager;
import Manager.Managers;
import Manager.TaskManager;
import Tasks.Epic;
import Tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerTest {
    TaskManager taskManager = new InMemoryTaskManager();

    @Test
    public void addtToEmptyHistoryList() {
        Task task = new Task("a", "a");
        taskManager.createTask(task);
        taskManager.getTaskById(0);
        assertEquals(Collections.singletonList(task), taskManager.getTasks());
    }

    @Test
    public void addDuplicationToHistoryList() {
        Task task = new Task("a", "a");
        taskManager.createTask(task);
        taskManager.createTask(task);
        taskManager.getTaskById(0);
        assertEquals(List.of(task), taskManager.getTasks());
    }

    @Test
    public void remove() {
        Task taskZero = new Task("a", "a");
        Task taskFirst = new Task("b", "b");
        taskManager.createTask(taskZero);
        taskManager.createTask(taskFirst);
        taskManager.getTaskById(0);
        taskManager.getTaskById(1);
        taskManager.deleteTaskById(0);
        assertEquals(Collections.singletonList(taskFirst), taskManager.getHistory());
    }

    @Test
    public void removeWithoutAnyTasks() {
        taskManager.deleteTaskById(0);
        assertNull(taskManager.getHistory());
    }

    @Test
    public void removeDuplicate() {
        Task taskZero = new Task("a", "a");
        taskManager.createTask(taskZero);
        taskManager.getTaskById(0);
        taskManager.deleteTaskById(0);
        taskManager.deleteTaskById(0);
        assertNull(taskManager.getHistory());
    }

    @Test
    public void removeHeadBodyTail() {
        Epic epic0 = new Epic("a", "a");
        Epic epic1 = new Epic("b", "b");
        Epic epic2 = new Epic("c", "c");
        Epic epic3 = new Epic("d", "d");
        Epic epic4 = new Epic("e", "e");
        taskManager.createEpic(epic0);
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        taskManager.createEpic(epic3);
        taskManager.createEpic(epic4);

        taskManager.getEpicById(0);
        taskManager.getEpicById(1);
        taskManager.getEpicById(2);
        taskManager.getEpicById(3);
        taskManager.getEpicById(4);

        taskManager.deleteEpicById(0);
        List<Task> testList = new ArrayList<>(Arrays.asList(epic1, epic2, epic3, epic4));
        assertEquals(testList, taskManager.getHistory());

        taskManager.deleteEpicById(4);
        testList = new ArrayList<>(Arrays.asList(epic1, epic2, epic3));
        assertEquals(testList, taskManager.getHistory());

        taskManager.deleteEpicById(2);
        testList = new ArrayList<>(Arrays.asList(epic1, epic3));
        assertEquals(testList, taskManager.getHistory());
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
    public void getEmptyHistory() {
        assertNull(taskManager.getHistory());
    }

    @Test
    public void linklast() {
        Task task0 = new Task("0", "0");
        Task task1 = new Task("1", "1");
        Task task2 = new Task("2", "2");
        taskManager.createTask(task0);
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        taskManager.getTaskById(2);
        taskManager.getTaskById(1);
        taskManager.getTaskById(0);
        List<Task> testList = new ArrayList<>(Arrays.asList(task2, task1, task0));
        assertEquals(testList, taskManager.getHistory());
    }

    @Test
    public void getTasks() {
        Task task0 = new Task("0", "0");
        Task task1 = new Task("1", "1");
        taskManager.createTask(task0);
        taskManager.createTask(task1);
        taskManager.getTaskById(0);
        taskManager.getTaskById(1);
        assertEquals(Arrays.asList(task0, task1), taskManager.getTasks());
    }
    @Test
    public void getTasksWhenHistoryEmpty() {
        assertEquals(Collections.EMPTY_LIST,taskManager.getTasks());
    }
}
