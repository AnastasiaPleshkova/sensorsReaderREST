package ru.pleshkova.sensorsReaderREST.util;

public class NotFoundSensorException extends RuntimeException {
    public NotFoundSensorException() {
        super("Not found registered sensor. Please register the sensor or check name");
    }
}
