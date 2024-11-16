package org.consultorio.medico.service.interfaces;

import org.consultorio.medico.modelo.Paciente;

import java.util.Set;

public interface PacienteService {

    Set<Paciente> allPacientes();
    void guardarPaciente(Paciente paciente);
    Paciente recuperarPaciente(Long pacienteId);
    void clearAll();

}
