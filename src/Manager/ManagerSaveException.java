package Manager;

public class ManagerSaveException extends Exception {
    public ManagerSaveException(){
        System.out.println("Произошла ошибка сохранения файла.");
    }
}
