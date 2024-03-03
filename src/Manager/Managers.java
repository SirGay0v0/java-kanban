package Manager;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Класс создает экземпляры менеджеров и занимается передачей ссылок на эти экземпляры.
 */
public class Managers {

    public static HttpTaskManager getDefault() throws IOException, InterruptedException {
        return new HttpTaskManager(URI.create("http://localhost:8078"));
    }

    public static TaskManager getDefaultFileBucked(File file) {
        return new FileBackedTaskManager(file);
    }
}
