package Tasks;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Наследник класса Task с новыми полями.
 * Включает в себя List, где хранятся id Subtask принадлежащих этому Epic.
 * Статус NEW задается по умолчанию и меняется с появлением Subtask.
 */
public class Epic extends Task {
    private Status epicStatus;
    private Collection<Integer> idSubTasks;

    public Epic(String name, String description) {
        super(name, description);
        idSubTasks = new ArrayList<>();
        setEpicStatus(Status.NEW);
    }

    public Status getEpicStatus() {
        return epicStatus;
    }

    public void setEpicStatus(Status epicStatus) {
        this.epicStatus = epicStatus;
    }

    public Collection<Integer> getIdSubTasks() {
        return idSubTasks;
    }

    public void setIdSubTasks(Collection<Integer> idSubTasks) {
        this.idSubTasks = idSubTasks;
    }

    @Override
    public String toString() {
        return "\nEpic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", epicStatus=" + getEpicStatus() +
                ", subtasksList=" + getIdSubTasks() +
                '}';
    }
}
