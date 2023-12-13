package ru.pleshkova.sensorsReaderREST.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.sensorsReaderREST.dto.MeasurementDto;
import ru.pleshkova.sensorsReaderREST.models.Measurement;
import ru.pleshkova.sensorsReaderREST.services.MeasurementService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final ModelMapper mapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, ModelMapper mapper) {
        this.measurementService = measurementService;
        this.mapper = mapper;
    }
    @GetMapping
    public List<MeasurementDto> getAll() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDto).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Long rainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDto measurementDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // TO DO

        }
        // TO DO CHECK SENCOR REGISTATION
        measurementService.save(convertToMeasurement(measurementDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private MeasurementDto convertToMeasurementDto(Measurement measurement) {
        return mapper.map(measurement, MeasurementDto.class);
    }

    private Measurement convertToMeasurement(MeasurementDto measurementDto) {
        return mapper.map(measurementDto, Measurement.class);
    }
}
