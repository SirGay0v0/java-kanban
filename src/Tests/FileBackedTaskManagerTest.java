package tests;

import Manager.Managers;
import Manager.TaskManager;
import Tasks.Epic;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FileBackedTaskManagerTest extends TaskManagerTest {

    @Test
    public void getTasks() {
        getTasks(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getTasksWhenCollectionEmpty() {
        getTasksWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getEpics() {
        getEpics(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getEpicsWhenCollectionEmpty() {
        getEpicsWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getSubtasks() {
        getSubtasks(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getSubtasksWhenCollectionEmpty() {
        getSubtasksWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void createTask() {
        createTask(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void createEpic() {
        createEpic(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void createSubtask() {
        createSubtask(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteTasks() {
        deleteTasks(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteEpics() {
        deleteEpics(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteSubtasks() {
        deleteSubtasks(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getTaskById() {
        getTaskById(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getTaskByIdWhenCollectionEmpty() {
        getTaskByIdWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getTaskByIdWhenIdIncorect() {
        getTaskByIdWhenIdIncorect(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getEpicById() {
        getEpicById(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getEpicByIdWhenCollectionEmpty() {
        getEpicByIdWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getEpicByIdWhenIdIncorect() {
        getEpicByIdWhenIdIncorect(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getSubtaskById() {
        getSubtaskById(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getSubtaskByIdWhenCollectionEmpty() {
        getSubtaskByIdWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getSubtaskByIdWhenIdIncorect() {
        getSubtaskByIdWhenIdIncorect(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateTask() {
        updateTask(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateTaskWhenCollectionEmpty() {
        updateTaskWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateTaskWhenIdIncorect() {
        updateTaskWhenIdIncorect(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateEpic() {
        updateEpic(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateEpicWhenCollectionEmpty() {
        updateEpicWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateEpicWhenIdIncorect() {
        updateEpicWhenIdIncorect(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateSubtask() {
        updateSubtask(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateSubtaskWhenCollectionEmpty() {
        updateSubtaskWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void updateSubtaskWhenIdIncorect() {
        updateSubtaskWhenIdIncorect(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteTaskById() {
        deleteTaskById(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteTaskByIdWhenCollectionEmpty() {
        deleteTaskByIdWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteEpicById() {
        deleteEpicById(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteEpicByIdWhenCollectionEmpty() {
        deleteEpicByIdWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteSubtaskById() {
        deleteSubtaskById(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void deleteSubtaskByIdWhenCollectionEmpty() {
        deleteSubtaskByIdWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getAllSubtasksFromEpic() {
        getAllSubtasksFromEpic(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getAllSubtasksFromEpicWhenCollectionEmpty() {
        getAllSubtasksFromEpicWhenCollectionEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getAllSubtasksFromEpicWhenIdIncorrect() {
        getAllSubtasksFromEpicWhenIdIncorrect(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getHistory() {
        getHistory(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void getHistoryWhenHistoryEmpty() {
        getHistoryWhenHistoryEmpty(TestMode.FILEBACKEDMODE);
    }

    @Test
    public void shouldSaveAndLoadEmptyTasks() throws IOException {
        File file = new File("save.csv");
        file.delete();
        file.createNewFile();
        TaskManager buckedManager = Managers.getDefaultFileBucked(file);
        assertNull(buckedManager.getHistory());
        buckedManager = Managers.getDefaultFileBucked(file);
        assertNull(buckedManager.getHistory());
    }

    @Test
    public void shouldSaveAndLoadWithoutSubtasks() throws IOException {
        File file = new File("save.csv");
        file.delete();
        file.createNewFile();
        Epic epic = new Epic("l", "l");
        TaskManager buckedManager = Managers.getDefaultFileBucked(file);
        buckedManager.createEpic(epic);
        buckedManager.getEpicById(0);
        assertEquals(Collections.singletonList(epic), buckedManager.getHistory());
        buckedManager = Managers.getDefaultFileBucked(file);
        assertEquals(Collections.singletonList(epic), buckedManager.getHistory());
    }

    @Test
    public void shouldSaveAndLoadEmptyHistory() throws IOException {
        File file = new File("save.csv");
        file.delete();
        file.createNewFile();
        Epic epic = new Epic("l", "l");
        TaskManager buckedManager = Managers.getDefaultFileBucked(file);
        buckedManager.createEpic(epic);
        assertNull(buckedManager.getHistory());
        buckedManager = Managers.getDefaultFileBucked(file);
        assertNull(buckedManager.getHistory());
    }
}
