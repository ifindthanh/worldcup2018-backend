package vn.com.smvn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueTeam {

    private String group;
    private int rank;
    private String team;
    private int teamId;
    private int playedGames;
    private String crestURI;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;

}
