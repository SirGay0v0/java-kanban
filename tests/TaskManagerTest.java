


import Manager.FileBackedTaskManager;
import Manager.InMemoryTaskManager;
import Manager.TaskManager;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;

import java.io.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TaskManagerTest<T extends TaskManager> {
    TaskManager taskManager;
    private TestMode mode;

    public void generateRightManager(TestMode mode) {
        if (mode== TestMode.FILEBACKEDMODE) {
            File file = new File("save.csv");
            clearFile(file);
            taskManager = new FileBackedTaskManager(file);
        } else {
            taskManager = new InMemoryTaskManager();
        }
    }

    public void clearFile(File file) {
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTasks(TestMode mode) {
        generateRightManager(mode);

        Task task1 = new Task("test1", "test1", Status.NEW);
        Task task2 = new Task("test2", "test2", Status.IN_PROGRESS);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Collection testList = new ArrayList<>(Arrays.asList(task1, task2));


        Collection listOfTasks = taskManager.getTasks();

        assertEquals(testList, listOfTasks);
    }

    public void getTasksWhenCollectionEmpty(TestMode mode) {
        generateRightManager(mode);
        Collection testList = new ArrayList<>();
        Collection listOfTasks = taskManager.getTasks();
        assertEquals(testList, listOfTasks);
    }

    public void getEpics(TestMode mode) {
        generateRightManager(mode);

        Epic epic1 = new Epic("test1", "test1");
        Epic epic2 = new Epic("test2", "test2");

        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);

        Collection testList = new ArrayList<>(Arrays.asList(epic1, epic2));
        Collection listOfTasks = taskManager.getEpics();

        assertEquals(testList, listOfTasks);
    }

    public void getEpicsWhenCollectionEmpty(TestMode mode) {
        generateRightManager(mode);
        Collection testList = new ArrayList<>();
        Collection listOfTasks = taskManager.getEpics();
        assertEquals(testList, listOfTasks);
    }

    public void getSubtasks(TestMode mode) {
        generateRightManager(mode);

        Epic epic = new Epic("t", "t");
        Subtask subtask1 = new Subtask("test1", "test1", Status.NEW, 0);
        Subtask subtask2 = new Subtask("test2", "test2", Status.DONE, 0);

        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        Collection testList = new ArrayList<>();
        testList.add(subtask1);
        testList.add(subtask2);

        Collection listOfTasks = taskManager.getSubtasks();

        assertEquals(testList, listOfTasks);
    }

    public void getSubtasksWhenCollectionEmpty(TestMode mode) {
        generateRightManager(mode);
        Collection testList = new ArrayList<>();
        Collection listOfTasks = taskManager.getSubtasks();
        assertEquals(testList, listOfTasks);
    }

    public void createTask(TestMode mode) {
        generateRightManager(mode);
        Task task = new Task("t", "t");
        taskManager.createTask(task);
        assertEquals(task, taskManager.getTaskById(0));
    }

    public void createEpic(TestMode mode) {
        this.mode = mode;
        generateRightManager(mode);
        Epic epic = new Epic("t", "t");
        taskManager.createEpic(epic);
        assertEquals(epic, taskManager.getEpicById(0));
    }

    public void createSubtask(TestMode mode) {
        generateRightManager(mode);
        Epic epic = new Epic("j", "f");
        Subtask subtask = new Subtask("t", "t", Status.NEW, 0);
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask);
        assertEquals(subtask, taskManager.getSubtaskById(1));
    }

    public void deleteTasks(TestMode mode) {
        generateRightManager(mode);
        Task task = new Task("t", "t");
        taskManager.createTask(task);
        Collection testCollection = new ArrayList();
        taskManager.deleteTasks();
        Collection testMethod = taskManager.getTasks();
        assertEquals(testCollection, testMethod);
    }

    public void deleteEpics(TestMode mode) {
        generateRightManager(mode);
        Epic epic = new Epic("l", "l");
        taskManager.createEpic(epic);
        Collection testCollection = new ArrayList();
        taskManager.deleteEpics();
        Collection testMethod = taskManager.getEpics();
        assertEquals(testCollection, testMethod);
    }

    public void deleteSubtasks(TestMode mode) {
        generateRightManager(mode);
        Epic epic = new Epic("l", "l");
        Subtask subtask = new Subtask("t", "t", Status.NEW, 0);
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask);
        Collection testCollection = new ArrayList();
        taskManager.deleteSubtasks();
        Collection testMethod = taskManager.getSubtasks();
        assertEquals(testCollection, testMethod);
    }

    public void getTaskById(TestMode mode) {
        generateRightManager(mode);
        Task task = new Task("t", "t");
        taskManager.createTask(task);
        Task testTask = taskManager.getTaskById(0);
        assertEquals(task, testTask);
    }

    public void getTaskByIdWhenCollectionEmpty(TestMode mode) {
        assertThrows(NullPointerException.class, () -> {
            generateRightManager(mode);
            Task task = taskManager.getTaskById(0);
        });
    }

    public void getTaskByIdWhenIdIncorect(TestMode mode) {
        assertThrows(NullPointerException.class, () -> {
            generateRightManager(mode);
            Task task = new Task("t", "t");
            taskManager.createTask(task);
            Task testTask = taskManager.getTaskById(1);
        });
    }

    public void getEpicById(TestMode mode) {
        generateRightManager(mode);
        Epic epic = new Epic("t", "t");
        taskManager.createEpic(epic);
        Epic testTask = taskManager.getEpicById(0);
        assertEquals(epic, testTask);
    }

    public void getEpicByIdWhenCollectionEmpty(TestMode mode) {
        assertThrows(NullPointerException.class, () -> {
            generateRightManager(mode);
            Epic epic = taskManager.getEpicById(0);
        });
    }

    public void getEpicByIdWhenIdIncorect(TestMode mode) {
        assertThrows(NullPointerException.class, () -> {
            generateRightManager(mode);
            Epic epic = new Epic("t", "t");
            taskManager.createTask(epic);
            Epic testEpic = taskManager.getEpicById(1);
        });
    }

    public void getSubtaskById(TestMode mode) {
        generateRightManager(mode);
        Epic epic = new Epic("t", "t");
        Subtask subtask = new Subtask("t", "t", Status.NEW, 0);
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask);
        Subtask subtasktest = taskManager.getSubtaskById(1);
        assertEquals(subtask, subtasktest);
    }

    public void getSubtaskByIdWhenCollectionEmpty(TestMode mode) {
        assertThrows(NullPointerException.class, () -> {
            generateRightManager(mode);
            Subtask task = taskManager.getSubtaskById(0);
        });
    }

    public void getSubtaskByIdWhenIdIncorect(TestMode mode) {
        assertThrows(NullPointerException.class, () -> {
            generateRightManager(mode);
            Epic epic = new Epic("t", "t");
            Subtask subtask = new Subtask("t", "t", Status.NEW, 0);
            taskManager.createEpic(epic);
            taskManager.createTask(subtask);
            Epic testTask = taskManager.getEpicById(2);
        });
    }

    public void updateTask(TestMode mode) {
        generateRightManager(mode);
        Task testTask = new Task("f", "f");
        taskManager.createTask(testTask);
        Task updateTestTask = new Task("a", "a");
        taskManager.updateTask(updateTestTask, 0);
        assertEquals(updateTestTask, taskManager.getTaskById(0));
    }

    public void updateTaskWhenCollectionEmpty(TestMode mode) {
        generateRightManager(mode);
        Task updateTestTask = new Task("a", "a");
        taskManager.updateTask(updateTestTask, 0);
        Collection testCollection = taskManager.getSubtasks();
        Collection emptyCollection = new ArrayList<>();
        assertEquals(emptyCollection, testCollection);
    }

    public void updateTaskWhenIdIncorect(TestMode mode) {
        generateRightManager(mode);
        Task updateTestTask = new Task("a", "a");
        taskManager.createTask(updateTestTask);
        taskManager.updateTask(updateTestTask, 1);
        Collection testCollection = taskManager.getTasks();
        Collection collection = new ArrayList<>(Arrays.asList(updateTestTask));
        assertEquals(collection, testCollection);
    }

    public void updateEpic(TestMode mode) {
        generateRightManager(mode);
        Epic testTask = new Epic("f", "f");
        taskManager.createEpic(testTask);
        Epic updateTestTask = new Epic("a", "a");
        taskManager.updateEpic(updateTestTask, 0);
        assertEquals(updateTestTask, taskManager.getEpicById(0));
    }

    public void updateEpicWhenCollectionEmpty(TestMode mode) {
        generateRightManager(mode);
        Epic updateTestTask = new Epic("a", "a");
        taskManager.updateEpic(updateTestTask, 0);
        Collection testCollection = taskManager.getEpics();
        Collection emptyCollection = new ArrayList<>();
        assertEquals(emptyCollection, testCollection);
    }

    public void updateEpicWhenIdIncorect(TestMode mode) {
        generateRightManager(mode);
        Epic updateTestTask = new Epic("a", "a");
        taskManager.createEpic(updateTestTask);
        taskManager.updateEpic(updateTestTask, 1);
        Collection testCollection = taskManager.getEpics();
        Collection collection = new ArrayList<>(Arrays.asList(updateTestTask));
        assertEquals(collection, testCollection);
    }

    public void updateSubtask(TestMode mode) {
        generateRightManager(mode);
        Epic testTask = new Epic("f", "f");
        Subtask testSubtask = new Subtask("f", "f", Status.NEW, 0);
        taskManager.createEpic(testTask);
        taskManager.createSubtask(testSubtask);
        Subtask updateSubtask = new Subtask("a", "a", Status.NEW, 0);
        taskManager.updateSubtask(updateSubtask, 1);
        assertEquals(updateSubtask, taskManager.getSubtaskById(1));
    }

    public void updateSubtaskWhenCollectionEmpty(TestMode mode) {
        generateRightManager(mode);

        Subtask updateSubtask = new Subtask("a", "a", Status.NEW, 0);
        taskManager.updateSubtask(updateSubtask, 1);
        Collection testCollection = taskManager.getSubtasks();
        Collection emptyCollection = new ArrayList<>();
        assertEquals(emptyCollection, testCollection);
    }

    public void updateSubtaskWhenIdIncorect(TestMode mode) {
        generateRightManager(mode);
        Epic testTask = new Epic("f", "f");
        Subtask testSubtask = new Subtask("f", "f", Status.NEW, 0);
        Subtask updateSubtask = new Subtask("a", "a", Status.NEW, 0);
        taskManager.createEpic(testTask);
        taskManager.createSubtask(testSubtask);
        taskManager.updateSubtask(updateSubtask, 2);
        Collection testCollection = taskManager.getSubtasks();
        Collection collection = new ArrayList<>(Arrays.asList(testSubtask));
        assertEquals(collection, testCollection);
    }

    public void deleteTaskById(TestMode mode) {
        generateRightManager(mode);
        Task testTask = new Task("f", "f");
        taskManager.createTask(testTask);
        taskManager.deleteTaskById(0);
        assertEquals(Collections.EMPTY_LIST, taskManager.getTasks());
    }

    public void deleteTaskByIdWhenCollectionEmpty(TestMode mode) {
            generateRightManager(mode);
            taskManager.deleteTaskById(0);
        assertEquals(Collections.EMPTY_LIST, taskManager.getTasks());
    }

    public void deleteEpicById(TestMode mode) {
            generateRightManager(mode);
            Epic testTask = new Epic("f", "f");
            taskManager.createEpic(testTask);
            taskManager.deleteEpicById(0);
        assertEquals(Collections.EMPTY_LIST, taskManager.getEpics());
    }

    public void deleteEpicByIdWhenCollectionEmpty(TestMode mode) {
            generateRightManager(mode);
            taskManager.deleteEpicById(0);
        assertEquals(Collections.EMPTY_LIST, taskManager.getEpics());
    }

    public void deleteSubtaskById(TestMode mode) {

            generateRightManager(mode);
            Epic testEpic = new Epic("f", "f");
            Subtask testTask = new Subtask("f", "f", Status.NEW, 0);
            taskManager.createEpic(testEpic);
            taskManager.createSubtask(testTask);
            taskManager.deleteSubtaskById(1);
        assertEquals(Collections.EMPTY_LIST, taskManager.getSubtasks());
    }

    public void deleteSubtaskByIdWhenCollectionEmpty(TestMode mode) {
            generateRightManager(mode);
            taskManager.deleteTaskById(0);
        assertEquals(Collections.EMPTY_LIST, taskManager.getSubtasks());
    }

    public void getAllSubtasksFromEpic(TestMode mode) {
        generateRightManager(mode);
        Epic testEpic = new Epic("f", "f");
        Subtask testTask = new Subtask("f", "f", Status.NEW, 0);
        taskManager.createEpic(testEpic);
        taskManager.createSubtask(testTask);
        Collection collection = new ArrayList<>(Arrays.asList(1));
        Collection testCollection = taskManager.getAllSubtasksFromEpic(0);
        assertEquals(collection, testCollection);
    }

    public void getAllSubtasksFromEpicWhenCollectionEmpty(TestMode mode) {
        generateRightManager(mode);
        Epic testEpic = new Epic("f", "f");
        taskManager.createEpic(testEpic);
        Collection collection = new ArrayList<>();
        Collection testCollection = taskManager.getAllSubtasksFromEpic(0);
        assertEquals(collection, testCollection);
    }

    public void getAllSubtasksFromEpicWhenIdIncorrect(TestMode mode) {
        assertThrows(NullPointerException.class, () -> {
            generateRightManager(mode);
            Epic testEpic = new Epic("f", "f");
            Subtask testTask = new Subtask("f", "f", Status.NEW, 0);
            taskManager.createSubtask(testTask);
            taskManager.createEpic(testEpic);
            taskManager.getAllSubtasksFromEpic(1);
        });
    }

    public void getHistory(TestMode mode) {
        generateRightManager(mode);
        Task task1 = new Task("a", "a");
        Task task2 = new Task("b", "b");
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.getTaskById(0);
        taskManager.getTaskById(1);
        Collection testCollection = new ArrayList<>(Arrays.asList(task1, task2));
        assertEquals(testCollection, taskManager.getHistory());
    }

    public void getHistoryWhenHistoryEmpty(TestMode mode) {
        generateRightManager(mode);
        assertNull(taskManager.getHistory());
    }
}
