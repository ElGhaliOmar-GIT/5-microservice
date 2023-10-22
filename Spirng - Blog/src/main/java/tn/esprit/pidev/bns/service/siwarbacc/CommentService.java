package tn.esprit.pidev.bns.service.siwarbacc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.siwarbacc.Comment;

import tn.esprit.pidev.bns.entity.siwarbacc.Forum;
import tn.esprit.pidev.bns.exception.siwarbacc.BadWordException;
import tn.esprit.pidev.bns.repository.siwarbacc.CommentRepository;
import tn.esprit.pidev.bns.repository.siwarbacc.ForumRepository;
import tn.esprit.pidev.bns.serviceInterface.siwarbacc.ICommentService;


import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ForumRepository forumRepository;


    public List<Comment> retrieveAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    //public Comment updateComment(Comment c) {// return commentRepository.save(c);}
    public Comment updateComment(Integer id, Comment updcomment) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        // Check if the updated comment contains any inappropriate words
        String content = updcomment.getContent();
        boolean hasBadWords = !checkText(content);
        if (hasBadWords) {
            throw new BadWordException("The comment contains inappropriate language.");
        }

        // Update the comment and save to the database
        comment.setContent(updcomment.getContent());
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }
    // public Comment addComment(Comment c) { return commentRepository.save(c);}


    // public Comment retrieveComment(Integer id) {
    public Comment getCommentById(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return comment;
    }


    //public void removeComment(Integer id) {Comment c = retrieveComment(id);commentRepository.delete(c);}
    public void removeComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }


    private static final List<String> BAD_WORDS = Arrays.asList("conasse", "hell", "blame");


    public boolean checkText(String text) {
        // Check if the text contains any inappropriate words
        // Return true if it does not contain any inappropriate words, false otherwise
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (BAD_WORDS.contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Comment addComment(Comment comment) {
        // Check if the comment contains any inappropriate words
        String content = comment.getContent();
        boolean hasBadWords = !checkText(content);

        if (hasBadWords) {
            // If it contains inappropriate words, throw a custom exception
            throw new BadWordException("The comment contains inappropriate language.");
        } else {
            // If it does not contain any inappropriate words, save the comment to the database
            Comment savedComment = commentRepository.save(comment);
            // Return the saved comment
            return savedComment;
        }
    }

    @Override
    public Comment assignCommentToForum(Integer commentId, Integer forumId) throws EntityNotFoundException {
        Comment comment = commentRepository
                .findById(commentId).orElseThrow(() -> new EntityNotFoundException("no such comment with id :"+ commentId));
        Forum forum = forumRepository.findById(forumId).orElseThrow(() -> new EntityNotFoundException("no such forum with id :" + forumId));
        comment.setForum(forum);
         return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentByForum(int id){
        return commentRepository.findAllByForumIdForum(id);
    }

}



