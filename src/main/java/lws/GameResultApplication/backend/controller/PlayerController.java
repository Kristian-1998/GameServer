package lws.GameResultApplication.backend.controller;

import lws.GameResultApplication.backend.repository.PlayerRepository;
import lws.GameResultApplication.backend.service.PlayerService;
import lws.GameResultApplication.backend.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/player")
    public class PlayerController {

        @Autowired
        private PlayerRepository playerRepository;
        private final PlayerService playerService;

        public PlayerController(PlayerService playerService) {
            this.playerService = playerService;
        }

        @PostMapping
        public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
            Player savedPlayer = playerRepository.save(player);
            return ResponseEntity.ok(savedPlayer);
        }

        @GetMapping
        public ResponseEntity<List<Player>> getAllPlayers() {
            List<Player> players = playerRepository.findAll();
            return ResponseEntity.ok(players);
        }

        @GetMapping(path = {"/{id}"})
        public Player getPlayerById(@PathVariable Long id) {
            return playerService.findPlayerById(id);
        }
        @DeleteMapping(path = {"/{id}"})
        public void deletePlayerById(@PathVariable Long id) {
            playerService.deletePlayerById(id);
        }

        @PatchMapping
        public Player updatePlayer(@RequestBody Player player) {
            return playerService.updatePlayer(player);
        }
    }

