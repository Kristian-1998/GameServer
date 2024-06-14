package lws.GameResultApplication.backend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Event{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @Column (name = "player_id")
    private Long playerID;
    @Column (name = "game_id")
    private Long gameID;
    private Float gameScore;
}
