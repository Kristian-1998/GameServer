package lws.GameResultApplication.backend.service;
import lombok.RequiredArgsConstructor;
import lws.GameResultApplication.backend.repository.PlayerRepository;
import lws.GameResultApplication.backend.exception.PlayerNotFoundException;
import lws.GameResultApplication.backend.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> findAllPlayer() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + "not found")
                );
    }

    public void deletePlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + "not found"));
        playerRepository.delete(player);
    }

    public Player updatePlayer(Player player) {
        Long id = player.getPlayerId();
        playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + player.getPlayerId() + "not found"));
        return playerRepository.save(player);
    }
}