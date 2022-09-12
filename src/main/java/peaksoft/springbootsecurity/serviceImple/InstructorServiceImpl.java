package peaksoft.springbootsecurity.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.springbootsecurity.model.Instructor;
import peaksoft.springbootsecurity.repositoryImpl.repository.InstructorRepository;
import peaksoft.springbootsecurity.serviceImple.service.InstructorService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }



    @Override
    public void saveInstructor(int id, Instructor instructor) {
        instructorRepository.saveInstructor(id,instructor);
    }

    @Override
    public void updateInstructor(int id,Instructor instructor) {
        instructorRepository.updateInstructor(id, instructor);
    }

    @Override
    public List<Instructor> getAllInstructors(int courseId) {
        return instructorRepository.getAllInstructors(courseId);
    }

    @Override
    public void deleteInstructorById(int id) {
        instructorRepository.deleteInstructorById(id);
    }

    @Override
    public void assignInstructorToCourse(int instId, int courseId) {
        instructorRepository.assignInstructorToCourse(instId,courseId);
    }

    @Override
    public Instructor getInstructorById(int id) {
        return instructorRepository.getInstructorById(id);
    }
}
