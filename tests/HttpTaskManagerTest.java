import Manager.HttpTaskManager;
import Manager.KVServer;
import Manager.Managers;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpTaskManagerTest {
    KVServer server;
    HttpTaskManager manager;
    Task task;
    Epic epic;
    Subtask subtask1;
    Subtask subtask2;

    @BeforeEach
    public void beforeEach() throws IOException, InterruptedException {
        server = new KVServer();
        server.start();
        manager = Managers.getDefault();
        task = new Task("taskName", "taskDesc", Status.NEW, "2000-01-01 00:00", "1440");
        epic = new Epic("epicName", "epicDesc");
        subtask1 = new Subtask("subName", "subDesc", Status.NEW, 1, "2001-01-01 00:00", "1440");
        subtask2 = new Subtask("subName", "subDesc", Status.DONE, 1, "2002-01-01 00:00", "1440");
    }

    @AfterEach
    public void afterEach() {
        server.stop();
    }

    @Test
    public void shouldReturnCorrectTaskEpicSubtaskFromServer() {
        manager.createTask(task);
        manager.createEpic(epic);
        manager.createSubtask(subtask1);
        assertEquals(task, manager.getTaskById(0));
        assertEquals(epic, manager.getEpicById(1));
        assertEquals(subtask1, manager.getSubtaskById(2));
    }

    @Test
    public void shouldReturnCorrectTaskEpicSubtaskFromServerAfterTheirDelete() {
        manager.createTask(task);
        manager.createEpic(epic);
        manager.createSubtask(subtask1);
        manager.deleteTaskById(0);
        manager.deleteEpicById(1);
        assertEquals(Collections.emptyList(), manager.getTasks());
        assertEquals(Collections.emptyList(), manager.getSubtasks());
        assertEquals(Collections.emptyList(), manager.getEpics());
    }

    @Test
    public void shouldReturnCorrectTaskEpicSubtaskFromServerAfterUpdate() {
        manager.createTask(task);
        manager.createEpic(epic);
        manager.createSubtask(subtask1);
        task = new Task("taskName", "taskDesc", Status.IN_PROGRESS, "2000-01-01 00:00", "1440");
        manager.updateTask(task, 0);
        epic = new Epic("epicName", "epicDescription");
        manager.updateEpic(epic, 1);
        subtask1 = new Subtask("subName", "subDesc", Status.DONE, 1, "2001-01-01 00:00", "1440");
        manager.updateSubtask(subtask1, 2);
        assertEquals(task, manager.getTaskById(0));
        assertEquals(epic, manager.getEpicById(1));
        assertEquals(subtask1, manager.getSubtaskById(2));
    }

    @Test
    public void shouldReturnCorrectTaskFromServerAfterDeleteAllTasks() {
        manager.createTask(task);
        task = new Task("taskName", "taskDesc", Status.IN_PROGRESS, "2000-01-01 00:00", "1440");
        manager.createTask(task);
        manager.createEpic(epic);
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);
        manager.deleteTasks();
        assertEquals(Collections.emptyList(), manager.getTasks());
        manager.deleteSubtasks();
        assertEquals(Collections.emptyList(), manager.getSubtasks());
        manager.deleteEpics();
        assertEquals(Collections.emptyList(), manager.getEpics());
    }

    @Test
    public void shouldReturnCorrectPriorityTaskFromServer() {
        manager.createTask(task);
        manager.createEpic(epic);
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);

        assertEquals(Stream.of(task, subtask1, subtask2).collect(Collectors.toSet()), manager.getPrioritizedTasks());
    }
}
