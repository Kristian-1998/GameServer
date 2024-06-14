package lws.GameResultApplication.backend.repository;

import lws.GameResultApplication.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
