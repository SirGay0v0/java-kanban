package Tasks;

/**
 * Наследник класса Task с новыми полями.
 * Id Epic к которому относится Subtask, задается в конструкторе при его создании.
 */
public class Subtask extends Task {
    private final int epicOwnerId;
    private final Status subtaskStatus;

    public Subtask(String name, String description, Status subtaskStatus,
                   int epicOwnerId, String startTime, String duration) {
        super(name, description, subtaskStatus, startTime, duration);
        this.epicOwnerId = epicOwnerId;
        this.subtaskStatus = subtaskStatus;
    }

    public Subtask(String name, String description, Status subtaskStatus, int epicOwnerId) {
        super(name, description);
        this.epicOwnerId = epicOwnerId;
        this.subtaskStatus = subtaskStatus;
    }

    public Status getSubtaskStatus() {
        return subtaskStatus;
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
                    ", subtaskStatus=" + getSubtaskStatus() +
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
                    ", subtaskStatus=" + getSubtaskStatus() +
                    ", epicOwnerId=" + getEpicOwnerId() +
                    ", startTime=" + getStartTime() +
                    ", duration=" + getDuration() +
                    ", endTime=" + getEndTime() +
                    "}";
    }

}
