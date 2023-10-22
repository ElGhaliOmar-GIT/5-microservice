package tn.esprit.pidev.bns.controller.siwarbacc;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.siwarbacc.Comment;
import tn.esprit.pidev.bns.exception.siwarbacc.BadWordException;
import tn.esprit.pidev.bns.repository.siwarbacc.CommentRepository;
import tn.esprit.pidev.bns.repository.siwarbacc.ForumRepository;
import tn.esprit.pidev.bns.serviceInterface.siwarbacc.ICommentService;
import tn.esprit.pidev.bns.serviceInterface.siwarbacc.IForumService;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
@CrossOrigin("http://localhost:4200/")
@RestController
@AllArgsConstructor
@RequestMapping("/Comment")

public class CommentRestController {
    @Autowired
    ICommentService commentService;
    @Autowired
    IForumService forumService;


    private static final List<String> BAD_WORDS = Arrays.asList("Conasse", "hell", "blame");
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ForumRepository forumRepository;



    @GetMapping("/retrieve-all-Comments")
    public ResponseEntity<List<Comment>> retrieveAllComments() {
        List<Comment> comments = commentService.retrieveAllComments();
        return ResponseEntity.ok(comments);
    }


    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/add-comment")
    public ResponseEntity<String> addComment(@RequestBody  Comment comment , @RequestParam Integer idForum ) throws NoSuchMethodException {

        commentService.addComment(comment);
        commentService.assignCommentToForum(comment.getIdComment(),idForum);
  return       ResponseEntity.ok().body("comment added");
    }

    @ExceptionHandler(BadWordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadWordException() {
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeComment(@PathVariable Integer id) {
        try {
            commentService.removeComment(id);
            return ResponseEntity.ok("Comment with ID " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }





    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id, @RequestBody Comment updcomment) {
        try {
            Comment updatedComment = commentService.updateComment(id, updcomment);
            return ResponseEntity.ok(updatedComment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadWordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private boolean checkText(String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (BAD_WORDS.contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }


    @PostMapping("/{commentId}/forum/{forumId}")
    public ResponseEntity<Comment> assignCommentToForum(@PathVariable Integer commentId, @PathVariable Integer forumId) {
        try {
            Comment comment = commentService.assignCommentToForum(commentId, forumId);
            return ResponseEntity.ok(comment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (NoSuchMethodException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("getAllCommentByForumId")
    public ResponseEntity<List<Comment>> getAllCommentByForumId(@RequestParam int idForum){
        return ResponseEntity.ok(commentService.getAllCommentByForum(idForum));
    }
}








