package peaksoft.springbootsecurity.repositoryImpl.repository;


import peaksoft.springbootsecurity.model.Task;

import java.util.List;

public interface TaskRepository {

    void saveTask(int lessId, Task task);

    void updateTask(int id,Task task);

    Task getTaskById(int id);

    List<Task> getAllTasks(int id);

    void deleteTaskById(int id);
}
