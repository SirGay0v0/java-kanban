package Manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


public class HttpTaskServer {
    private static final int PORT = 8080;
    private final HttpServer server;
    private final Gson gson;
    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private TaskManager manager = Managers.getDefaultFileBucked(new File("save.scv"));

    public HttpTaskServer() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/tasks", new TasksHandler());
        this.gson = new Gson();
        server.start();
        System.out.println("server launched");
    }

    class TasksHandler implements HttpHandler {
        String response;

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Endpoint endpoint = getEndpoint(exchange.getRequestURI().getPath(), exchange.getRequestMethod());
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

                case UNKNOWN:
                    try (OutputStream os = exchange.getResponseBody()) {
                        exchange.sendResponseHeaders(400, 0);
                        os.write("Error: Unknown command!".getBytes());
                    }
            }

        }

    }

    private Endpoint getEndpoint(String requestPath, String requestMethod) {
        String[] parts = requestPath.split("/");
        if (requestMethod.equals("POST") && parts[parts.length - 1].equals("task")) {
            return Endpoint.POST_CREATE_TASK;
        } else if (requestMethod.equals("POST") && parts[parts.length - 1].equals("epic")) {
            return Endpoint.POST_CREATE_EPIC;
        } else if (requestMethod.equals("POST") && parts[parts.length - 1].equals("subtask")) {
            return Endpoint.POST_CREATE_SUBTASK;
        }
        return Endpoint.UNKNOWN;
    }

    private void postTaskCreate(String jsonBody, HttpExchange exchange, TaskManager manager) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        String status = jsonObject.get("taskStatus").getAsString();
        String startTime = jsonObject.get("startTime").getAsString();
        String duration = jsonObject.get("duration").getAsString();
        manager.createTask(new Task(name, description, Status.valueOf(status), startTime, duration));
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
        String status = jsonObject.get("subtaskStatus").getAsString();
        String startTime = jsonObject.get("startTime").getAsString();
        String duration = jsonObject.get("duration").getAsString();
        manager.createSubtask(new Subtask(name, description, Status.valueOf(status), epicOwnerId, startTime, duration));
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, 0);
            os.write("Subtask was created!".getBytes());
        }
    }

    enum Endpoint {
        POST_CREATE_TASK,
        POST_CREATE_EPIC,
        POST_CREATE_SUBTASK,
        POST_UPDATE_TASK,
        POST_UPDATE_EPIC,
        POST_UPDATE_SUBTASK,
        UNKNOWN
    }
}
