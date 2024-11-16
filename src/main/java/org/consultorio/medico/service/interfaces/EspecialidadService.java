package org.consultorio.medico.service.interfaces;

import org.consultorio.medico.modelo.Especialidad;

import java.util.Set;

public interface EspecialidadService {

    Set<Especialidad> allEspecialidad();
    void guardarEspecialidad(Especialidad especialidad);
    Especialidad recuperarEspecialidad(Long especialidadId);
    void clearAll();

}
