package model;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();

    Task getTaskById(int id);

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(int id);

    void deleteAllTasks();
}
