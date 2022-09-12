package peaksoft.springbootsecurity.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.springbootsecurity.model.Company;
import peaksoft.springbootsecurity.model.Course;
import peaksoft.springbootsecurity.model.Instructor;
import peaksoft.springbootsecurity.repositoryImpl.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {
    @PersistenceContext
    private EntityManager manager;


    @Override
    public void saveInstructor(int id, Instructor instructor) {
        Company company = manager.find(Company.class,id);
        company.addInstructor(instructor);
        instructor.setCompany(company);
        manager.merge(instructor);
    }

    @Override
    public void updateInstructor(int id, Instructor instructor) {
        Instructor instructor1 = manager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setSpecialization(instructor.getSpecialization());
        manager.merge(instructor1);

    }


    @Override
    public List<Instructor> getAllInstructors(int courseId) {
        return manager
                .createQuery("select i from Instructor i where i.company.id = : id",
                        Instructor.class)
                .setParameter("id",courseId).getResultList();
    }


    @Override
    public void deleteInstructorById(int id) {
        Instructor instructor = manager.find(Instructor.class, id);
        manager.remove(instructor);
    }


    @Override
    public void assignInstructorToCourse(int instId, int courseId) {
        Instructor instructor = manager.find(Instructor.class,instId);
        Course course = manager.find(Course.class,courseId);
        instructor.addCourse(course);
        course.addInstructor(instructor);
        manager.merge(instructor);
    }

    @Override
    public Instructor getInstructorById(int id) {
        return manager.find(Instructor.class,id);
    }

}
