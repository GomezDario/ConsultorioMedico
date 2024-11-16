package org.consultorio.medico.modelo;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SystemClockProvider implements ClockProvider{
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
