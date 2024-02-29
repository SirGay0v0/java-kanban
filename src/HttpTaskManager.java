import Manager.FileBackedTaskManager;
import Manager.HistoryManager;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class HttpTaskManager extends FileBackedTaskManager {

    public HttpTaskManager(HistoryManager inMemoryHistoryManager, File file, URI url) throws IOException, InterruptedException {
        super(inMemoryHistoryManager,file);
        KVTaskClient client = new KVTaskClient(url);
    }

}
