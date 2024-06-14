package lws.GameResultApplication.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class GameResultRequest {
        private Long eventID;
        private Long playerId;
        private Long gameId;
        private Float gameScore;
        private String gameType;
        private String playerName;
        private String playerEmail;
        private Date timeStamp;
}
