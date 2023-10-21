package tn.esprit.pidev.bns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.Event;
import tn.esprit.pidev.bns.repository.IEventRepo;
import tn.esprit.pidev.bns.serviceInterface.IEventService;

import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    private IEventRepo evenetRepo;

    @Override
    public List<Event> getAll() {
        return (List<Event>)evenetRepo.findAll();
    }

    @Override
    public Event getById(int id) {
        return evenetRepo.findById(id).orElse(null);
    }

    @Override
    public Event addEvent(Event event) {
        return evenetRepo.save(event);
    }

    @Override
    public boolean updateEvent(Event event) {
        if(evenetRepo.existsById(event.getId())){
            evenetRepo.save(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEvent(int id) {
        if(evenetRepo.existsById(id)) {
            evenetRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
