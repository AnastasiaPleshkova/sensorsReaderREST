package ru.pleshkova.sensorsReaderREST.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDto {
    @NotEmpty(message = "can't be empty")
    @Size(min = 2, max = 30, message = "should be between 2 and 30 characters")
    private String name;

}
