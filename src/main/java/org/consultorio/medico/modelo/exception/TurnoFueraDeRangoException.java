package org.consultorio.medico.modelo.exception;

public class TurnoFueraDeRangoException extends RuntimeException {
    public TurnoFueraDeRangoException(String message) {
        super(message);
    }
}
