package peaksoft.springbootsecurity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootsecurity.model.Instructor;
import peaksoft.springbootsecurity.serviceImple.service.CourseService;
import peaksoft.springbootsecurity.serviceImple.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/instructors/{id}")
    private String getAllInstructors(@PathVariable int id, Model model) {
        model.addAttribute("instructorAtr", instructorService.getAllInstructors(id));
        model.addAttribute("companyId", id);
        model.addAttribute("instructorsC", courseService.getAllCourses(id));
        return "instructor/instructors";
    }






    @GetMapping("/{id}/addInstructor")
    private String addInstructor(@PathVariable int id, Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("courseId", id);
        return "/instructor/addInstructor";
    }
    @PostMapping("/{id}/saveInstructor")
    private String saveInstructor(@ModelAttribute("instructor") Instructor instructor,
                                  @PathVariable("id") int id) {
        instructorService.saveInstructor(id, instructor);
        return "redirect:/instructors/instructors/ " + id;
    }






    @GetMapping("/getInstructor/{id}")
    private String getInstructorById(@PathVariable("id")int id,Model model) {
        model.addAttribute("id",instructorService.getInstructorById(id));
        return "/instructor/instructors";
    }
    @GetMapping("/updateInstructor/{instructorId}")
    private String updateInstructor(@PathVariable("instructorId") int insId,Model model){
        Instructor instructor = instructorService.getInstructorById(insId);
        model.addAttribute("instructor", instructor);
        model.addAttribute("companyId",instructor.getCompany().getId());
        return "/instructor/updateInstructor";
    }
    @PostMapping("/{companyId}/{instructorId}/saveUpdate")
    private String saveUpdateInstructor(@PathVariable("companyId") int comId,
                                        @PathVariable("instructorId") int instrId,
                                        @ModelAttribute("instructor")Instructor instructor){
        instructorService.updateInstructor(instrId,instructor);
        return "redirect:/instructors/instructors/ "+ comId;

    }





    @PostMapping("/{comId}/{instrId}/delete")
    private String deleteInstructor(@PathVariable("comId") int comId,
                                    @PathVariable("instrId") int instrId){
        instructorService.deleteInstructorById(instrId);
        return "redirect:/instructors/instructors/ "+comId;
    }




}
