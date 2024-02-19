package Tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Класс родитель.
 * Содержит все общие поля и 2 конструктора: Для себя и общий для наследников.
 */
public class Task {
    private final String name;
    private final String description;
    private int id;
    private Status taskStatus;
    private Duration duration;
    private LocalDateTime startTime;

    public Task(String name, String description, Status taskStatus) {
        this.name = name;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        taskStatus = Status.NEW;
    }

    public Task(String name, String description, Status taskStatus,
                String startTime, String duration) {
        this.name = name;
        this.description = description;
        this.taskStatus = taskStatus;
        setStartTime(startTime);
        setDuration(duration);
    }

    public void setDuration(String duration) {
        this.duration = Duration.ofMinutes(Integer.parseInt(duration));
    }

    public void setStartTime(String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startTime = LocalDateTime.parse(startTime, formatter);
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getEndTime() {
        return startTime.plus(duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        if (startTime == null) {
            return "\nTask{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", id=" + id +
                    ", taskStatus=" + taskStatus +
                    ", startTime=" + "n/a" +
                    ", duration=" + "n/a" +
                    ", endTime=" + "n/a" +
                    '}';
        } else return "\nTask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", taskStatus=" + taskStatus +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", endTime=" + getEndTime() +
                '}';
    }
}
