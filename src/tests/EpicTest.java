package tests;

import Manager.Managers;
import Manager.TaskManager;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {
    TaskManager inMemoryManager;
    Epic testEpic;

    @BeforeEach
    public void beforeEach() {
        inMemoryManager = Managers.getDefault();
        testEpic = new Epic("test epic", "some description");
        inMemoryManager.createEpic(testEpic);
    }

    @Test
    public void shouldReturnNewStatusEpicWhenSubtasksAreNew() {
        Subtask subtask1 = new Subtask("s", "d", Status.NEW, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.NEW, 0,"2020-01-01 00:00","1440");

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.NEW, testEpic.getEpicStatus());
    }

    @Test
    public void shouldReturnDoneStatusEpicWhenSubtasksAreDone() {
        Subtask subtask1 = new Subtask("s", "d", Status.DONE, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.DONE, 0);

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.DONE, testEpic.getEpicStatus());
    }

    @Test
    public void shouldReturnInProgressStatusEpicWhenSubtasksAreNewAndDone() {
        Subtask subtask1 = new Subtask("s", "d", Status.NEW, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.DONE, 0);

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.IN_PROGRESS, testEpic.getEpicStatus());
    }

    @Test
    public void shouldReturnINPROGRESSStatusEpicWhenSubtasksAreInProgress() {
        Subtask subtask1 = new Subtask("s", "d", Status.IN_PROGRESS, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.IN_PROGRESS, 0);

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.IN_PROGRESS, testEpic.getEpicStatus());
    }
}
