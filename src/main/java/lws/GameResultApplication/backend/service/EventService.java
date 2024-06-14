package lws.GameResultApplication.backend.service;

import lombok.RequiredArgsConstructor;
import lws.GameResultApplication.backend.exception.EventNotFoundException;
import lws.GameResultApplication.backend.model.Event;
import lws.GameResultApplication.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository; // Assuming you have an EventRepository class

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with id " + id + " not found")); // Consider a custom exception for Events
    }

    public void deleteEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with id " + id + " not found")); // Consider a custom exception for Events
        eventRepository.delete(event);
    }

    public Event updateEvent(Event event) {
        Long eventId = event.getEventId();
        eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event with id " + eventId + " not found")); // Consider a custom exception for Events
        return eventRepository.save(event);
    }
}

