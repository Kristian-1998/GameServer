package lws.GameResultApplication.backend.controller;

import lws.GameResultApplication.backend.model.Game;
import lws.GameResultApplication.backend.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game savedGame = gameService.createGame(game);
        return ResponseEntity.ok(savedGame);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.findAllGames(); // Assuming a findAllGames method in GameService
        return ResponseEntity.ok(games);
    }

    @GetMapping(path = {"/{id}"})
    public Game getGameById(@PathVariable Long id) {
        return gameService.findGameById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public void deleteGameById(@PathVariable Long id) {
        gameService.deleteGameById(id);
    }

    @PatchMapping
    public Game updateGame(@RequestBody Game game) {
        return gameService.updateGame(game);
    }
}
