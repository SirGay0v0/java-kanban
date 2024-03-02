package Manager;

import Tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class HttpTaskManager extends FileBackedTaskManager {

    KVTaskClient client;
    private final Gson gson;

    public HttpTaskManager(URI url) throws IOException, InterruptedException {
        super(new File("save.csv"));
        client = new KVTaskClient(url);
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Duration.class, new DurationAdapter())
                .registerTypeAdapter(LocalDateTime.class, new StartTimeAdapter())
                .create();


        Type type = new TypeToken<Map<Integer, Task>>() {
        }.getType();
        if (gson.fromJson(client.load("tasksMap"), type) != null) {
            taskHashMap.putAll(gson.fromJson(client.load("tasksMap"), type));
        }
        if (gson.fromJson(client.load("epicsMap"),type) != null) {
            epicHashMap.putAll(gson.fromJson(client.load("epicsMap"), type));
        }
        if (gson.fromJson(client.load("subtasksMap"),type) != null) {
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
