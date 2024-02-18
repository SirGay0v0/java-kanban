package Exceptions;

public class ManagerLoadException extends RuntimeException {
    public void ManagerSaveException(){
        System.out.println("Произошла ошибка загрузки из файла.");
    }
}
