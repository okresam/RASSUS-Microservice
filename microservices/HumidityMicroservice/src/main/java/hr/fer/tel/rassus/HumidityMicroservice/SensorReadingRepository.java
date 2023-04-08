package hr.fer.tel.rassus.HumidityMicroservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

}
