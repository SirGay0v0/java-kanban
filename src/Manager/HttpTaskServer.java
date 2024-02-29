package Manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;


public class HttpTaskServer {
    private static final int PORT = 8080;
    private final HttpServer server;
    private final Gson gson;
    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final TaskManager manager;

    public HttpTaskServer() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/tasks", new TasksHandler());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Duration.class, new DurationAdapter())
                .registerTypeAdapter(LocalDateTime.class, new StartTimeAdapter())
                .create();
        server.start();
        this.manager = Managers.getDefaultFileBucked(new File("save.csv"));
        System.out.println("server launched");
    }

    class TasksHandler implements HttpHandler {
        String response;

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Endpoint endpoint = getEndpoint(exchange.getRequestURI().getPath(), exchange.getRequestMethod(), exchange.getRequestURI().getQuery());
            switch (endpoint) {
                case POST_CREATE_TASK: {
                    postTaskCreate(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange, manager);
                    break;
                }
                case POST_CREATE_EPIC: {
                    postEpicCreate(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange, manager);
                    break;
                }
                case POST_CREATE_SUBTASK: {
                    postSubtaskCreate(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange, manager);
                    break;
                }
                case POST_UPDATE_TASK: {
                    postTaskUpdateById(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange, manager);
                    break;
                }
                case POST_UPDATE_EPIC: {
                    postEpicUpdateById(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange, manager);
                    break;
                }
                case POST_UPDATE_SUBTASK: {
                    postSubtaskUpdateById(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange, manager);
                    break;
                }
                case GET_TASK: {
                    getTaskById(exchange, manager);
                    break;
                }
                case GET_EPIC: {
                    getEpicById(exchange, manager);
                    break;
                }
                case GET_SUBTASK: {
                    getSubtaskById(exchange, manager);
                    break;
                }
                case GET_TASKS: {
                    getTasks(exchange, manager);
                    break;
                }
                case GET_EPICS: {
                    getEpics(exchange, manager);
                    break;
                }
                case GET_SUBTASKS: {
                    getSubtasks(exchange, manager);
                    break;
                }
                case GET_SUBTASKS_FROM_EPIC: {
                    getSubtasksFromEpic(exchange, manager);
                    break;
                }
                case GET_PRIORITY: {
                    getPriorityTasks(exchange, manager);
                    break;
                }
                case DELETE_TASK: {
                    deleteTaskById(exchange, manager);
                    break;
                }
                case DELETE_EPIC: {
                    deleteEpicById(exchange, manager);
                    break;
                }
                case DELETE_SUBTASK: {
                    deleteSubtaskById(exchange, manager);
                    break;
                }
                case DELETE_TASKS: {
                    deleteTasks(exchange, manager);
                    break;
                }
                case DELETE_EPICS: {
                    deleteEpics(exchange, manager);
                    break;
                }
                case DELETE_SUBTASKS: {
                    deleteSubtasks(exchange, manager);
                    break;
                }
                case UNKNOWN:
                    try (OutputStream os = exchange.getResponseBody()) {
                        exchange.sendResponseHeaders(400, 0);
                        os.write("Error: Unknown command!".getBytes());
                    }
            }

        }

    }

    private Endpoint getEndpoint(String requestPath, String requestMethod, String query) {
        String path = requestPath.split("/")[2];
        if (requestMethod.equals("POST")) {
            if (query == null) {
                switch (path) {
                    case "task":
                        return Endpoint.POST_CREATE_TASK;
                    case "epic":
                        return Endpoint.POST_CREATE_EPIC;
                    case "subtask":
                        return Endpoint.POST_CREATE_SUBTASK;
                }
            } else {
                switch (path) {
                    case "task":
                        return Endpoint.POST_UPDATE_TASK;
                    case "epic":
                        return Endpoint.POST_UPDATE_EPIC;
                    case "subtask":
                        return Endpoint.POST_UPDATE_SUBTASK;
                }
            }
        } else if (requestMethod.equals("GET")) {
            switch (path) {
                case "tasks":
                    return Endpoint.GET_TASKS;
                case "epics":
                    return Endpoint.GET_EPICS;
                case "subtasks":
                    return Endpoint.GET_SUBTASKS;
                case "priority":
                    return Endpoint.GET_PRIORITY;
                case "task":
                    return Endpoint.GET_TASK;
                case "epic":
                    return Endpoint.GET_EPIC;
                case "subtask": {
                    if (requestPath.split("/")[3].equals("epic")) {
                        return Endpoint.GET_SUBTASKS_FROM_EPIC;
                    } else {
                        return Endpoint.GET_SUBTASK;
                    }
                }
            }
        } else if (requestMethod.equals("DELETE")) {
            switch (path) {
                case "tasks":
                    return Endpoint.DELETE_TASKS;
                case "epics":
                    return Endpoint.DELETE_EPICS;
                case "subtasks":
                    return Endpoint.DELETE_SUBTASKS;
                case "task":
                    return Endpoint.DELETE_TASK;
                case "epic":
                    return Endpoint.DELETE_EPIC;
                case "subtask":
                    return Endpoint.DELETE_SUBTASK;
            }
        }
        return Endpoint.UNKNOWN;
    }

    private void postTaskCreate(String jsonBody, HttpExchange exchange, TaskManager manager) throws IOException {
//        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
//        String name = jsonObject.get("name").getAsString();
//        String description = jsonObject.get("description").getAsString();
//        String status = jsonObject.get("status").getAsString();
//        String startTime = jsonObject.get("startTime").getAsString();
//        String duration = jsonObject.get("duration").getAsString();
//        manager.createTask(new Task(name, description, Status.valueOf(status), startTime, duration));
        Task task = gson.fromJson(jsonBody, Task.class);
        manager.createTask(task);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write("Task was created!".getBytes());
        }
    }

    private void postEpicCreate(String jsonBody, HttpExchange exchange, TaskManager manager) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();

        Epic epic = new Epic(name, description);
        manager.createEpic(epic);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write("Epic was created!".getBytes());
        }
    }

    private void postSubtaskCreate(String jsonBody, HttpExchange exchange, TaskManager manager) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        int epicOwnerId = jsonObject.get("epicOwnerId").getAsInt();
        String status = jsonObject.get("status").getAsString();
        String startTime = jsonObject.get("startTime").getAsString();
        String duration = jsonObject.get("duration").getAsString();
        manager.createSubtask(new Subtask(name, description, Status.valueOf(status), epicOwnerId, startTime, duration));
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write("Subtask was created!".getBytes());
        }
    }

    private void postTaskUpdateById(String jsonBody, HttpExchange exchange, TaskManager manager) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        String status = jsonObject.get("taskStatus").getAsString();
        String startTime = jsonObject.get("startTime").getAsString();
        String duration = jsonObject.get("duration").getAsString();
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.updateTask(new Task(name, description, Status.valueOf(status), startTime, duration), id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(("Task " + id + " was updated!").getBytes());
        }
    }

    private void postEpicUpdateById(String jsonBody, HttpExchange exchange, TaskManager manager) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.updateEpic(new Epic(name, description), id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(("Task " + id + " was updated!").getBytes());
        }
    }

    private void postSubtaskUpdateById(String jsonBody, HttpExchange exchange, TaskManager manager) throws
            IOException {
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        String status = jsonObject.get("subtaskStatus").getAsString();
        String startTime = jsonObject.get("startTime").getAsString();
        String duration = jsonObject.get("duration").getAsString();
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.updateTask(new Subtask(name, description, Status.valueOf(status), id, startTime, duration), id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(("Task " + id + " was updated!").getBytes());
        }
    }

    private void getTaskById(HttpExchange exchange, TaskManager manager) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        Task task = manager.getTaskById(id);

        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(task).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getEpicById(HttpExchange exchange, TaskManager manager) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        Epic epic = manager.getEpicById(id);
        String epicString = "{\n" +
                "\"name\": \"" + epic.getName() + "\",\n" +
                "\"description\": \"" + epic.getDescription() + "\",\n" +
                "\"id\": " + epic.getId() + ",\n" +
                "\"idSubtasks\": \"" + epic.getIdSubTasks() + "\",\n" +
                "\"status\": \"" + epic.getStatus() + "\",\n" +
                "\"startTime\": \"" + epic.getStartTime() + "\",\n" +
                "\"duration\": \"" + epic.getDuration().toMinutes() + "\"\n" +
                "}";
        JsonElement jsonElement = JsonParser.parseString(epicString);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(jsonElement).getBytes());
        }
    }

    private void getSubtaskById(HttpExchange exchange, TaskManager manager) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        Subtask subtask = manager.getSubtaskById(id);

        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(subtask).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getTasks(HttpExchange exchange, TaskManager manager) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getTasks()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getEpics(HttpExchange exchange, TaskManager manager) throws IOException {
        Collection<JsonElement> jeList = new ArrayList<>();
        for (Epic epic : (Collection<Epic>) manager.getEpics()) {
            String epicString = "{\n" +
                    "\"name\": \"" + epic.getName() + "\",\n" +
                    "\"description\": \"" + epic.getDescription() + "\",\n" +
                    "\"id\": " + epic.getId() + ",\n" +
                    "\"idSubtasks\": \"" + epic.getIdSubTasks() + "\",\n" +
                    "\"status\": \"" + epic.getStatus() + "\",\n" +
                    "\"startTime\": \"" + epic.getStartTime() + "\",\n" +
                    "\"duration\": \"" + epic.getDuration().toMinutes() + "\"\n" +
                    "}";
            JsonElement jsonElement = JsonParser.parseString(epicString);
            jeList.add(jsonElement);
        }

        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(jeList).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getSubtasks(HttpExchange exchange, TaskManager manager) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getSubtasks()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getSubtasksFromEpic(HttpExchange exchange, TaskManager taskManager) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        Collection<Integer> subtasksListId = manager.getAllSubtasksFromEpic(id);
        Collection<Subtask> subtasksList = new ArrayList<>();
        for (Integer idSubtask : subtasksListId) {
            subtasksList.add(manager.getSubtaskById(idSubtask));
        }
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(subtasksList).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getPriorityTasks(HttpExchange exchange, TaskManager manager) throws IOException {

        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getPrioritizedTasks()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteTasks(HttpExchange exchange, TaskManager manager) throws IOException {
        manager.deleteTasks();
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("All Tasks was deleted").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteEpics(HttpExchange exchange, TaskManager manager) throws IOException {
        manager.deleteEpics();
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("All Epics was deleted with Subtasks").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteSubtasks(HttpExchange exchange, TaskManager manager) throws IOException {
        manager.deleteSubtasks();
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("All Subtasks was deleted").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteTaskById(HttpExchange exchange, TaskManager manager) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.deleteTaskById(id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("Task with id " + id + " was deleted").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteEpicById(HttpExchange exchange, TaskManager manager) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.deleteEpicById(id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("Epic with id " + id + " was deleted with Subtasks").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteSubtaskById(HttpExchange exchange, TaskManager manager) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.deleteSubtaskById(id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("Subtask with id " + id + " was deleted").getBytes(StandardCharsets.UTF_8));
        }
    }

    enum Endpoint {
        POST_CREATE_TASK,
        POST_CREATE_EPIC,
        POST_CREATE_SUBTASK,
        POST_UPDATE_TASK,
        POST_UPDATE_EPIC,
        POST_UPDATE_SUBTASK,
        GET_TASK,
        GET_TASKS,
        GET_EPIC,
        GET_EPICS,
        GET_SUBTASK,
        GET_SUBTASKS,
        GET_SUBTASKS_FROM_EPIC,
        GET_PRIORITY,
        DELETE_TASK,
        DELETE_EPIC,
        DELETE_SUBTASK,
        DELETE_TASKS,
        DELETE_EPICS,
        DELETE_SUBTASKS,
        UNKNOWN
    }

    static class StartTimeAdapter extends TypeAdapter<LocalDateTime> {

        @Override
        public void write(JsonWriter jsonWriter, LocalDateTime localDate) throws IOException {
            final DateTimeFormatter formatterWriter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            jsonWriter.value(localDate.format(formatterWriter));
        }

        @Override
        public LocalDateTime read(JsonReader jsonReader) throws IOException {
            return null;
        }
    }

    static class DurationAdapter extends TypeAdapter<Duration> {

        @Override
        public void write(JsonWriter jsonWriter, Duration duration) throws IOException {
            jsonWriter.value(String.valueOf(duration.toMinutes()));
        }

        @Override
        public Duration read(JsonReader jsonReader) throws IOException {
            return null;
        }
    }
}
