package Manager;

/**
 * Класс создает экземпляры менеджеров и занимается передачей ссылок на эти экземпляры.
 */
public class Managers {

    private static final HistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
    private static final TaskManager inMemoryTaskManager = new InMemoryTaskManager();

    public static TaskManager getDefault() {
        return inMemoryTaskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return inMemoryHistoryManager;
    }

}
