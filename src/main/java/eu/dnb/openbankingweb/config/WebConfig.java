package eu.dnb.openbankingweb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.dnb.openbanking.client.OpenBankingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by rmang on 17-03-2018.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public RestTemplate restTemplate(@Value("${api.user.name}") String username, @Value("${api.user.password}") String password) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new BasicAuthorizationInterceptor(username, password);
        restTemplate.getInterceptors().add(clientHttpRequestInterceptor);
        return restTemplate;
    }

    @Bean
    public OpenBankingService openBankingService(@Value("${openbanking.service.url}") String baseUrl,
                                                 RestTemplate restTemplate) {
        return new OpenBankingService(baseUrl, restTemplate);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
