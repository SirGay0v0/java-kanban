package Manager;


public class Managers {

    static final HistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
    static final TaskManager inMemoryTaskManager = new InMemoryTaskManager();

    public static TaskManager getDefault() {
        return inMemoryTaskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return inMemoryHistoryManager;
    }

}
