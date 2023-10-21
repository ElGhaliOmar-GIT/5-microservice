package tn.esprit.pidev.bns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.Event;
import tn.esprit.pidev.bns.serviceInterface.IEventService;

import java.util.List;

@CrossOrigin( "http://localhost:4200/")
@RestController
@RequestMapping("events")
public class EventAPI {
    @Autowired
    private IEventService eventService;

    // -------------------- BEGIN : GET Methods --------------------
    @GetMapping("getAllEvents")
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }

    @GetMapping("/getEventById/{eventId}")
    public Event getEventById(@PathVariable("eventId") int eventId) {
        return eventService.getById(eventId);
    }
    // -------------------- END : GET Methods --------------------


    // -------------------- BEGIN : ADD Methods --------------------
    @PostMapping("addEvent")
    @ResponseBody
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }
    // -------------------- END : ADD Methods --------------------


    // -------------------- BEGIN : UPDATE Methods --------------------
    @PutMapping("updateEvent")
    public boolean updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }
    // -------------------- BEGIN : UPDATE Methods --------------------


    // -------------------- BEGIN : DELETE Methods --------------------
    @DeleteMapping("deleteEvent/{eventId}")
    public boolean deleteEvent(@PathVariable("eventId") int eventId) {
        return eventService.deleteEvent(eventId);
    }
    // -------------------- END : DELETE Methods --------------------
}
