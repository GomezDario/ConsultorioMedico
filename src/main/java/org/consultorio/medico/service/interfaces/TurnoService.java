package org.consultorio.medico.service.interfaces;

import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.Turno;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TurnoService {

    Set<Turno> allTurnos();
    void guardarTurno(Turno turno);
    Turno recuperarTurno(Long turnoId);
    void clearAll();

    List<Turno> turnosPorProfesional(Profesional profesional);
    List<Turno> turnosPorEspecialidad(Especialidad especialidad);

    List<Turno> turnosPorProfesionalYPorEspecialidad(Profesional profesional, Especialidad especialidad);

    void cancelarTurno(Long turnoId);
    void modificarTurno(Long turnoId, LocalDateTime nuevaFechaHora, Profesional nuevoProfesional, int nuevoConsultorio);


}
