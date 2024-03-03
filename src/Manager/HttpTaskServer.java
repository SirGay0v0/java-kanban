package Manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class HttpTaskServer {
    private static final int PORT = 8080;
    private final com.google.gson.Gson gson= new Gson().GsonTaskBuilder();
    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final  TaskManager manager= Managers.getDefaultFileBucked(new File("save.csv"));

    public HttpTaskServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/tasks", new TasksHandler());
        server.start();
        System.out.println("server launched");
    }

    class TasksHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Endpoint endpoint = getEndpoint(exchange.getRequestURI().getPath(), exchange.getRequestMethod(), exchange.getRequestURI().getQuery());
            switch (endpoint) {
                case POST_CREATE_TASK: {
                    postTaskCreate(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange);
                    break;
                }
                case POST_CREATE_EPIC: {
                    postEpicCreate(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange);
                    break;
                }
                case POST_CREATE_SUBTASK: {
                    postSubtaskCreate(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange);
                    break;
                }
                case POST_UPDATE_TASK: {
                    postTaskUpdateById(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange);
                    break;
                }
                case POST_UPDATE_EPIC: {
                    postEpicUpdateById(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange);
                    break;
                }
                case POST_UPDATE_SUBTASK: {
                    postSubtaskUpdateById(new String(exchange.getRequestBody().readAllBytes(), DEFAULT_CHARSET), exchange);
                    break;
                }
                case GET_TASK: {
                    getTaskById(exchange);
                    break;
                }
                case GET_EPIC: {
                    getEpicById(exchange);
                    break;
                }
                case GET_SUBTASK: {
                    getSubtaskById(exchange);
                    break;
                }
                case GET_TASKS: {
                    getTasks(exchange);
                    break;
                }
                case GET_EPICS: {
                    getEpics(exchange);
                    break;
                }
                case GET_SUBTASKS: {
                    getSubtasks(exchange);
                    break;
                }
                case GET_SUBTASKS_FROM_EPIC: {
                    getSubtasksFromEpic(exchange);
                    break;
                }
                case GET_PRIORITY: {
                    getPriorityTasks(exchange);
                    break;
                }
                case DELETE_TASK: {
                    deleteTaskById(exchange);
                    break;
                }
                case DELETE_EPIC: {
                    deleteEpicById(exchange);
                    break;
                }
                case DELETE_SUBTASK: {
                    deleteSubtaskById(exchange);
                    break;
                }
                case DELETE_TASKS: {
                    deleteTasks(exchange);
                    break;
                }
                case DELETE_EPICS: {
                    deleteEpics(exchange);
                    break;
                }
                case DELETE_SUBTASKS: {
                    deleteSubtasks(exchange);
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
        switch (requestMethod) {
            case "POST":
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
                break;
            case "GET":
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
                        if (requestPath.split("/").length == 3) {
                            return Endpoint.GET_SUBTASK;
                        } else {
                            return Endpoint.GET_SUBTASKS_FROM_EPIC;
                        }
                    }
                }
                break;
            case "DELETE":
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
                break;
        }
        return Endpoint.UNKNOWN;
    }

    private void postTaskCreate(String jsonBody, HttpExchange exchange) throws IOException {
        manager.createTask(gson.fromJson(jsonBody, Task.class));
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write("Task was created!".getBytes());
        }
    }

    private void postEpicCreate(String jsonBody, HttpExchange exchange) throws IOException {
        manager.createEpic(gson.fromJson(jsonBody, Epic.class));
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write("Epic was created!".getBytes());
        }
    }

    private void postSubtaskCreate(String jsonBody, HttpExchange exchange) throws IOException {
        manager.createSubtask(gson.fromJson(jsonBody, Subtask.class));
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write("Subtask was created!".getBytes());
        }
    }

    private void postTaskUpdateById(String jsonBody, HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.updateTask(gson.fromJson(jsonBody, Task.class), id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(("Task " + id + " was updated!").getBytes());
        }
    }

    private void postEpicUpdateById(String jsonBody, HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.updateEpic(gson.fromJson(jsonBody, Epic.class), id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(("Task " + id + " was updated!").getBytes());
        }
    }

    private void postSubtaskUpdateById(String jsonBody, HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.updateTask(gson.fromJson(jsonBody, Subtask.class), id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(("Task " + id + " was updated!").getBytes());
        }
    }

    private void getTaskById(HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        Task task = manager.getTaskById(id);

        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(task).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getEpicById(HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getEpicById(id)).getBytes());
        }
    }

    private void getSubtaskById(HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getSubtaskById(id)).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getTasks(HttpExchange exchange) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getTasks()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getEpics(HttpExchange exchange) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getEpics()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getSubtasks(HttpExchange exchange) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getSubtasks()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getSubtasksFromEpic(HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getAllSubtasksFromEpic(id)).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void getPriorityTasks(HttpExchange exchange) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson(manager.getPrioritizedTasks()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteTasks(HttpExchange exchange) throws IOException {
        manager.deleteTasks();
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("All Tasks was deleted").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteEpics(HttpExchange exchange) throws IOException {
        manager.deleteEpics();
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("All Epics was deleted with Subtasks").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteSubtasks(HttpExchange exchange) throws IOException {
        manager.deleteSubtasks();
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("All Subtasks was deleted").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteTaskById(HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.deleteTaskById(id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("Task with id " + id + " was deleted").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteEpicById(HttpExchange exchange) throws IOException {
        int id = Integer.parseInt(exchange.getRequestURI().getQuery().split("=")[1]);
        manager.deleteEpicById(id);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write(gson.toJson("Epic with id " + id + " was deleted with Subtasks").getBytes(StandardCharsets.UTF_8));
        }
    }

    private void deleteSubtaskById(HttpExchange exchange) throws IOException {
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
}
