package Manager;

public class ManagerSaveException extends RuntimeException {
    public ManagerSaveException(){
        System.out.println("Произошла ошибка сохранения файла.");
        System.exit(0);
    }
}
