package ru.pleshkova.sensorsReaderREST.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.pleshkova.sensorsReaderREST.dto.SensorDto;
import ru.pleshkova.sensorsReaderREST.services.SensorService;

@Component
public class SensorDtoValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorDtoValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDto sensor = (SensorDto) target;
        if (sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}
