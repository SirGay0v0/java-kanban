import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
/**
 * Все проверки, которые были указаны в ТЗ прошли проверку без ошибок.*/
public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Task task;
        Epic epic;
        Subtask subtask;

        task = new Task("First task", "To do smth", Status.NEW);
        manager.createTask(task);
        task = new Task("Second task", "Smth is Done", Status.NEW);
        manager.createTask(task);

        epic = new Epic("First epic", "Epic with 2 subtasks");
        manager.createEpic(epic);
        epic = new Epic("Second epic", "Epic with 1 subtask");
        manager.createEpic(epic);

        subtask = new Subtask("First subtask", "Subtask1 for first epic", Status.NEW, 2);
        manager.createSubtask(subtask);
        subtask = new Subtask("Second subtask", "Subtask2 for first epic", Status.NEW, 2);
        manager.createSubtask(subtask);

        subtask = new Subtask("First subtask", "Subtask for second epic", Status.NEW, 3);
        manager.createSubtask(subtask);

        System.out.println(manager.getEpics());
        System.out.println();
        System.out.println(manager.getTasks());
        System.out.println();
        System.out.println(manager.getSubtasks());
        System.out.println();

        task = manager.getTaskById(0);
        task.setName("Renamed Task1");
        task.setTaskStatus(Status.IN_PROGRESS);
        manager.updateTask(task, 0);

        task = new Task("Second Task", "Redescription task 2", Status.DONE);
        task.setId(1);
        manager.updateTask(task, 1);

        System.out.println(manager.getTasks());
        System.out.println();

        epic = manager.getEpicById(2);
        epic.setName("Renamed epic1");
        manager.updateEpic(epic, 2);

        epic = new Epic("Second epic", "Redescription");
        epic.setId(3);
        epic.setSubtasksList(manager.getAllSubtasksFromEpic(3));
        manager.updateEpic(epic, 3);

        System.out.println(manager.getEpics());
        System.out.println();

        subtask = manager.getSubtaskById(4);
        subtask.setName("Renamed first subtask of first epic");
        subtask.setSubtaskStatus(Status.IN_PROGRESS);
        manager.updateSubtask(subtask, 4);

        subtask = new Subtask("First subtask", "Redescription", Status.DONE, 3);
        manager.updateSubtask(subtask, 6);

        System.out.println(manager.getSubtasks());
        System.out.println();

        System.out.println(manager.getEpics());
        System.out.println();

        manager.deleteTaskById(0);
        manager.deleteEpicById(3);
        manager.deleteSubtaskById(4);

        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());
    }
}