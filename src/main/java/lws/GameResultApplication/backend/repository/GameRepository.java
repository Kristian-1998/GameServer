package lws.GameResultApplication.backend.repository;

import lws.GameResultApplication.backend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository  extends JpaRepository<Game, Long> {

}
