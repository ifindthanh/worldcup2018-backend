package vn.com.smvn.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import vn.com.smvn.model.Fixture;
import vn.com.smvn.model.PagingFixtures;
import vn.com.smvn.util.TwilioUtils;

@Component
public class ScheduledTasks {

    private static final Logger log = LogManager.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    RestTemplate restTemplate;

    @Scheduled(cron = "0 15 * * * *")
    public void reportCurrentTime() {
        PagingFixtures fixtures = this.restTemplate.getForObject(
                "http://api.football-data.org/v1/competitions/467/fixtures?timeFrame=n1", PagingFixtures.class);
        List<String> receivers = new ArrayList<String>();
        receivers.add("+841685180626");
        Date now = new Date();
        for (Fixture fixture : fixtures.getFixtures()) {
            Date matchDate = fixture.getDate();
            if (now.after(new Date(matchDate.getTime() - TimeUnit.HOURS.toMillis(1))) && now.before(matchDate)) {
                log.info("Job is alerted => send SMS to everybody");
                TwilioUtils.sendNotification(fixture.getHomeTeamName(), fixture.getAwayTeamName(),
                        dateFormat.format(matchDate), receivers);
                break;
            }
        }
        log.info("The time is now {}", dateFormat.format(now));
    }
}
