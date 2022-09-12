package peaksoft.springbootsecurity.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.springbootsecurity.model.Course;
import peaksoft.springbootsecurity.repositoryImpl.repository.CourseRepository;
import peaksoft.springbootsecurity.serviceImple.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;

    @Autowired
    public CourseServiceImpl( CourseRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveCourse(int id, Course course) {
        repository.saveCourse(id,course);
    }

    @Override
    public List<Course> getAllCourses(int id) {
        return repository.getAllCourses(id);
    }

    @Override
    public Course getCourseById(int id) {
        return repository.getCourseById(id);
    }

    @Override
    public void updateCourse(Course course, int id) {
        repository.updateCourse(id, course);
    }


    @Override
    public void deleteCourse(int id) {
        repository.deleteCourse(id);
    }
}
