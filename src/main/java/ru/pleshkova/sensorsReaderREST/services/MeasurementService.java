package ru.pleshkova.sensorsReaderREST.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pleshkova.sensorsReaderREST.models.Measurement;
import ru.pleshkova.sensorsReaderREST.repositories.MeasurementRepository;
import ru.pleshkova.sensorsReaderREST.repositories.SensorRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }


    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Measurement findById(long id) {
        return measurementRepository.findById(id).orElse(null);
    }

    public Long getRainyDaysCount() {
        return (long) measurementRepository.findByRainingTrue().size();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setTime(new Date());
        measurementRepository.save(measurement);
    }



}
