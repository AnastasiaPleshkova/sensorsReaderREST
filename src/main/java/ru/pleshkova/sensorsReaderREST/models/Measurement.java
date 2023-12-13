package ru.pleshkova.sensorsReaderREST.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "measurements")
@Data
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(value = -100, message = "shouldn't be less than -100")
    @Max(value = 100, message = "should be less than 100")
    @NotEmpty(message = "can't be empty")
    @Column(name = "temperature")
    private Integer value;

    @NotEmpty(message = "can't be empty")
    @Column(name = "raining")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

}
