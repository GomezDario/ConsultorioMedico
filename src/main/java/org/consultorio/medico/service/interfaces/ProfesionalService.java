package org.consultorio.medico.service.interfaces;

import org.consultorio.medico.modelo.Profesional;

import java.util.Set;

public interface ProfesionalService {

    Set<Profesional> allProfesionales();
    void guardarProfesional(Profesional profesional);
    Profesional recuperarProfesional(Long profesionalId);
    void clearAll();

}
