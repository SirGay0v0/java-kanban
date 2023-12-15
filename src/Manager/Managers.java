package Manager;

/**
 * Класс создает экземпляры менеджеров и занимается передачей ссылок на эти экземпляры.
 */
public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }

    public static HistoryManager getDefaultHistory() {
        HistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        return inMemoryHistoryManager;
    }

}
