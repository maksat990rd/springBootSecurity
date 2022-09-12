package peaksoft.springbootsecurity.repositoryImpl.repository;


import peaksoft.springbootsecurity.model.Student;

import java.util.List;

public interface StudentRepository {
    void saveStudent(int id, Student student);

    void updateStudent(int id,Student student);

    List<Student> getAllStudents(int id);

    Student getStudentById(int id);

    void deleteStudentById(int id);

    void assignStudentToCourse(int studentId,int courseId);

}
