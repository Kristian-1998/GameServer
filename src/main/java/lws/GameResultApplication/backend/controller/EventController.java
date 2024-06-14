package lws.GameResultApplication.backend.controller;

import lws.GameResultApplication.backend.model.Event;
import lws.GameResultApplication.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.findAllEvents(); // Assuming a findAllEvents method in EventService
        return ResponseEntity.ok(events);
    }

    @GetMapping(path = {"/{id}"})
    public Event getEventById(@PathVariable Long id) {
        return eventService.findEventById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public void deleteEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
    }

    @PatchMapping
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }
}

