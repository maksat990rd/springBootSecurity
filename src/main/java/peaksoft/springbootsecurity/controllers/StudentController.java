package peaksoft.springbootsecurity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootsecurity.model.Course;
import peaksoft.springbootsecurity.model.Student;
import peaksoft.springbootsecurity.serviceImple.service.CourseService;
import peaksoft.springbootsecurity.serviceImple.service.StudentService;


@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/students/{companyId}")
    private String getAllStudents(@PathVariable("companyId") int companyId,
                                  @ModelAttribute("course") Course course,
                                  Model model ){
        model.addAttribute("studentAtr",studentService.getAllStudents(companyId));
        model.addAttribute("coursId",companyId);
        model.addAttribute("studentsC",courseService.getAllCourses(companyId));
        return "/student/students";
    }




    @PostMapping("/{companyId}/{studentId}/saveAssign")
    private String saveAssign(@PathVariable("studentId") int studentId,
                              @PathVariable("companyId") int companyId,
                              @ModelAttribute("course")Course course){
        studentService.assignStudentToCourse(studentId,course.getId());
        return "redirect:/students/students/ "+companyId;
    }



    @GetMapping("/{companyId}/addStudent")
    private String addStudent(@PathVariable("companyId") int companyId,Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("companyId",companyId);
        return "/student/addStudent";
    }
    @PostMapping("/{companyId}/saveStudent")
    private String saveStudent(@ModelAttribute("student" )Student student,
                               @PathVariable("companyId") int companyId){
        studentService.saveStudent(companyId,student);
        return "redirect:/students/students/ "+companyId;
    }



    @GetMapping("/updateStudent/{studentId}")
    private String updateStudent(@PathVariable("studentId") int stdId,Model model){
        Student student = studentService.getStudentById(stdId);
        model.addAttribute("student",student);
        model.addAttribute("companyId",student.getCompany().getId());
        return "/student/updateStudent";
    }
    @PostMapping("{companyId}/{studentId}/saveUpdate")
    private String saveUpdateStudent(@PathVariable("companyId") int companyId,
                                     @PathVariable("studentId") int studentId,
                                     @ModelAttribute("student") Student student){
        studentService.updateStudent(studentId,student);
        return "redirect:/students/students/ "+companyId;
    }


    @PostMapping("/{companyId}/{studentId}/delete")
    private String deleteInstructor(@PathVariable("companyId") int companyId,
                                    @PathVariable("studentId")int studentId){
         studentService.deleteStudentById(studentId);
         return "redirect:/students/students/ "+companyId;
    }
}
