package vn.com.smvn.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ElasticPutDate {
    @Autowired
    private RestTemplate restTemplate;

}
