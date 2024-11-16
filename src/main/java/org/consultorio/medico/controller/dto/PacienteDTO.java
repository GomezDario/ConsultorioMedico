package org.consultorio.medico.controller.dto;

import org.consultorio.medico.modelo.Paciente;

public record PacienteDTO(Long id, String nombre) {

    public static PacienteDTO desdeModelo(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNombre()
        );
    }

    public Paciente aModelo() {
        Paciente paciente = new Paciente(this.nombre);
        paciente.setId(this.id);
        return paciente;
    }
}
