package org.consultorio.medico.controller.dto;

import org.consultorio.medico.modelo.Especialidad;

public record EspecialidadDTO(Long id, String nombre) {

    public static EspecialidadDTO desdeModelo(Especialidad especialidad) {
        return new EspecialidadDTO(
                especialidad.getId(),
                especialidad.getNombre()
        );
    }

    public Especialidad aModelo() {
        Especialidad especialidad = new Especialidad(this.nombre);
        especialidad.setId(this.id);
        return especialidad;
    }
}
