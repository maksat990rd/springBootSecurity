package peaksoft.springbootsecurity.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootsecurity.model.Video;
import peaksoft.springbootsecurity.serviceImple.service.VideoService;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/allVideos/{lessonId}")
    private String  getAllVideos(@PathVariable("lessonId")int lessonId, Model model) {
        model.addAttribute("allVideos",videoService.getAllVideos(lessonId));
        model.addAttribute("lesson",lessonId);
        return "video/mainVideo";
    }



    @GetMapping("/{lessonId}/newVideo")
    private String newVideo(@PathVariable("lessonId")int id,Model model) {
        model.addAttribute("newVideo",new Video());
        model.addAttribute("lessonId",id);
        return "video/newVideo";
    }
    @PostMapping("/{lessonId}/saveVideo")
    private String saveVideo(@PathVariable("lessonId")int id,
                             @ModelAttribute("newVideo")Video video) {
        videoService.saveVideo(id,video);
        return "redirect:/videos/allVideos/ "+id;
    }




    @GetMapping("/getVideo/{videoId}")
    private String getVideoById(@PathVariable("videoId")int id,
                                Model model) {
        model.addAttribute("video",videoService.getVideoById(id));
        return "video/mainVideo";
    }




    @GetMapping("/updateVideo/{videoId}")
    private String updateVideo(@PathVariable("videoId")int id,Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video",video);
        model.addAttribute("lessonId",video.getLesson().getId());
        return "video/updateVideo";
    }
    @PostMapping("/{lessonId}/{videoId}/saveUpdateVideo")
    private String saveUpdateVideo(@PathVariable("lessonId")int id,
                                   @PathVariable("videoId")int videoId,
                                   @ModelAttribute("video")Video video) {
        videoService.updateVideo(videoId,video);
        return "redirect:/videos/allVideos/ " + id;
    }





    @PostMapping("/{lessonId}/{videoId}/deleteVideo")
    private String deleteVideo(@PathVariable("lessonId")int id,
                               @PathVariable("videoId")int videoId) {
        videoService.deleteVideoById(videoId);
        return "redirect:/videos/allVideos/ " + id;
    }
}

