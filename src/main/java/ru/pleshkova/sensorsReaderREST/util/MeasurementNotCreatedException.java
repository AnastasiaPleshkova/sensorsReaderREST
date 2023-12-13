package ru.pleshkova.sensorsReaderREST.util;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String msg) {
        super(msg);
    }
}
