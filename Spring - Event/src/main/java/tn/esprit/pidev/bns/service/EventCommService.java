package tn.esprit.pidev.bns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.Event;
import tn.esprit.pidev.bns.entity.EventComment;
import tn.esprit.pidev.bns.repository.IEventCommRepo;
import tn.esprit.pidev.bns.repository.IEventRepo;
import tn.esprit.pidev.bns.serviceInterface.IEventCommService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EventCommService implements IEventCommService {
    @Autowired
    private IEventCommRepo commRepo;
    @Autowired
    private IEventRepo eventRepo;

    @Override
    public List<EventComment> getAll() {
        return (List<EventComment>)commRepo.findAll();
    }

    @Override
    public EventComment getById(int id) {
        return commRepo.findById(id).orElse(null);
    }

    @Override
    public EventComment addComment(EventComment comment, Integer idEvent) {
        Event event = eventRepo.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("evenement non trouvée"));
        event.getEventCommentList().add(comment);
        comment.setEventId(idEvent);
        return commRepo.save(comment);
    }

    @Override
    public boolean updateComment(EventComment comment) {
        if(commRepo.existsById(comment.getId())){
            commRepo.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteComment(int id) {
        if(commRepo.existsById(id)) {
            EventComment comment = commRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("commentaire non trouvée"));
            Event event = eventRepo.findById(comment.getEventId()).orElseThrow(() -> new EntityNotFoundException("evenement du commentaire non trouvée"));
            event.getEventCommentList().remove(comment);
            eventRepo.save(event);
            commRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
