package org.consultorio.medico.service.impl;

import org.consultorio.medico.modelo.Paciente;
import org.consultorio.medico.persistence.PacienteDAO;
import org.consultorio.medico.service.interfaces.PacienteService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteDAO pacienteDAO;

    public PacienteServiceImpl(PacienteDAO pacienteDAO){
        this.pacienteDAO = pacienteDAO;
    }

    @Override
    public Set<Paciente> allPacientes() {
        return Set.copyOf(pacienteDAO.findAll());
    }

    @Override
    public void guardarPaciente(Paciente paciente) {
        pacienteDAO.save(paciente);

    }

    @Override
    public Paciente recuperarPaciente(Long pacienteId) {
        return pacienteDAO.findById(pacienteId).orElseThrow(() -> new NoSuchElementException("Paciente no encontrado con id: " + pacienteId));
    }

    @Override
    public void clearAll() {
        pacienteDAO.deleteAll();
    }
}
