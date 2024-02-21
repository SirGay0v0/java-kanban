package tests;

import org.junit.jupiter.api.Test;

public class InMemoryTaskManagerTest extends TaskManagerTest {

    @Test
    public void getTasks() {
        getTasks(TestMode.INMEMORYMODE);
    }

    @Test
    public void getTasksWhenCollectionEmpty() {
        getTasksWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void getEpics() {
        getEpics(TestMode.INMEMORYMODE);
    }

    @Test
    public void getEpicsWhenCollectionEmpty() {
        getEpicsWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void getSubtasks() {
        getSubtasks(TestMode.INMEMORYMODE);
    }

    @Test
    public void getSubtasksWhenCollectionEmpty() {
        getSubtasksWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void createTask() {
        createTask(TestMode.INMEMORYMODE);
    }

    @Test
    public void createEpic() {
        createEpic(TestMode.INMEMORYMODE);
    }

    @Test
    public void createSubtask() {
        createSubtask(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteTasks() {
        deleteTasks(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteEpics() {
        deleteEpics(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteSubtasks() {
        deleteSubtasks(TestMode.INMEMORYMODE);
    }

    @Test
    public void getTaskById() {
        getTaskById(TestMode.INMEMORYMODE);
    }

    @Test
    public void getTaskByIdWhenCollectionEmpty() {
        getTaskByIdWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void getTaskByIdWhenIdIncorect() {
        getTaskByIdWhenIdIncorect(TestMode.INMEMORYMODE);
    }

    @Test
    public void getEpicByIdWhenCollectionEmpty() {
        getEpicByIdWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void getEpicByIdWhenIdIncorect() {
        getEpicByIdWhenIdIncorect(TestMode.INMEMORYMODE);
    }

    @Test
    public void getSubtaskById() {
        getSubtaskById(TestMode.INMEMORYMODE);
    }

    @Test
    public void getSubtaskByIdWhenCollectionEmpty() {
        getSubtaskByIdWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void getSubtaskByIdWhenIdIncorect() {
        getSubtaskByIdWhenIdIncorect(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateTask() {
        updateTask(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateTaskWhenCollectionEmpty() {
        updateTaskWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateTaskWhenIdIncorect() {
        updateTaskWhenIdIncorect(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateEpic() {
        updateEpic(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateEpicWhenCollectionEmpty() {
        updateEpicWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateEpicWhenIdIncorect() {
        updateEpicWhenIdIncorect(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateSubtask() {
        updateSubtask(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateSubtaskWhenCollectionEmpty() {
        updateSubtaskWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void updateSubtaskWhenIdIncorect() {
        updateSubtaskWhenIdIncorect(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteTaskById() {
        deleteTaskById(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteTaskByIdWhenCollectionEmpty() {
        deleteTaskByIdWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteEpicById() {
        deleteEpicById(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteEpicByIdWhenCollectionEmpty() {
        deleteEpicByIdWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteSubtaskById() {
        deleteSubtaskById(TestMode.INMEMORYMODE);
    }

    @Test
    public void deleteSubtaskByIdWhenCollectionEmpty() {
        deleteSubtaskByIdWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void getAllSubtasksFromEpic() {
        getAllSubtasksFromEpic(TestMode.INMEMORYMODE);
    }

    @Test
    public void getAllSubtasksFromEpicWhenCollectionEmpty() {
        getAllSubtasksFromEpicWhenCollectionEmpty(TestMode.INMEMORYMODE);
    }

    @Test
    public void getAllSubtasksFromEpicWhenIdIncorrect() {
        getAllSubtasksFromEpicWhenIdIncorrect(TestMode.INMEMORYMODE);
    }

    @Test
    public void getHistory() {
        getHistory(TestMode.INMEMORYMODE);
    }

    @Test
    public void getHistoryWhenHistoryEmpty() {
        getHistoryWhenHistoryEmpty(TestMode.INMEMORYMODE);
    }
}
