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
public class MathResult {
    private int goalsHomeTeam;
    private int goalsAwayTeam;
}
