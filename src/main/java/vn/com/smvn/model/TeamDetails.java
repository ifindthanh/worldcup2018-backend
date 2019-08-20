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
public class TeamDetails {
    private TeamReferrence self;
    private TeamReferrence fixtures;
    private TeamReferrence players;
}
