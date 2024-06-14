package lws.GameResultApplication.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

    @Entity
    public class Player {
        @Id
        private Long playerId;
        private String name;
        private String email;
    }