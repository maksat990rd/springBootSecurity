package peaksoft.springbootsecurity.repositoryImpl;


import org.springframework.stereotype.Repository;
import peaksoft.springbootsecurity.model.Lesson;
import peaksoft.springbootsecurity.model.Task;
import peaksoft.springbootsecurity.repositoryImpl.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void saveTask(int lessId, Task task) {
        Lesson lesson = manager.find(Lesson.class, lessId);
        lesson.addTasks(task);
        task.setLesson(lesson);
        manager.persist(task);
    }

    @Override
    public void updateTask(int id, Task task) {
        Task task1 = manager.find(Task.class, id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        manager.merge(task1);
    }

    @Override
    public Task getTaskById(int id) {
        return manager.find(Task.class, id);
    }

    @Override
    public List<Task> getAllTasks(int id) {
        return manager.
                createQuery("select task from Task task where task.lesson.id = : id",
                        Task.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void deleteTaskById(int id) {
        Task task = manager.find(Task.class, id);
        task.setLesson(null);
        manager.remove(task);
    }
}
