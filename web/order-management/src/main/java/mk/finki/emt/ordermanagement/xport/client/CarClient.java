package mk.finki.emt.ordermanagement.xport.client;

import mk.finki.emt.ordermanagement.domain.valueobjects.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class CarClient {

    private final RestTemplate restTemplate;

    private final String serverUrl;

    public CarClient(@Value("${app.retail-a-car.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Car> findAll() {
        try {
            return restTemplate.exchange(uri().
                    path("/api/car").build().toUri(),
                    HttpMethod.GET,null,
                    new ParameterizedTypeReference<List<Car>>() {
            }).getBody();
        } catch (Exception exception) {
            return Collections.emptyList();
        }
    }
}
