import Manager.FileBackedTaskManager;
import Manager.HistoryManager;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;

public class HttpTaskManager extends FileBackedTaskManager {

    KVTaskClient client;
    public HttpTaskManager(URI url) throws IOException, InterruptedException {
        super(new File(url));
        client = new KVTaskClient(url);
    }


    @Override
    public void createTask(Task task) {
        super.createTask(task);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
    }

    @Override
    public void deleteSubtaskById(int id) {
        super.deleteSubtaskById(id);
    }

    @Override
    public Task getTaskById(int id) {
        return super.getTaskById(id);
    }

    @Override
    public Epic getEpicById(int id) {
        return super.getEpicById(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        return super.getSubtaskById(id);
    }

    @Override
    public void updateTask(Task task, int id) {
        super.updateTask(task, id);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        super.updateEpic(epic, id);
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        super.updateSubtask(subtask, id);
    }

    @Override
    public Collection getTasks() {
        return super.getTasks();
    }

    @Override
    public Collection getEpics() {
        return super.getEpics();
    }

    @Override
    public Collection getSubtasks() {
        return super.getSubtasks();
    }

    @Override
    public void deleteTasks() {
        super.deleteTasks();
    }

    @Override
    public void deleteEpics() {
        super.deleteEpics();
    }

    @Override
    public void deleteSubtasks() {
        super.deleteSubtasks();
    }

    @Override
    public Collection<? extends Task> getPrioritizedTasks() {
        return super.getPrioritizedTasks();
    }
}
