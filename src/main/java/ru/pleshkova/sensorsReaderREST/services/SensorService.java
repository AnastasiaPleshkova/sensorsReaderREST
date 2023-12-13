package ru.pleshkova.sensorsReaderREST.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pleshkova.sensorsReaderREST.models.Sensor;
import ru.pleshkova.sensorsReaderREST.repositories.SensorRepository;

import java.util.List;


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
        return sensorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
