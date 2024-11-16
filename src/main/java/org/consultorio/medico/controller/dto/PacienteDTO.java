package org.consultorio.medico.controller.dto;

import org.consultorio.medico.modelo.Paciente;

public record PacienteDTO(Long id, String nombre, String apellido, int edad, int numeroDni) {

    public static PacienteDTO desdeModelo(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getEdad(),
                paciente.getNumeroDni()
        );
    }

    public Paciente aModelo() {
        Paciente paciente = new Paciente(this.nombre, this.apellido, this.edad, this.numeroDni);
        paciente.setId(this.id);
        return paciente;
    }
}
