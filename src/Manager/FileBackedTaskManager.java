package Manager;

import Exceptions.ManagerSaveException;
import ManagerBusinessLogic.EpicStatusVerification;
import ManagerBusinessLogic.EpicTimeVerification;
import Tasks.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

/**
 * Класс реализующий интерфейс TaskManager.
 * Отличается тем, что хранит историю просмотра в файле указанном при создании класса.
 */
public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;

    /**
     * При инициализации запускает метод loadFromFile, который подгружает из файла актуальное
     * состояние InMemoryHistoryManager, а также заполняет пустые hashMap.
     */
    public FileBackedTaskManager(HistoryManager inMemoryHistoryManager, File file) {
        super(inMemoryHistoryManager);
        this.file = file;
        if (file.exists()) {
            loadFromFile(file);
        }
    }

    /**
     * Метод save() сканирует все HashMap и записывает в файл все их объекты,
     * также формирует строку из id элементов InMemoryHistoryManager и выкидывает
     * собственное исключение в случае ошибки в данном блоке инструкций
     */

    private void save() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            bufferedWriter.write("id,type,name,status,description,epic,startTime,duration,endTime\n");

            for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
                bufferedWriter.write(taskToString(taskHashMap.get(entry.getKey())));
            }
            for (Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()) {
                bufferedWriter.write(epicToString(epicHashMap.get(entry.getKey())));
            }
            for (Map.Entry<Integer, Subtask> entry : subtaskHashMap.entrySet()) {
                bufferedWriter.write(subTaskToString(subtaskHashMap.get(entry.getKey())));
            }
            bufferedWriter.write("\n");

            bufferedWriter.write(historyToString(inMemoryHistoryManager));

        } catch (IOException ex) {
            new ManagerSaveException();
        }
    }

    /**
     * Следуюшие три метода возвращают строковое представление каждого из объектов Task/Epic/Subtask
     * которое в последующем будет записано в файл с сохраниением.
     */
    public String taskToString(Task task) {
        if (task.getStartTime() == null) {
            return task.getId() + ",TASK,"
                    + task.getName() + ","
                    + task.getStatus() + ","
                    + task.getDescription()
                    + ",n/a,n/a,n/a,\n";
        } else
            return task.getId() + ",TASK," + task.getName() + ","
                    + task.getStatus() + "," + task.getDescription()
                    + "," + task.getStartTime() + "," + task.getDuration()
                    + "," + task.getEndTime() + ",\n";
    }

    public String epicToString(Epic epic) {
        if (!epic.getIdSubTasks().isEmpty() && epic.getStartTime() != null) {
            return epic.getId() + ",EPIC," + epic.getName() + ","
                    + epic.getStatus() + "," + epic.getDescription()
                    + "," + epic.getStartTime() + "," + epic.getDuration() + ","
                    + epic.getEndTime() + ",\n";
        } else return epic.getId() + ",EPIC," + epic.getName() + ","
                + epic.getStatus() + "," + epic.getDescription()
                + ",n/a,n/a,n/a,\n";
    }

    public String subTaskToString(Subtask subTask) {
        if (subTask.getStartTime() == null) {
            return subTask.getId() + ",SUBTASK," + subTask.getName() + ","
                    + subTask.getStatus() + "," + subTask.getDescription() + ","
                    + subTask.getEpicOwnerId() + "," + ",n/a,n/a,n/a,\n";
        } else
            return subTask.getId() + ",SUBTASK," + subTask.getName() + ","
                    + subTask.getStatus() + "," + subTask.getDescription() + ","
                    + subTask.getEpicOwnerId() + "," + subTask.getStartTime()
                    + "," + subTask.getDuration() + "," + subTask.getEndTime() + ",\n";
    }

    /**
     * Метод historyToString возвращает действующую историю вызовов задач
     */

    public String historyToString(HistoryManager historyManager) {

        Collection<Task> collection = historyManager.getHistory();
        if (collection != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Task task : collection) {
                stringBuilder.append(task.getId() + ",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return String.valueOf(stringBuilder);
        } else return "";
    }


    /**
     * Основной метод, который подгружает историю из файла.
     * Сначала он пробегается по задачам параллельно кладя их в
     * соответсвующие HashMap, после считывает строку с историей и
     * вызывает для каждой из задач метод get, чтобы восстановились все звенья истории в InMemoryTaskManager
     */

    public void loadFromFile(File file) {

        try (BufferedReader buffReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String[] taskParts;
            int maxId = -1;
            while (buffReader.ready()) {
                String stringLine = buffReader.readLine();
                if (!stringLine.isEmpty() && stringLine.endsWith(",")) {
                    taskParts = stringLine.split(",");
                    int id = Integer.parseInt(taskParts[0]);
                    String type = taskParts[1];
                    Task task = fromString(stringLine);
                    task.setId(id);
                    if (id > maxId) {
                        maxId = id;
                    }
                    switch (type) {
                        case "TASK": {
                            taskHashMap.put(id, task);
                            break;
                        }
                        case "EPIC": {
                            epicHashMap.put(id, (Epic) task);
                            break;
                        }
                        case "SUBTASK": {
                            subtaskHashMap.put(id, (Subtask) task);
                            EpicStatusVerification epicStatusVerify = new EpicStatusVerification();
                            EpicTimeVerification epicTimeVerify = new EpicTimeVerification();
                            epicHashMap.get(((Subtask) task).getEpicOwnerId()).getIdSubTasks().add(task.getId());
                            epicStatusVerify.verifyStatus(epicHashMap.get(((Subtask) task).getEpicOwnerId()), subtaskHashMap);
                            epicTimeVerify.verifyTime(epicHashMap.get(((Subtask) task).getEpicOwnerId()), subtaskHashMap);
                            break;
                        }
                    }
                } else if (!stringLine.startsWith("id") && !stringLine.isEmpty()) {
                    List<Integer> historyFromFile = historyFromString(stringLine);
                    for (Integer id : historyFromFile) {
                        if (taskHashMap.containsKey(id)) {
                            getTaskById(id);
                        } else if (epicHashMap.containsKey(id)) {
                            getEpicById(id);
                        } else {
                            getSubtaskById(id);
                        }
                    }
                }
            }
            if (maxId > id || maxId == 0) {
                id = maxId + 1;
            }
        } catch (IOException ex) {
            throw new ManagerSaveException();
        }
        taskHashMap.values().stream().map(task -> priorityTree.add(task)).findFirst();
        subtaskHashMap.values().stream().map(subtask -> priorityTree.add(subtask)).findFirst();
        epicHashMap.values().stream().map(epic -> priorityTree.add(epic)).findFirst();
    }

    /**
     * Метод historyToString возвращает список из истории вызванных задач в порядке, указанном по завершении программы
     */

    List<Integer> historyFromString(String value) {
        String[] idTasksInHistory = value.split(",");
        List<Integer> idTasks = new ArrayList<>();
        for (String id : idTasksInHistory) {
            idTasks.add(Integer.valueOf(id));
        }
        return idTasks;
    }

    /**
     * Метод возвращает один из объектов Task/Epic/Subtask из строки переданной в метод
     */

    public Task fromString(String value) {
        String[] taskParts = value.split(",");
        String type = taskParts[1];
        String name = taskParts[2];
        String description = taskParts[4];
        String startTime;
        String duration;

        switch (type) {
            case "TASK": {
                Status status = Status.valueOf(taskParts[3]);
                if (!taskParts[5].equals("n/a")) {
                    startTime = taskParts[5].replace("T", " ");
                    duration = String.valueOf(Duration.parse(taskParts[6]).toMinutes());
                    return new Task(name, description, status, startTime, duration);
                } else {
                    return new Task(name, description, status);
                }
            }
            case "EPIC": {
                return new Epic(name, description);
            }
            case "SUBTASK": {
                int epicOwnerId = Integer.parseInt(taskParts[5]);
                Status status = Status.valueOf(taskParts[3]);
                startTime = taskParts[6].replace("T", " ");
                duration = String.valueOf(Duration.parse(taskParts[7]).toMinutes());
                return new Subtask(name, description, status, epicOwnerId, startTime, duration);
            }
        }
        return null;
    }

    /**
     * Ниже находятся все переопределенные методы, которые влияют на формирование и добавление задач в историю
     */

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void deleteSubtaskById(int id) {
        super.deleteSubtaskById(id);
        save();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }

    @Override
    public void updateTask(Task task, int id) {
        super.updateTask(task, id);
        save();
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        super.updateEpic(epic, id);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        super.updateSubtask(subtask, id);
        save();
    }

    @Override
    public Collection getTasks() {
        Collection collection = super.getTasks();
        save();
        return collection;
    }

    @Override
    public Collection getEpics() {
        Collection collection = super.getEpics();
        save();
        return collection;
    }

    @Override
    public Collection getSubtasks() {
        Collection collection = super.getSubtasks();
        save();
        return collection;
    }


    @Override
    public void deleteTasks() {
        super.deleteTasks();
        save();
    }

    @Override
    public void deleteEpics() {
        super.deleteEpics();
        save();
    }

    @Override
    public void deleteSubtasks() {
        super.deleteSubtasks();
        save();
    }

    @Override
    public Collection<? extends Task> getPrioritizedTasks() {

        return super.getPrioritizedTasks();
    }
}
