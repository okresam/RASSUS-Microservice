package hr.fer.tel.rassus.HumidityMicroservice;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HumidityMicroserviceController {

    @Autowired
    SensorReadingRepository repository;

    @GetMapping("/read-humidity")
    public ResponseEntity<SensorReading> getReading() {

        String[] headers = { "Temperature", "Pressure", "Humidity", "CO", "NO2", "SO2" };
        Iterable<CSVRecord> readings = null;
        List<CSVRecord> allReadings = new ArrayList<>();
        try {
            ClassLoader cl = getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("readings.csv");
            //Reader in = new FileReader("readings.csv");
            Reader in = new InputStreamReader(is);
            readings = CSVFormat.RFC4180.builder().setHeader(headers).setSkipHeaderRecord(true).build().parse(in);
            for(CSVRecord record : readings)
                allReadings.add(record);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int readingLine = Long.valueOf((System.currentTimeMillis() % 100) + 1).intValue();

        CSVRecord currReading = allReadings.get(readingLine);
        SensorReading reading = new SensorReading("Humidity", "%", Integer.parseInt(currReading.get("Humidity")));

        repository.save(reading);

        return new ResponseEntity<SensorReading>(reading, HttpStatus.OK);
    }
}
