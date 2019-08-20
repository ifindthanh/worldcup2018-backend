package vn.com.smvn.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.Team.TeamBuilder;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teams {
    private List<Team> teams;
}
