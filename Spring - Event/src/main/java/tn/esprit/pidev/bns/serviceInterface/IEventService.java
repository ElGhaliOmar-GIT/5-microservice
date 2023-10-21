package tn.esprit.pidev.bns.serviceInterface;

import tn.esprit.pidev.bns.entity.Event;

import java.util.List;

public interface IEventService {
    public List<Event> getAll();

    public Event getById(int id);

    public Event addEvent(Event event);

    public boolean updateEvent(Event event);

    public boolean deleteEvent(int id);
}
