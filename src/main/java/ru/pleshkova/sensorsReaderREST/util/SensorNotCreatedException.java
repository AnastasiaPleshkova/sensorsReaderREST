package ru.pleshkova.sensorsReaderREST.util;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
