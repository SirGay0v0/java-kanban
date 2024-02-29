import Manager.*;
import Tasks.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;


/**
 * Все условия проверки, которые были указаны в ТЗ прошли без ошибок.
 */
public class Main {
    public static void main(String[] args) throws IOException {



//        TaskManager manager= Managers.getDefault();
//        Epic epic = new Epic("epicName", "epicDescription");
//        Subtask subtask = new Subtask("name", "desc",
//                Status.NEW, 1, "2000-01-01 00:00", "1440");
//        Task task = new Task("0", "0", Status.NEW, "2000-01-02 00:00", "1440");
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();
//        Gson gson = gsonBuilder.create();
////        System.out.println(gson.toJson(task));
////
//manager.createTask(task);
//manager.createEpic(epic);
//manager.createSubtask(subtask);
//
//        System.out.println(gson.toJson(manager.getPrioritizedTasks()));
////

//        System.out.println(gson.toJson(epic));
//        System.out.println(gson.toJson(subtask));

//            Task task = new Task("0", "0", Status.NEW, "2000-01-02 00:00", "1440");
//            System.out.println(gson.toJson(task));


//        File file = new File("save.csv");
//        TaskManager manager = Managers.getDefault();
//
//
//        Task task;
//        Epic epic;
//        Subtask subtask;
//
//        task = new Task("0", "0", Status.NEW, "2000-01-02 00:00", "1440");
//        manager.createTask(task);
//
//        task = new Task("1", "1", Status.NEW, "2000-01-02 12:00", "1440");
//        manager.createTask(task);
//
//        task = new Task("2", "2", Status.NEW, "2000-01-01 12:00", "1440");
//        manager.createTask(task);
//
//        task = new Task("3", "3", Status.NEW, "2000-01-02 00:00", "2400");
//        manager.createTask(task);
//
//        task = new Task("4", "4", Status.NEW, "2000-01-03 00:00", "1440");
//        manager.createTask(task);
//
//        System.out.println((manager.getPrioritizedTasks()));
//        System.out.println(manager.getTasks());

//        task = new Task("1", "1");
//        taskManager.createTask(task);
//        task = new Task("3", "3", Status.IN_PROGRESS, "2024-03-20 14:00", "40");
//        taskManager.createTask(task);
//        task = new Task("3", "3", Status.IN_PROGRESS, "2025-03-20 14:00", "40");
//        taskManager.createTask(task);
//        epic = new Epic("name3", "description");
//        taskManager.createEpic(epic);
//        subtask = new Subtask("name3", "description2", Status.NEW, 3, "2014-04-08 12:30", "180");
//        taskManager.createSubtask(subtask);
//        subtask = new Subtask("name4", "description2", Status.NEW, 3, "2013-04-08 12:30", "180");
//        taskManager.createSubtask(subtask);
//        epic = new Epic("name5", "description");
//        taskManager.createEpic(epic);
//        System.out.println(taskManager.getPrioritizedTasks());
//        System.out.println(taskManager.getTasks());
//        System.out.println(taskManager.getEpics());
//        System.out.println(taskManager.getSubtasks());


//        epic = new Epic("name3", "description");
//        taskManager.createEpic(epic);
//        subtask = new Subtask("name3", "description2", Status.NEW, 3, "2014-04-08 12:30", "180");
//        taskManager.createSubtask(subtask);
//        subtask = new Subtask("name3", "description3", Status.NEW, 3, "2014-04-08 18:00", "60");
//        taskManager.createSubtask(subtask);
//        System.out.println(taskManager.getEpics());
//        System.out.println(taskManager.getSubtasks());

        //System.out.println(taskManager.getTaskById(0));

//        epic = new Epic("First epic", "Epic with 3 subtasks");
//        fileBuckedTaskManager.createEpic(epic);
//
//        subtask = new Subtask("First subtask", "Subtask1 for first epic", Status.NEW, 0);
//        fileBuckedTaskManager.createSubtask(subtask);
//        subtask = new Subtask("Second subtask", "Subtask2 for first epic", Status.IN_PROGRESS, 0);
//        fileBuckedTaskManager.createSubtask(subtask);
//        subtask = new Subtask("Third subtask", "Subtask3 for first epic", Status.NEW, 0);
//        fileBuckedTaskManager.createSubtask(subtask);
//
//        epic = new Epic("Second epic", "Epic without subtasks");
//        fileBuckedTaskManager.createEpic(epic);
//
//        fileBuckedTaskManager.getEpicById(0);
//        fileBuckedTaskManager.getSubtaskById(2);
//        fileBuckedTaskManager.getSubtaskById(1);
//        fileBuckedTaskManager.getSubtaskById(3);
//        fileBuckedTaskManager.getEpicById(4);
//        fileBuckedTaskManager.getSubtaskById(2);
//        fileBuckedTaskManager.getEpicById(0);
//        System.out.println(fileBuckedTaskManager.getHistory());
//        System.out.println();
//        // Проверка на корректность истории прошла успешно
//
//        fileBuckedTaskManager.deleteEpicById(4);
//        fileBuckedTaskManager.deleteSubtaskById(3);
//        System.out.println(fileBuckedTaskManager.getHistory());
        //Проверка на удаление Epic и зависимых от него SubTask прошла успешно
        //Проверка на удаление одного SubTask из нескольких прошла успешно


        /*task = new Task("First task", "To do smth", Status.NEW);
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
        epic.setIdSubTasks(inMemoryTaskManager.getAllSubtasksFromEpic(3));
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

        System.out.println();
        System.out.println();

        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getEpicById(2);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getEpicById(2);

        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteTaskById(1);

        System.out.println();
        System.out.println();

        System.out.println(inMemoryTaskManager.getHistory());*/


    }
}