package peaksoft.springbootsecurity.repositoryImpl;


import org.springframework.stereotype.Repository;
import peaksoft.springbootsecurity.model.Course;
import peaksoft.springbootsecurity.model.Lesson;
import peaksoft.springbootsecurity.repositoryImpl.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void saveLesson(int courseId, Lesson lesson) {
        Course course = manager.find(Course.class,courseId);
        course.addLessons(lesson);
        lesson.setCourse(course);
        manager.persist(course);
    }

    @Override
    public void updateLesson(int id, Lesson lesson) {
        Lesson lesson1 = manager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        lesson1.setVideo(lesson.getVideo());
        manager.merge(lesson1);
    }

    @Override
    public List<Lesson> getAllLessons(int id) {
        return manager
                .createQuery("select l from Lesson l where l.course.id = : id",
                        Lesson.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public Lesson getLessonById(int id) {
        return manager.find(Lesson.class,id);
    }

    @Override
    public void deleteLessonById(int id) {
        Lesson lesson = manager.find(Lesson.class,id);
        lesson.setCourse(null);
        manager.remove(lesson);
    }
}
