package vn.com.smvn.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.smvn.entity.TeamEntity;
import vn.com.smvn.repository.TeamRepository;
import vn.com.smvn.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

    Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamEntity getTeamFromCode(String id) {
        return this.teamRepository.findById(id);
    }

}
