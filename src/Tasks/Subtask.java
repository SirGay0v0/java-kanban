package Tasks;

/**
 * Наследник класса Task с новыми полями.
 * Id Epic к которому относится Subtask, задается в конструкторе при его создании.
 */
public class Subtask extends Task {
    private final int epicOwnerId;

    public Subtask(String name, String description, Status status,
                   int epicOwnerId, String startTime, String duration) {
        super(name, description, status, startTime, duration);
        this.epicOwnerId = epicOwnerId;
    }

    public Subtask(String name, String description, Status status, int epicOwnerId) {
        super(name, description, status);
        this.epicOwnerId = epicOwnerId;
    }

    public int getEpicOwnerId() {
        return epicOwnerId;
    }

    @Override
    public String toString() {
        if (getStartTime() == null) {
            return "\nSubtask{" +
                    "name='" + getName() + '\'' +
                    ", description='" + getDescription() + '\'' +
                    ", id=" + getId() +
                    ", subtaskStatus=" + getStatus() +
                    ", epicOwnerId=" + getEpicOwnerId() +
                    ", startTime=" + "n/a" +
                    ", duration=" + "n/a" +
                    ", endTime=" + "n/a" +
                    '}';
        } else
            return "\nSubtask{" +
                    "name='" + getName() + '\'' +
                    ", description='" + getDescription() + '\'' +
                    ", id=" + getId() +
                    ", subtaskStatus=" + getStatus() +
                    ", epicOwnerId=" + getEpicOwnerId() +
                    ", startTime=" + getStartTime() +
                    ", duration=" + getDuration() +
                    ", endTime=" + getEndTime() +
                    "}";
    }

}
