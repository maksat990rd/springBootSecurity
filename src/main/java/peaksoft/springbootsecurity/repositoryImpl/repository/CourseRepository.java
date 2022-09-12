package peaksoft.springbootsecurity.repositoryImpl.repository;


import peaksoft.springbootsecurity.model.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourse(int id, Course course);

    List<Course> getAllCourses(int id);

    Course getCourseById(int id);

    void updateCourse(int id,Course course);

    void deleteCourse(int id);
}
