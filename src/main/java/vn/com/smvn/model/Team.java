package vn.com.smvn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.LeagueTable.LeagueTableBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private TeamDetails _links;
    private String name;
    private String code;
    private String shortName;
    private Double squadMarketValue;
    private String crestUrl;
}
