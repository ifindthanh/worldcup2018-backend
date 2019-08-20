package vn.com.smvn;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import vn.com.smvn.bean.XUserAgentInterceptor;

@SpringBootApplication
@EnableScheduling
public class SMVNWorldcup2018Application {

	public static void main(String[] args) {
		SpringApplication.run(SMVNWorldcup2018Application.class, args);
	}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        RestTemplate build = builder.build();
        ClientHttpRequestInterceptor interceptor = new XUserAgentInterceptor();
        build.setInterceptors(Collections.singletonList(interceptor));
        return build;
    }

}
