package org.example.rtms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.rtms.dto.StadiumDTO;
import org.example.rtms.model.entity.Team;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class
MatchResponseDTO {
    private UUID uuid; //Set using UUID.Random() when creating a match
    private StadiumDTO stadium; //Set using HomeTeam stadium
    private LocalDate date;
    private LocalTime time;
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private int spectators;
    private BigDecimal revenue; //Calculated by spectators number

}