package ru.pleshkova.sensorsReaderREST.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "measurements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {   // TODO ADDED VALIDATION
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(value = -100, message = "can't be less than -100")
    @Max(value = 100, message = "can't be more than 100")
    @Column(name = "temperature")
    private Integer value;   // TODO SHOULD BE DOUBLE


    @Column(name = "raining")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    private Date time;

}
