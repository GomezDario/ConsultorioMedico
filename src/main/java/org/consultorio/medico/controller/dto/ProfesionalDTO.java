package org.consultorio.medico.controller.dto;

import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Profesional;

import java.time.LocalTime;

public record ProfesionalDTO(Long id, String nombre, Long especialidadId, LocalTime horarioInicio, LocalTime horarioFin) {

    public static ProfesionalDTO desdeModelo(Profesional profesional) {
        return new ProfesionalDTO(
                profesional.getId(),
                profesional.getNombre(),
                profesional.getEspecialidad().getId(),
                profesional.getHorarioInicio(),
                profesional.getHorarioFin()
        );
    }

    public Profesional aModelo(Especialidad especialidad) {
        Profesional profesional = new Profesional(this.nombre, especialidad, this.horarioInicio, this.horarioFin);
        profesional.setId(this.id);
        return profesional;
    }
}
