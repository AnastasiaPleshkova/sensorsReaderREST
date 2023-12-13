package ru.pleshkova.sensorsReaderREST.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MeasurementDto {

    @Min(value = -100, message = "can't be less than -100")
    @Max(value = 100, message = "can't be more than 100")
    private Integer value;

    private Boolean raining;

    private SensorDto sensor;
}
