package Tests;

import org.junit.jupiter.api.Test;

public class FileBuckedTaskManagerTest extends TaskManagerTest {

    @Test
    public void getTasks() {
        getTasks("bucked");
    }

    @Test
    public void getTasksWhenCollectionEmpty() {
        getTasksWhenCollectionEmpty("bucked");
    }

    @Test
    public void getEpics() {
        getEpics("bucked");
    }

    @Test
    public void getEpicsWhenCollectionEmpty() {
        getEpicsWhenCollectionEmpty("bucked");
    }

    @Test
    public void getSubtasks() {
        getSubtasks("bucked");
    }

    @Test
    public void getSubtasksWhenCollectionEmpty() {
        getSubtasksWhenCollectionEmpty("bucked");
    }

    @Test
    public void createTask() {
        createTask("bucked");
    }

    @Test
    public void createEpic() {
        createEpic("bucked");
    }

    @Test
    public void createSubtask() {
        createSubtask("bucked");
    }

    @Test
    public void deleteTasks() {
        deleteTasks("bucked");
    }

    @Test
    public void deleteEpics() {
        deleteEpics("bucked");
    }

    @Test
    public void deleteSubtasks() {
        deleteSubtasks("bucked");
    }

    @Test
    public void getTaskById() {
        getTaskById("bucked");
    }

    @Test
    public void getTaskByIdWhenCollectionEmpty() {
        getTaskByIdWhenCollectionEmpty("bucked");
    }

    @Test
    public void getTaskByIdWhenIdIncorect() {
        getTaskByIdWhenIdIncorect("bucked");
    }
    @Test
    public void getEpicById() {
        getEpicById("bucked");
    }

    @Test
    public void getEpicByIdWhenCollectionEmpty() {
        getEpicByIdWhenCollectionEmpty("bucked");
    }

    @Test
    public void getEpicByIdWhenIdIncorect() {
        getEpicByIdWhenIdIncorect("bucked");
    }

    @Test
    public void getSubtaskById() {
        getSubtaskById("bucked");
    }

    @Test
    public void getSubtaskByIdWhenCollectionEmpty() {
        getSubtaskByIdWhenCollectionEmpty("bucked");
    }

    @Test
    public void getSubtaskByIdWhenIdIncorect() {
        getSubtaskByIdWhenIdIncorect("bucked");
    }

    @Test
    public void updateTask() {
        updateTask("bucked");
    }

    @Test
    public void updateTaskWhenCollectionEmpty() {
        updateTaskWhenCollectionEmpty("bucked");
    }

    @Test
    public void updateTaskWhenIdIncorect() {
        updateTaskWhenIdIncorect("bucked");
    }

    @Test
    public void updateEpic() {
        updateEpic("bucked");
    }

    @Test
    public void updateEpicWhenCollectionEmpty() {
        updateEpicWhenCollectionEmpty("bucked");
    }

    @Test
    public void updateEpicWhenIdIncorect() {
        updateEpicWhenIdIncorect("bucked");
    }

    @Test
    public void updateSubtask() {
        updateSubtask("bucked");
    }

    @Test
    public void updateSubtaskWhenCollectionEmpty() {
        updateSubtaskWhenCollectionEmpty("bucked");
    }

    @Test
    public void updateSubtaskWhenIdIncorect() {
        updateSubtaskWhenIdIncorect("bucked");
    }

    @Test
    public void deleteTaskById() {
        deleteTaskById("bucked");
    }

    @Test
    public void deleteTaskByIdWhenCollectionEmpty() {
        deleteTaskByIdWhenCollectionEmpty("bucked");
    }

    @Test
    public void deleteEpicById() {
        deleteEpicById("bucked");
    }

    @Test
    public void deleteEpicByIdWhenCollectionEmpty() {
        deleteEpicByIdWhenCollectionEmpty("bucked");
    }

    @Test
    public void deleteSubtaskById() {
        deleteSubtaskById("bucked");
    }

    @Test
    public void deleteSubtaskByIdWhenCollectionEmpty() {
        deleteSubtaskByIdWhenCollectionEmpty("bucked");
    }

    @Test
    public void getAllSubtasksFromEpic() {
        getAllSubtasksFromEpic("bucked");
    }

    @Test
    public void getAllSubtasksFromEpicWhenCollectionEmpty() {
        getAllSubtasksFromEpicWhenCollectionEmpty("bucked");
    }

    @Test
    public void getAllSubtasksFromEpicWhenIdIncorrect() {
        getAllSubtasksFromEpicWhenIdIncorrect("bucked");
    }

    @Test
    public void getHistory() {
        getHistory("bucked");
    }

    @Test
    public void getHistoryWhenHistoryEmpty() {
        getHistoryWhenHistoryEmpty("bucked");
    }
}
