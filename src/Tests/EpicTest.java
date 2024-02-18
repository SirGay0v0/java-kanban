package Tests;

import Manager.Managers;
import Manager.TaskManager;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {

    TaskManager inMemoryManager = Managers.getDefault();
    Epic testEpic = new Epic("test epic", "some description");

    @Test
    public void shouldReturnNewStatusEpicWhenSubtasksAreNew() {
        inMemoryManager.createEpic(testEpic);
        Subtask subtask1 = new Subtask("s", "d", Status.NEW, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.NEW, 0);

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.NEW, testEpic.getEpicStatus());
    }

    @Test
    public void shouldReturnDoneStatusEpicWhenSubtasksAreDone() {
        inMemoryManager.createEpic(testEpic);
        Subtask subtask1 = new Subtask("s", "d", Status.DONE, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.DONE, 0);

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.DONE, testEpic.getEpicStatus());
    }

    @Test
    public void shouldReturnInProgressStatusEpicWhenSubtasksAreNewAndDone() {
        inMemoryManager.createEpic(testEpic);
        Subtask subtask1 = new Subtask("s", "d", Status.NEW, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.DONE, 0);

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.IN_PROGRESS, testEpic.getEpicStatus());
    }

    @Test
    public void shouldReturnINPROGRESSStatusEpicWhenSubtasksAreInProgress() {
        inMemoryManager.createEpic(testEpic);
        Subtask subtask1 = new Subtask("s", "d", Status.IN_PROGRESS, 0);
        Subtask subtask2 = new Subtask("s", "d", Status.IN_PROGRESS, 0);

        inMemoryManager.createSubtask(subtask1);
        inMemoryManager.createSubtask(subtask2);

        assertEquals(Status.IN_PROGRESS, testEpic.getEpicStatus());
    }
}
