package vn.com.smvn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.TeamReferrence.TeamReferrenceBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchReferrences {
    private TeamReferrence homeTeam;
    private TeamReferrence awayTeam;
}
