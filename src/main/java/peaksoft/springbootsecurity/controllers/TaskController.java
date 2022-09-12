package peaksoft.springbootsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootsecurity.model.Task;
import peaksoft.springbootsecurity.serviceImple.service.TaskService;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allTasks/{lessonId}")
    private String getAllTasks(@PathVariable("lessonId")int lessonId, Model model) {
        model.addAttribute("allTasks", taskService.getAllTasks(lessonId));
        model.addAttribute("lessonId",lessonId);
        return "task/mainTask";
    }





    @GetMapping("/{lessonId}/newTask")
    private String newTask(@PathVariable("lessonId")int id,Model model) {
        model.addAttribute("newTask",new Task());
        model.addAttribute("lessonId",id);
        return "task/newTask";
    }
    @PostMapping("/{lessonId}/saveTask")
    private String saveTask(@PathVariable("lessonId")int id,
                            @ModelAttribute("newTask")Task task) {
        taskService.saveTask(id,task);
        return "redirect:/tasks/allTasks/ "+ id;
    }





    @GetMapping("/getTask/{taskId}")
    private String getLessonById(@PathVariable("taskId")int id,Model model) {
        model.addAttribute("task",taskService.getTaskById(id));
        return "task/mainTask";
    }


    @GetMapping("/updateTask/{taskId}")
    private String updateTask(@PathVariable("taskId")int taskId,Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task",task);
        model.addAttribute("lessonId",task.getLesson().getId());
        return "task/updateTask";
    }
    @PostMapping("/{lessonId}/{taskId}/saveUpdateTask")
    private String saveUpdateTask(@PathVariable("lessonId")int lessonId,
                                  @PathVariable("taskId")int taskId,
                                  @ModelAttribute("task")Task task) {
        taskService.updateTask(taskId,task);
        return "redirect:/tasks/allTasks/ " + lessonId;

    }



    @PostMapping("/{lessonId}/{taskId}/deleteTask")
    private String deleteTask(@PathVariable("lessonId")int id,@PathVariable("taskId")int taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/tasks/allTasks/ " + id;
    }
}
