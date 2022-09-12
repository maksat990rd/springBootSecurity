package peaksoft.springbootsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootsecurity.model.Lesson;
import peaksoft.springbootsecurity.serviceImple.service.LessonService;


@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @GetMapping("/allLessons/{courseId}")
    private String getAllLessons(@PathVariable("courseId")int courseId,
                                 Model model) {
        model.addAttribute("allLessons",lessonService.getAllLessons(courseId));
        model.addAttribute("courseId",courseId);
        return "lesson/lessons";
    }




    @GetMapping("/{courseId}/addLesson")
    private String newLesson(@PathVariable("courseId")int id,Model model) {
        model.addAttribute("newLesson",new Lesson());
        model.addAttribute("courseId",id);
        return "lesson/addLesson";
    }
    @PostMapping("/{courseId}/saveLesson")
    private String saveLesson(@PathVariable("courseId")int id,
                              @ModelAttribute("newLesson")Lesson lesson) {
        lessonService.saveLesson(id,lesson);
        return "redirect:/lessons/allLessons/ " + id;
    }





    @GetMapping("/getLesson/{lessonId}")
    private String getLessonById(@PathVariable("lessonId")int id,
                                 Model model) {
        model.addAttribute("lesson",lessonService.getLessonById(id));
        return "lesson/lessons";
    }




    @GetMapping("/updateLesson/{lessonId}")
    private String updateLesson(@PathVariable("lessonId")int lessonId,
                                Model model) {
        Lesson lesson = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson",lesson);
        model.addAttribute("courseId",lesson.getCourse().getId());
        return "lesson/updateLesson";
    }
    @PostMapping("/{courseId}/{lessonId}/saveUpdateLesson")
    private String saveUpdateLesson(@PathVariable("courseId")int courseId,
                                    @PathVariable("lessonId")int lessonId,
                                    @ModelAttribute("lesson")Lesson lesson) {
        lessonService.updateLesson(lessonId,lesson);
        return "redirect:/lessons/allLessons/ " + courseId;
    }






    @PostMapping("/{courseId}/{lessonId}/delete")
    private String deleteLesson(@PathVariable("courseId")int id,
                                @PathVariable("lessonId")int lessonId) {
        lessonService.deleteLessonById(lessonId);
        return "redirect:/lessons/allLessons/ " + id;
    }
}
