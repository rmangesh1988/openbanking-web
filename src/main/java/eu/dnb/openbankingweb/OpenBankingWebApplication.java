package eu.dnb.openbankingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@PropertySource("classpath:environment.properties")
public class OpenBankingWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenBankingWebApplication.class, args);
    }
}
