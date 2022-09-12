package peaksoft.springbootsecurity.repositoryImpl;


import org.springframework.stereotype.Repository;
import peaksoft.springbootsecurity.model.Company;
import peaksoft.springbootsecurity.model.Course;
import peaksoft.springbootsecurity.model.Instructor;
import peaksoft.springbootsecurity.repositoryImpl.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void saveCourse(int id, Course course) {
        Company company = manager.find(Company.class, id);
        company.addCourse(course);
        course.setCompany(company);
        manager.merge(course);
    }

    @Override
    public List<Course> getAllCourses(int id) {
        List<Course> courses = manager
                .createQuery("select c from Course c where c.company.id = :id",
                        Course.class).setParameter("id",id)
                .getResultList();
        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        return manager.find(Course.class, id);
    }

    @Override
    public void updateCourse(int id,Course course) {
        Course course1 = manager.find(Course.class,id);
        course1.setCourseName(course.getCourseName());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setDuration(course.getDuration());
        course1.setImage(course.getImage());
        course1.setDescription(course.getDescription());
        manager.merge(course1);
    }

    @Override
    public void deleteCourse(int id) {
        Course course = manager.find(Course.class, id);
        for (Instructor instructor : course.getInstructors()) {
            instructor.setCourses(null);
        }
        course.setCompany(null);
        manager.remove(manager.find(Course.class, id));

    }
}
