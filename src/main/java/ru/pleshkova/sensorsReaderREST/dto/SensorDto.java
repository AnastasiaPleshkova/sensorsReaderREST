package ru.pleshkova.sensorsReaderREST.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDto {

    @Size(min = 3, max = 30, message = "should be between 3 and 30 characters")
    private String name;

}
