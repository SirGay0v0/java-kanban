package Tasks;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Наследник класса Task с новыми полями.
 * Включает в себя List, где хранятся id Subtask принадлежащих этому Epic.
 * Статус NEW задается по умолчанию и меняется с появлением Subtask.
 */
public class Epic extends Task {
   // @SerializedName("status")
    private Status epicStatus;
    private final Collection<Integer> idSubTasks;
    //@SerializedName("epicStartTime")
    private LocalDateTime startTime;
   // @SerializedName("epicDuration")
    private Duration duration;
    private LocalDateTime endTime;

    public Epic(String name, String description) {
        super(name, description);
        this.idSubTasks = new ArrayList<>();
        this.epicStatus = null;
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        if (startTime == null) {
            return "\nEpic{" +
                    "name='" + getName() + '\'' +
                    ", description='" + getDescription() + '\'' +
                    ", id=" + getId() +
                    ", epicStatus=" + getEpicStatus() +
                    ", subtasksList=" + getIdSubTasks() +
                    ", startTime=" + "n/a" +
                    ", duration=" + "n/a" +
                    ", endTime=" + "n/a" +
                    '}';
        } else return "\nEpic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", epicStatus=" + getEpicStatus() +
                ", subtasksList=" + getIdSubTasks() +
                ", startTime=" + this.startTime +
                ", duration=" + this.duration +
                ", endTime=" + this.endTime +
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
