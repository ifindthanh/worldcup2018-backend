package vn.com.smvn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.LeagueTeam.LeagueTeamBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueTable {
    private String leagueCaption;
    private int matchday;
    private Standings standings;
}
