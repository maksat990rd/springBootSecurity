package peaksoft.springbootsecurity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootsecurity.model.Course;
import peaksoft.springbootsecurity.model.Instructor;
import peaksoft.springbootsecurity.serviceImple.service.CompanyService;
import peaksoft.springbootsecurity.serviceImple.service.CourseService;
import peaksoft.springbootsecurity.serviceImple.service.InstructorService;


@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService,
                           InstructorService instructorService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
    }


    @GetMapping("/courses/{id}")
    private String getAllCourses(@PathVariable int id, Model model,
                                 @ModelAttribute("inst") Instructor instructor) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("companyId",id);
        model.addAttribute("instructors",instructorService.getAllInstructors(id));
        return "course/course";
    }





    @PostMapping("/{companyId}/{courseId}/saveAssign")
    private String saveAssign(@PathVariable("courseId")int courseId,
                              @ModelAttribute("inst") Instructor instructor,
                              @PathVariable("companyId") int compId) {
        instructorService.assignInstructorToCourse(instructor.getId(),courseId);
        return "redirect:/courses/courses/ "+compId;
    }



    @GetMapping("/{id}/addCourse")
    private String addCourse(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "course/addCourse";
    }
    @PostMapping("/{id}/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course,
                             @PathVariable("id") int id) {
        courseService.saveCourse(id, course);
        return "redirect:/courses/courses/ "+id;
    }





    @GetMapping("/getCourse/{courseId}")
    private String getCourseById(@PathVariable("courseId") int courseId, Model model) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "course/course";
    }
    @GetMapping("/update/{courseId}")
    public String updateCourse(@PathVariable("courseId") int courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("compId", course.getCompany().getId());
        return "course/updateCourse";
    }
    @PostMapping("/{companyId}/{courseId}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("companyId") int companyId,
                                   @PathVariable("courseId") int courseId,
                                   @ModelAttribute("course") Course course) {
        courseService.updateCourse(course,courseId);
        return "redirect:/courses/courses/ " + companyId;
    }







    @PostMapping("/{id}/{compId}")
    private String deleteCourse(@PathVariable("id") int id,
                                @PathVariable("compId") int compId) {
        courseService.deleteCourse(id);
        return "redirect:/courses/courses/ "+compId;
    }
}