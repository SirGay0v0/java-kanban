package Tasks;

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

    public Task(String name, String description, Status taskStatus) {
        this.name = name;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\nTask{" + "name='" + getName() + '\'' + ", description='" + getDescription() + '\'' + ", id=" + getId() + ", taskStatus=" + getTaskStatus() + '}';
    }
}
