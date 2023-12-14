import Manager.HistoryManager;
import Manager.InMemoryTaskManager;
import Manager.Managers;
import Manager.TaskManager;
import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;

/**
 * Все проверки, которые были указаны в ТЗ прошли проверку без ошибок.
 */
public class Main {
    public static void main(String[] args) {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

        Task task;
        Epic epic;
        Subtask subtask;

        task = new Task("First task", "To do smth", Status.NEW);
        inMemoryTaskManager.createTask(task);
        task = new Task("Second task", "Smth is Done", Status.NEW);
        inMemoryTaskManager.createTask(task);

        epic = new Epic("First epic", "Epic with 2 subtasks");
        inMemoryTaskManager.createEpic(epic);
        epic = new Epic("Second epic", "Epic with 1 subtask");
        inMemoryTaskManager.createEpic(epic);

        subtask = new Subtask("First subtask", "Subtask1 for first epic", Status.NEW, 2);
        inMemoryTaskManager.createSubtask(subtask);
        subtask = new Subtask("Second subtask", "Subtask2 for first epic", Status.NEW, 2);
        inMemoryTaskManager.createSubtask(subtask);

        subtask = new Subtask("First subtask", "Subtask for second epic", Status.NEW, 3);
        inMemoryTaskManager.createSubtask(subtask);

        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println();
        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println();
        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println();

        task = inMemoryTaskManager.getTaskById(0);
        task.setName("Renamed Task1");
        task.setTaskStatus(Status.IN_PROGRESS);
        inMemoryTaskManager.updateTask(task, 0);

        task = new Task("Second Task", "Redescription task 2", Status.DONE);
        task.setId(1);
        inMemoryTaskManager.updateTask(task, 1);

        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println();

        epic = inMemoryTaskManager.getEpicById(2);
        epic.setName("Renamed epic1");
        inMemoryTaskManager.updateEpic(epic, 2);

        epic = new Epic("Second epic", "Redescription");
        epic.setId(3);
        epic.setSubtasksList(inMemoryTaskManager.getAllSubtasksFromEpic(3));
        inMemoryTaskManager.updateEpic(epic, 3);

        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println();

        subtask = inMemoryTaskManager.getSubtaskById(4);
        subtask.setName("Renamed first subtask of first epic");
        subtask.setSubtaskStatus(Status.IN_PROGRESS);
        inMemoryTaskManager.updateSubtask(subtask, 4);

        subtask = new Subtask("First subtask", "Redescription", Status.DONE, 3);
        inMemoryTaskManager.updateSubtask(subtask, 6);

        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println();

        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println();

        inMemoryTaskManager.deleteTaskById(0);
        inMemoryTaskManager.deleteEpicById(3);
        inMemoryTaskManager.deleteSubtaskById(4);

        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());

        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);

        System.out.println(inMemoryHistoryManager.getHistory());
    }
}