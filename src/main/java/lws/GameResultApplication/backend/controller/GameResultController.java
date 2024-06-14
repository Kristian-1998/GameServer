package lws.GameResultApplication.backend.controller;

import lws.GameResultApplication.backend.model.Event;
import lws.GameResultApplication.backend.model.Game;
import lws.GameResultApplication.backend.model.GameResultRequest;
import lws.GameResultApplication.backend.model.Player;
import lws.GameResultApplication.backend.repository.EventRepository;
import lws.GameResultApplication.backend.repository.GameRepository;
import lws.GameResultApplication.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game-results")
public class GameResultController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping
    public ResponseEntity<Void> saveGameResult(@RequestBody GameResultRequest request) {

        // Extract values from request
        Long playerId = request.getPlayerId();
        Long gameId = request.getGameId();
        Float gameScore = request.getGameScore();
        String gameType = request.getGameType();
        String playerName = request.getPlayerName();
        String playerEmail = request.getPlayerEmail();

        // Check if player exists, create if not
        Player player = playerRepository.findById(playerId).orElseGet(() -> {
            Player newPlayer = new Player();
            newPlayer.setName(playerName);
            newPlayer.setEmail(playerEmail);
            return playerRepository.save(newPlayer);
        });

        // Check if game exists, create if not
        Game game = gameRepository.findById(gameId).orElseGet(() -> {
            Game newGame = new Game();
            newGame.setGameId(gameId);
            newGame.setGameType(gameType);
            return gameRepository.save(newGame);
        });

        // Create and save Event
        Event event = new Event();
        event.setPlayerID(player.getPlayerId());
        event.setGameID(game.getGameId());
        event.setGameScore(gameScore);
        eventRepository.save(event);

        return ResponseEntity.ok().build();
    }
}

