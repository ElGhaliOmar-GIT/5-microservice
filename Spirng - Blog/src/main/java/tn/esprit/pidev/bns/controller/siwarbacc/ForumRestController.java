package tn.esprit.pidev.bns.controller.siwarbacc;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.siwarbacc.Forum;
import tn.esprit.pidev.bns.service.siwarbacc.ForumService;
import tn.esprit.pidev.bns.serviceInterface.siwarbacc.IForumService;

import java.util.List;
@CrossOrigin("http://localhost:4200/")
@RestController
@AllArgsConstructor
@RequestMapping("/forum")

public class ForumRestController {
    @Autowired
    IForumService forumService;

    @GetMapping("/retrieve-all-forums")
    public List<Forum> getForums() {
        return forumService.retrieveAllForums();
    }

    @GetMapping("/retrieve-forum/{forum-id}")
    public Forum retrieveForum(@PathVariable("forum-id") Integer forumId) {
        return forumService.retrieveForum(forumId);
    }

    @PostMapping("/add-forum")
    public Forum addForum(@RequestBody Forum f) {
        Forum forum = forumService.addForum(f);
        return forum;
    }

    @DeleteMapping("/remove-forum/{forum-id}")
    public void removeForum(@PathVariable("forum-id") Integer forumId) {
        forumService.removeForum(forumId);
    }

    @PutMapping("/update-forum")
    public Forum updateForum(@RequestBody Forum f) {
        Forum forum= forumService.updateForum(f);
        return forum;
    }


    @GetMapping("/search/{keyword}")
    public List<Forum> searchForums(@PathVariable("keyword") String keyword) {
        return forumService.searchForums(keyword);
    }


}
