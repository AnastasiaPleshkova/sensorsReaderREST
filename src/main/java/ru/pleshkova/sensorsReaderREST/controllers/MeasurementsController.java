package ru.pleshkova.sensorsReaderREST.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.sensorsReaderREST.dto.MeasurementDto;
import ru.pleshkova.sensorsReaderREST.dto.SensorDto;
import ru.pleshkova.sensorsReaderREST.models.Measurement;
import ru.pleshkova.sensorsReaderREST.models.Sensor;
import ru.pleshkova.sensorsReaderREST.services.MeasurementService;
import ru.pleshkova.sensorsReaderREST.services.SensorService;
import ru.pleshkova.sensorsReaderREST.util.MeasurementNotCreatedException;
import ru.pleshkova.sensorsReaderREST.util.ErrorResponse;
import ru.pleshkova.sensorsReaderREST.util.NotFoundSensorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    private final ModelMapper mapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, SensorService sensorService, ModelMapper mapper) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
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
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new MeasurementNotCreatedException(sb.toString());
        }
        measurementService.save(convertToMeasurement(measurementDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private MeasurementDto convertToMeasurementDto(Measurement measurement) {
        MeasurementDto measurementDto = mapper.map(measurement, MeasurementDto.class);
        measurementDto.setSensor(mapper.map(measurement.getSensor(), SensorDto.class));
        return measurementDto;
    }

    private Measurement convertToMeasurement(MeasurementDto measurementDto) {
        Measurement measurement = mapper.map(measurementDto, Measurement.class);
        measurement.setSensor(sensorService.findByName(measurementDto.getSensor().getName()).orElseThrow(NotFoundSensorException::new));
        return measurement;
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException (MeasurementNotCreatedException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException (NotFoundSensorException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
