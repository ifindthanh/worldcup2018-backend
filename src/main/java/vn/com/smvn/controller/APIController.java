package vn.com.smvn.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import vn.com.smvn.entity.TeamEntity;
import vn.com.smvn.model.CustomResponse;
import vn.com.smvn.model.Fixture;
import vn.com.smvn.model.FixturesByDay;
import vn.com.smvn.model.LeagueTable;
import vn.com.smvn.model.PagingFixtures;
import vn.com.smvn.model.TeamReferrence;
import vn.com.smvn.service.TeamService;
import vn.com.smvn.util.SMVNUtilities;

@RestController
@EnableAutoConfiguration
@RequestMapping("/smvn-worldcup/api")
public class APIController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TeamService teamService;

    private static SimpleDateFormat time = new SimpleDateFormat("HH:mm");

    private static Logger logger = LogManager.getLogger(APIController.class);

    @RequestMapping("/leagueTable")
    public CustomResponse<LeagueTable> leaguteTable() {
        LeagueTable leagueTable = this.restTemplate
                .getForObject("http://api.football-data.org/v1/competitions/467/leagueTable", LeagueTable.class);
        logger.info("Get /leagueTable successfully");
        return new CustomResponse<LeagueTable>(1, "sucess", leagueTable);
    }

    @RequestMapping("/fixturesByDay")
    public CustomResponse<PagingFixtures> getFixturesByDay(Pageable pageable) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        PagingFixtures fixtures = this.restTemplate
                .getForObject("http://api.football-data.org/v1/competitions/467/fixtures", PagingFixtures.class);
        // loading the matchs of two days
        logger.info("Getting data from http://api.football-data.org/v1/competitions/467/fixtures");
        PagingFixtures result = new PagingFixtures();
        int size = fixtures.getFixtures().size();

        Map<String, List<Fixture>> temp = new LinkedHashMap<String, List<Fixture>>();
        int index = pageable.getPageNumber();
        for (int i = pageable.getPageNumber() - 1; i < size; i++) {
            Fixture fixture = fixtures.getFixtures().get(i);

            fixture.setDateFormatted(time.format(fixture.getDate()));
            Date date = fixture.getDate();
            String formattedDate = sdf.format(date);
            result.getFixtures().add(fixture);
            FixturesByDay item = new FixturesByDay();
            item.setDate(formattedDate);

            if ("FINISHED".equals(fixture.getStatus()) || "TIMED".equals(fixture.getStatus())) {
                // setting CrestUrl
                TeamReferrence homeTeam = fixture.get_links().getHomeTeam();
                String homeHref = homeTeam.getHref();
                TeamEntity teamEntity = this.teamService.getTeamFromCode(SMVNUtilities.encryptString(homeHref));
                if (teamEntity != null) {
                    homeTeam.setCrestUrl(teamEntity.getCrestUrl());
                }

                TeamReferrence awayTeam = fixture.get_links().getAwayTeam();
                TeamEntity awayTeamEntity = this.teamService
                        .getTeamFromCode(SMVNUtilities.encryptString(awayTeam.getHref()));
                if (awayTeamEntity != null) {
                    awayTeam.setCrestUrl(awayTeamEntity.getCrestUrl());
                }
            }

            if (temp.containsKey(formattedDate)) {
                temp.get(formattedDate).add(fixture);
                index++;
            } else {
                // only fetch the match of 2 days
                if (temp.size() == 2) {
                    break;
                }
                ArrayList<Fixture> tempFixtures = new ArrayList<Fixture>();
                tempFixtures.add(fixture);
                temp.put(formattedDate, tempFixtures);
                index++;
            }

        }

        List<FixturesByDay> fixturesByDays = new ArrayList<FixturesByDay>();
        Iterator<Entry<String, List<Fixture>>> iterator = temp.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, List<Fixture>> entry = iterator.next();
            fixturesByDays.add(new FixturesByDay(entry.getKey(), entry.getValue()));
        }

        result.setHasMoreElement(index <= size);
        result.setFixturesByDay(fixturesByDays);
        result.setNextIndex(index);
        return new CustomResponse<PagingFixtures>(1, "sucess", result);
    }

    @RequestMapping("/teamCrestUrl")
    public CustomResponse<PagingFixtures> getTeamCrestUrl(Pageable pageable) {
        PagingFixtures fixtures = this.restTemplate
                .getForObject("http://api.football-data.org/v1/competitions/467/fixtures", PagingFixtures.class);

        PagingFixtures result = new PagingFixtures();
        int size = fixtures.getFixtures().size();
        long max = pageable.getOffset() > size ? size : pageable.getOffset();
        for (int i = (pageable.getPageNumber() - 1) * pageable.getPageSize(); i < max; i++) {
            Fixture fixture = fixtures.getFixtures().get(i);

            result.getFixtures().add(fixture);
        }

        result.setHasMoreElement(max < size);
        return new CustomResponse<PagingFixtures>(1, "sucess", result);
    }

    @RequestMapping("/todayMatch")
    public CustomResponse<PagingFixtures> getTodayMatch() {
        PagingFixtures fixtures = this.restTemplate.getForObject(
                "http://api.football-data.org/v1/competitions/467/fixtures?timeFrame=n1", PagingFixtures.class);

        for (Fixture item : fixtures.getFixtures()) {
            item.setDateFormatted(time.format(item.getDate()));
            // setting CrestUrl
            if ("FINISHED".equals(item.getStatus()) || "TIMED".equals(item.getStatus())) {
                TeamReferrence homeTeam = item.get_links().getHomeTeam();
                String homeHref = homeTeam.getHref();
                TeamEntity teamEntity = this.teamService.getTeamFromCode(SMVNUtilities.encryptString(homeHref));
                if (teamEntity != null) {
                    homeTeam.setCrestUrl(teamEntity.getCrestUrl());
                }

                TeamReferrence awayTeam = item.get_links().getAwayTeam();
                TeamEntity awayTeamEntity = this.teamService
                        .getTeamFromCode(SMVNUtilities.encryptString(awayTeam.getHref()));
                if (awayTeamEntity != null) {
                    awayTeam.setCrestUrl(awayTeamEntity.getCrestUrl());
                }
            }

        }
        return new CustomResponse<PagingFixtures>(1, "sucess", fixtures);
    }

}
