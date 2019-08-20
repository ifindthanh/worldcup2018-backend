package vn.com.smvn.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.LeagueTeam.LeagueTeamBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Standings {
    private List<LeagueTeam> A;
    private List<LeagueTeam> B;
    private List<LeagueTeam> C;
    private List<LeagueTeam> D;
    private List<LeagueTeam> E;
    private List<LeagueTeam> F;
    private List<LeagueTeam> G;
    private List<LeagueTeam> H;
}
