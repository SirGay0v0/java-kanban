package Tasks;

import java.util.ArrayList;

/**
 * Наследник класса Task с новыми полями.
 * Включает в себя List, где хранятся id Subtask принадлежащих этому Epic.
 * Статус NEW задается по умолчанию и меняется с появлением Subtask/
 */
public class Epic extends Task {
    private Status epicStatus;
    public ArrayList<Integer> subtasksList;

    public Epic(String name, String description) {
        super(name, description);
        setEpicStatus(Status.NEW);
        subtasksList = new ArrayList<>();
    }

    public Status getEpicStatus() {
        return epicStatus;
    }

    public void setEpicStatus(Status epicStatus) {
        this.epicStatus = epicStatus;
    }

    public ArrayList<Integer> getSubtasksList() {
        return subtasksList;
    }

    public void setSubtasksList(ArrayList<Integer> subtasksList) {
        this.subtasksList = subtasksList;
    }

    @Override
    public String toString() {
        return "\nEpic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", epicStatus=" + getEpicStatus() +
                ", subtasksList=" + getSubtasksList() +
                '}';
    }
}
