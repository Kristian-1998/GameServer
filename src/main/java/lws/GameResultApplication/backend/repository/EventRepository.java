package lws.GameResultApplication.backend.repository;

import lws.GameResultApplication.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
