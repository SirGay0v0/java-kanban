

import Manager.InMemoryTaskManager;
import Manager.Managers;
import Manager.TaskManager;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtaskTest {
    TaskManager inMemoryManager = new InMemoryTaskManager();
    Epic epic = new Epic("test epic", "some description");
    Subtask subtask = new Subtask("t","t", Status.IN_PROGRESS,0,
            "2014-04-08 12:30","120");

    @Test
    void shouldReturEpicOwnerId(){
        inMemoryManager.createEpic(epic);
        inMemoryManager.createSubtask(subtask);

        assertEquals(0,inMemoryManager.getSubtaskById(1).getEpicOwnerId());
    }
}
