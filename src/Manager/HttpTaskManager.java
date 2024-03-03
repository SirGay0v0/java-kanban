package Manager;

import Tasks.Task;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.*;

public class HttpTaskManager extends FileBackedTaskManager {

    private final KVTaskClient client;
    private final com.google.gson.Gson gson= new Gson().GsonTaskBuilder();;

    public HttpTaskManager(URI url) throws IOException, InterruptedException {
        super(new File("save.csv"));
        client = new KVTaskClient(url);

        Type type = new TypeToken<Map<Integer, Task>>() {
        }.getType();

        if (gson.fromJson(client.load("tasksMap"), type) != null) {
            taskHashMap.putAll(gson.fromJson(client.load("tasksMap"), type));
        }
        if (gson.fromJson(client.load("epicsMap"), type) != null) {
            epicHashMap.putAll(gson.fromJson(client.load("epicsMap"), type));
        }
        if (gson.fromJson(client.load("subtasksMap"), type) != null) {
            subtaskHashMap.putAll(gson.fromJson(client.load("subtasksMap"), type));
        }

        priorityTree.addAll(taskHashMap.values());
        priorityTree.addAll(subtaskHashMap.values());
    }

    @Override
    public void save() {
        try {
            client.put("tasksMap", gson.toJson(taskHashMap));
            client.put("epicsMap", gson.toJson(epicHashMap));
            client.put("subtasksMap", gson.toJson(subtaskHashMap));
            client.put("priorityTasks", gson.toJson(priorityTree));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
