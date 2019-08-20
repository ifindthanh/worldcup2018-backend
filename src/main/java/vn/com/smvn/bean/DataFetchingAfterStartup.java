package vn.com.smvn.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import vn.com.smvn.service.DataFetchingService;

@Component
public class DataFetchingAfterStartup {
    @Autowired
    private DataFetchingService dataFetchingService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        this.dataFetchingService.fetchTeamInfo();
    }
}
