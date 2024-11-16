package org.consultorio.medico.modelo;

import java.time.LocalDateTime;

public interface ClockProvider {

    LocalDateTime now();

}
