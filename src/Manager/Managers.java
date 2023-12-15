package Manager;

/**
 * Класс создает экземпляры менеджеров и занимается передачей ссылок на эти экземпляры.
 */
public class Managers {

    private static final HistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }

    public static HistoryManager getDefaultHistory() {
        return inMemoryHistoryManager;
    }

}
