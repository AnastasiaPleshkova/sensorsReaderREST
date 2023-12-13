package ru.pleshkova.sensorsReaderREST.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MeasurementDto {

    @Min(value = -100, message = "shouldn't be less than -100")
    @Max(value = 100, message = "should be less than 100")
    @NotEmpty(message = "can't be empty")
    @Column(name = "temperature")
    private Integer value;

    @NotEmpty(message = "can't be empty")
    @Column(name = "isRaining")
    private Boolean raining;
}
