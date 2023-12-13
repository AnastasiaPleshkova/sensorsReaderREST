package ru.pleshkova.sensorsReaderREST.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.pleshkova.sensorsReaderREST.models.Measurement;

@Component
public class MeasurementValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

    }
}
