package tn.esprit.pidev.bns.serviceInterface;

import tn.esprit.pidev.bns.entity.EventComment;

import java.util.List;

public interface IEventCommService {
    public List<EventComment> getAll();

    public EventComment getById(int id);

    public EventComment addComment(EventComment comment, Integer idEvent);

    public boolean updateComment(EventComment comment);

    public boolean deleteComment(int id);
}
