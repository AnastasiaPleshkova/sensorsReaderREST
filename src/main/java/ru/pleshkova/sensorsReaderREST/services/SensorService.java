package ru.pleshkova.sensorsReaderREST.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pleshkova.sensorsReaderREST.models.Sensor;
import ru.pleshkova.sensorsReaderREST.repositories.SensorRepository;
import ru.pleshkova.sensorsReaderREST.util.NotFoundSensorException;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findById(long id){
        return sensorRepository.findById(id).orElseThrow(NotFoundSensorException::new);
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
