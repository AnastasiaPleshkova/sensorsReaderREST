package ru.pleshkova.sensorsReaderREST.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MeasurementDto {

    @Min(value = -100, message = "can't be less than -100")
    @Max(value = 100, message = "can't be more than 100")
    @NotNull(message = "can't be empty")
    private Integer value;

    @NotNull(message = "can't be empty")
    private Boolean raining;
    @NotNull(message = "can't be empty")
    private SensorDto sensor;

}
