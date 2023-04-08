package hr.fer.tel.rassus.TemperatureMicroservice;

import jakarta.persistence.*;

@Entity
@Table(name = "temperature_reading")
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    private String name;

    private String unit;

    @Column(name="\"value\"")
    private int value;

    public SensorReading() {

    }

    public SensorReading(String name, String unit, int value) {
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
