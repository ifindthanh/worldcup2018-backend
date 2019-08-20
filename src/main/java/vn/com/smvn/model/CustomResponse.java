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
public class CustomResponse<T> {
    private int code;
    private String message;
    private T result;
    
}
