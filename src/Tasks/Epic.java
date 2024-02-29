package Tasks;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Наследник класса Task с новыми полями.
 * Включает в себя List, где хранятся id Subtask принадлежащих этому Epic.
 * Статус NEW задается по умолчанию и меняется с появлением Subtask.
 */
public class Epic extends Task {
    private final Collection<Integer> idSubTasks;

    public Epic(String name, String description) {
        super(name, description);
        this.idSubTasks = new ArrayList<>();
        super.setStatus(Status.NEW);
    }

    public Collection<Integer> getIdSubTasks() {
        return idSubTasks;
    }

    @Override
    public String toString() {
        if (getStartTime() == null) {
            return "\nEpic{" +
                    "name='" + getName() + '\'' +
                    ", description='" + getDescription() + '\'' +
                    ", id=" + getId() +
                    ", epicStatus=" + getStatus() +
                    ", subtasksList=" + getIdSubTasks() +
                    ", startTime=" + "n/a" +
                    ", duration=" + "n/a" +
                    ", endTime=" + "n/a" +
                    '}';
        } else return "\nEpic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", epicStatus=" + getStatus() +
                ", subtasksList=" + getIdSubTasks() +
                ", startTime=" + getStartTime() +
                ", duration=" + getDuration() +
                ", endTime=" + getDuration() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
