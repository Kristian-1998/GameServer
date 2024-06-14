package lws.GameResultApplication.backend.service;

import lombok.RequiredArgsConstructor;
import lws.GameResultApplication.backend.repository.GameRepository;
import lws.GameResultApplication.backend.exception.GameNotFoundException;
import lws.GameResultApplication.backend.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public Game findGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + "not found")
                );
    }

    public void deleteGameById (Long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + "not found"));
        gameRepository.delete(game);
    }

    public Game updateGame (Game game){
        Long id = game.getGameId();
        gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + game.getGameId() + "not found"));
        return gameRepository.save(game);
    }

}
