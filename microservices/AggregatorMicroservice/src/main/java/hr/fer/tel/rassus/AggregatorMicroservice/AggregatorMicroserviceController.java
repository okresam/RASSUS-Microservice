package hr.fer.tel.rassus.AggregatorMicroservice;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RefreshScope
@RestController
public class AggregatorMicroserviceController {

    @Value("${tempUnit}")
    private String tempUnit;

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient;

    @GetMapping("/get-aggregated")
    public ResponseEntity<?> getAggregatedReadings() {
        List<SensorReading> result = new ArrayList<>();

        try {
            ResponseEntity<SensorReading> tempReadingRE = restTemplate.getForEntity("http://temperature-microservice/read-temperature", SensorReading.class);
            ResponseEntity<SensorReading> humReadingRE = restTemplate.getForEntity("http://humidity-microservice/read-humidity", SensorReading.class);

            SensorReading tempReading = tempReadingRE.getBody();
            SensorReading humReading = humReadingRE.getBody();

            if (tempUnit.equals("K")) {
                tempReading.setUnit("K");
                tempReading.setValue(tempReading.getValue() + 273);
            }

            result.add(tempReading);
            result.add(humReading);
        } catch(IllegalStateException e) {
            return new ResponseEntity(null, HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
