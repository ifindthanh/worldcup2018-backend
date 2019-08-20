package vn.com.smvn.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vn.com.smvn.entity.TeamEntity;
import vn.com.smvn.model.Team;
import vn.com.smvn.model.Teams;
import vn.com.smvn.repository.TeamRepository;
import vn.com.smvn.service.DataFetchingService;
import vn.com.smvn.util.SMVNUtilities;

@Service
public class DataFetchingServiceImpl implements DataFetchingService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TeamRepository teamRepository;

    private Logger logger = LogManager.getLogger(DataFetchingServiceImpl.class);

    @Transactional
    public void fetchTeamInfo() {
        Teams teams = this.restTemplate.getForObject("http://api.football-data.org/v1/competitions/467/teams",
                Teams.class);
        for (Team item : teams.getTeams()) {
            TeamEntity team = new TeamEntity();
            team.setId(SMVNUtilities.encryptString(item.get_links().getSelf().getHref()));
            team.setName(item.getName());
            team.setShortName(item.getShortName());
            team.setCrestUrl(item.getCrestUrl());
            this.teamRepository.save(team);
        }

        logger.info("Save teams to database successfully");
    }
    
}
