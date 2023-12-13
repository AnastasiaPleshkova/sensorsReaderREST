package ru.pleshkova.sensorsReaderREST.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.sensorsReaderREST.dto.SensorDto;
import ru.pleshkova.sensorsReaderREST.models.Sensor;
import ru.pleshkova.sensorsReaderREST.services.SensorService;
import ru.pleshkova.sensorsReaderREST.util.ErrorResponse;
import ru.pleshkova.sensorsReaderREST.util.SensorNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorService sensorService;
    private final ModelMapper mapper;
    @Autowired
    public SensorsController(SensorService sensorService, ModelMapper mapper) {
        this.sensorService = sensorService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Sensor> findAll() {
        return sensorService.findAll();
    }

    @GetMapping("/{id}")
    public Sensor findById(@PathVariable("id") long id) {
        return sensorService.findById(id);
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDto sensorDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new SensorNotCreatedException(sb.toString());
        }
        // TODO NEED TO FIX, SHOULD BE UNIQUE NAME
        sensorService.save(convertToSensor(sensorDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private SensorDto convertToSensorDto(Sensor sensor) {
        return mapper.map(sensor, SensorDto.class);
    }

    private Sensor convertToSensor(SensorDto sensorDto) {
        return mapper.map(sensorDto, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException (SensorNotCreatedException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
