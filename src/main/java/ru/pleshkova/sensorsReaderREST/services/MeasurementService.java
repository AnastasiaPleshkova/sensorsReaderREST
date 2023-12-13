package ru.pleshkova.sensorsReaderREST.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pleshkova.sensorsReaderREST.models.Measurement;
import ru.pleshkova.sensorsReaderREST.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
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
        // TO DO
        measurementRepository.save(measurement);
    }

}
