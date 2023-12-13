package ru.pleshkova.sensorsReaderREST.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pleshkova.sensorsReaderREST.models.Measurement;
import ru.pleshkova.sensorsReaderREST.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByName(String name);
}
