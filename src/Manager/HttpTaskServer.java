package Manager;

import com.google.gson.Gson;

import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpTaskServer extends FileBackedTaskManager {
    private static final int PORT = 8080;
    private final HttpServer httpServer;
    //private final Gson gson;
    private final GetHandler getHandler;

    public HttpTaskServer(HistoryManager inMemoryHistoryManager, File file) throws IOException {
        super(inMemoryHistoryManager, file);
        this.httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        this.getHandler = new GetHandler();
//        this.gson = new Gson();
        httpServer.createContext("/tasks", new GetHandler());
//        httpServer.createContext("/tasks", new PostHandler());
//        httpServer.createContext("/tasks", new DeleteHandler());

        httpServer.start();
    }



}
