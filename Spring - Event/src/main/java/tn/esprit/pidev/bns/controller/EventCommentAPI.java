package tn.esprit.pidev.bns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.EventComment;
import tn.esprit.pidev.bns.serviceInterface.IEventCommService;

import java.util.List;

@CrossOrigin( "http://localhost:4200/")
@RestController
@RequestMapping("comments")
public class EventCommentAPI {
    @Autowired
    private IEventCommService commService;

    // -------------------- BEGIN : GET Methods --------------------
    @GetMapping("getAllComments")
    public List<EventComment> getAllComments() {
        return commService.getAll();
    }

    @GetMapping("/getCommentById/{commentId}")
    public EventComment getCommentById(@PathVariable("commentId") int commentId) {
        return commService.getById(commentId);
    }
    // -------------------- END : GET Methods --------------------


    // -------------------- BEGIN : ADD Methods --------------------
    @PostMapping("addComment/{idEvent}")
    @ResponseBody
    public EventComment addComment(@RequestBody EventComment comment, @PathVariable Integer idEvent) {
        return commService.addComment(comment, idEvent);
    }
    // -------------------- END : ADD Methods --------------------


    // -------------------- BEGIN : UPDATE Methods --------------------
    @PutMapping("updateComment")
    public boolean updateComment(@RequestBody EventComment comment) {
        return commService.updateComment(comment);
    }
    // -------------------- BEGIN : UPDATE Methods --------------------


    // -------------------- BEGIN : DELETE Methods --------------------
    @DeleteMapping("deleteComment/{commentId}")
    public boolean deleteComment(@PathVariable("commentId") int commentId) {
        return commService.deleteComment(commentId);
    }
    // -------------------- END : DELETE Methods --------------------
}
