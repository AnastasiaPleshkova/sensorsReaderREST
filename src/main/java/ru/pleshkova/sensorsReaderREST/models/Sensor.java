package ru.pleshkova.sensorsReaderREST.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "sensors")
@NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @NotEmpty(message = "can't be empty")
    @Size(min = 3, max = 30, message = "should be between 3 and 30 characters")
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "sensor")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    List<Measurement> measurements;

    public Sensor(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
