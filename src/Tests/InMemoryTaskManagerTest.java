package Tests;

import org.junit.jupiter.api.Test;

public class InMemoryTaskManagerTest extends TaskManagerTest {

    @Test
    public void getTasks() {
        getTasks("");
    }

    @Test
    public void getTasksWhenCollectionEmpty() {
        getTasksWhenCollectionEmpty("");
    }

    @Test
    public void getEpics() {
        getEpics("");
    }

    @Test
    public void getEpicsWhenCollectionEmpty() {
        getEpicsWhenCollectionEmpty("");
    }

    @Test
    public void getSubtasks() {
        getSubtasks("");
    }

    @Test
    public void getSubtasksWhenCollectionEmpty() {
        getSubtasksWhenCollectionEmpty("");
    }

    @Test
    public void createTask() {
        createTask("");
    }

    @Test
    public void createEpic() {
        createEpic("");
    }

    @Test
    public void createSubtask() {
        createSubtask("");
    }

    @Test
    public void deleteTasks() {
        deleteTasks("");
    }

    @Test
    public void deleteEpics() {
        deleteEpics("");
    }

    @Test
    public void deleteSubtasks() {
        deleteSubtasks("");
    }

    @Test
    public void getTaskById() {
        getTaskById("");
    }

    @Test
    public void getTaskByIdWhenCollectionEmpty() {
        getTaskByIdWhenCollectionEmpty("");
    }

    @Test
    public void getTaskByIdWhenIdIncorect() {
        getTaskByIdWhenIdIncorect("");
    }

    @Test
    public void getEpicByIdWhenCollectionEmpty() {
        getEpicByIdWhenCollectionEmpty("");
    }

    @Test
    public void getEpicByIdWhenIdIncorect() {
        getEpicByIdWhenIdIncorect("");
    }

    @Test
    public void getSubtaskById() {
        getSubtaskById("");
    }

    @Test
    public void getSubtaskByIdWhenCollectionEmpty() {
        getSubtaskByIdWhenCollectionEmpty("");
    }

    @Test
    public void getSubtaskByIdWhenIdIncorect() {
        getSubtaskByIdWhenIdIncorect("");
    }

    @Test
    public void updateTask() {
        updateTask("");
    }

    @Test
    public void updateTaskWhenCollectionEmpty() {
        updateTaskWhenCollectionEmpty("");
    }

    @Test
    public void updateTaskWhenIdIncorect() {
        updateTaskWhenIdIncorect("");
    }

    @Test
    public void updateEpic() {
        updateEpic("");
    }

    @Test
    public void updateEpicWhenCollectionEmpty() {
        updateEpicWhenCollectionEmpty("");
    }

    @Test
    public void updateEpicWhenIdIncorect() {
        updateEpicWhenIdIncorect("");
    }

    @Test
    public void updateSubtask() {
        updateSubtask("");
    }

    @Test
    public void updateSubtaskWhenCollectionEmpty() {
        updateSubtaskWhenCollectionEmpty("");
    }

    @Test
    public void updateSubtaskWhenIdIncorect() {
        updateSubtaskWhenIdIncorect("");
    }

    @Test
    public void deleteTaskById() {
        deleteTaskById("");
    }

    @Test
    public void deleteTaskByIdWhenCollectionEmpty() {
        deleteTaskByIdWhenCollectionEmpty("");
    }

    @Test
    public void deleteEpicById() {
        deleteEpicById("");
    }

    @Test
    public void deleteEpicByIdWhenCollectionEmpty() {
        deleteEpicByIdWhenCollectionEmpty("");
    }

    @Test
    public void deleteSubtaskById() {
        deleteSubtaskById("");
    }

    @Test
    public void deleteSubtaskByIdWhenCollectionEmpty() {
        deleteSubtaskByIdWhenCollectionEmpty("");
    }

    @Test
    public void getAllSubtasksFromEpic() {
        getAllSubtasksFromEpic("");
    }

    @Test
    public void getAllSubtasksFromEpicWhenCollectionEmpty() {
        getAllSubtasksFromEpicWhenCollectionEmpty("");
    }

    @Test
    public void getAllSubtasksFromEpicWhenIdIncorrect() {
        getAllSubtasksFromEpicWhenIdIncorrect("");
    }

    @Test
    public void getHistory() {
        getHistory("");
    }

    @Test
    public void getHistoryWhenHistoryEmpty() {
        getHistoryWhenHistoryEmpty("");
    }
}
