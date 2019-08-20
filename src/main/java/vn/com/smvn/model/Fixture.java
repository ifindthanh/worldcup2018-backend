package vn.com.smvn.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.MathResult.MathResultBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fixture {
    private MatchReferrences _links;
    private Date date;
    private String status;
    private String homeTeamName;
    private String awayTeamName;
    private MathResult result;
    private String dateFormatted;   

}
