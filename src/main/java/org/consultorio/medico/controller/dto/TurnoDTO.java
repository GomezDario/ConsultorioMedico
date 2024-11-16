package org.consultorio.medico.controller.dto;

import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Paciente;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.Turno;

import java.time.LocalDateTime;

public record TurnoDTO(Long id, LocalDateTime fechaHora, Long profesionalId, Long pacienteId, Long especialidadId, int consultorio, int cancelacion) {

    public static TurnoDTO desdeModelo(Turno turno) {
        return new TurnoDTO(
                turno.getId(),
                turno.getFechaHora(),
                turno.getProfesional().getId(),
                turno.getPaciente().getId(),
                turno.getEspecialidad().getId(),
                turno.getConsultorio(),
                turno.getCancelacion()
        );
    }

    public Turno aModelo(Profesional profesional, Paciente paciente, Especialidad especialidad) {
        Turno turno = new Turno(this.fechaHora, profesional, paciente, this.consultorio);
        turno.setId(this.id);
        turno.setEspecialidad(especialidad);
        turno.setCancelacion(this.cancelacion);
        return turno;
    }
}
