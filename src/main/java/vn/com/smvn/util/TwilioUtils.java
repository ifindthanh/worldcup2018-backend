package vn.com.smvn.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import vn.com.smvn.bean.ScheduledTasks;

public class TwilioUtils {

    private static final String ACCOUNT_SID = "ACd89bb19316636517e24ef1cf95c22b18";
    private static final String AUTH_TOKEN = "d5e683e9f7b0362070bb82426e578d54";
    private static final Logger log = LogManager.getLogger(ScheduledTasks.class);

    public static void sendNotification(String homeTeam, String awayTeam, String time, List<String> receivers) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        for (String receiver : receivers) {
            Message message = Message
                    .creator(new PhoneNumber(receiver), new PhoneNumber("+14697891913"), "Tran dau giua "
                            + homeTeam + " - " + awayTeam + " se dien ra luc " + time + ". Anh em vao dat keo nhanh!!!")
                    .create();
            log.info("Sent SMS to " + receiver + " the MSG id is " + message.getSid());
        }
    }
}
