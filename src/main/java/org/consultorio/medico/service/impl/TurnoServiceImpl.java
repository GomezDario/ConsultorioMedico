package org.consultorio.medico.service.impl;

import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.Turno;
import org.consultorio.medico.modelo.exception.TurnoFueraDeRangoException;
import org.consultorio.medico.persistence.TurnoDAO;
import org.consultorio.medico.service.interfaces.TurnoService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class TurnoServiceImpl implements TurnoService {

    private TurnoDAO turnoDAO;

    public TurnoServiceImpl(TurnoDAO turnoDAO){
        this.turnoDAO = turnoDAO;
    }

    @Override
    public Set<Turno> allTurnos() {
        return Set.copyOf(turnoDAO.findAll());
    }

    @Override
    public void guardarTurno(Turno turno) {

        LocalDateTime fechaHora = turno.getFechaHora();

        LocalTime hora = fechaHora.toLocalTime();
        DayOfWeek dia = fechaHora.getDayOfWeek();

        if (hora.isBefore(LocalTime.of(8, 0)) || hora.isAfter(LocalTime.of(23, 0))){
            throw new TurnoFueraDeRangoException("El turno debe estar entre las 8:00 y las 23:00.");
        }

        if(dia == DayOfWeek.SUNDAY){
            throw new TurnoFueraDeRangoException("No se pueden asignar turnos los domingos.");
        }

        turno.getProfesional().agregarTurno(turno);
        turno.getPaciente().agregarTurno(turno);

        turnoDAO.save(turno);

    }

    @Override
    public Turno recuperarTurno(Long turnoId) {
        return turnoDAO.findById(turnoId).orElseThrow(() -> new NoSuchElementException("Turno no encontrado con id: " + turnoId));
    }


    @Override
    public List<Turno> turnosPorProfesional(Profesional profesional) {
        return this.turnoDAO.findByProfesional(profesional.getId());
    }

    @Override
    public List<Turno> turnosPorEspecialidad(Especialidad especialidad) {
        return this.turnoDAO.findByEspecialidad(especialidad.getId());
    }

    @Override
    public List<Turno> turnosPorProfesionalYPorEspecialidad(Profesional profesional, Especialidad especialidad) {
        return this.turnoDAO.findByEspecialidadAndProfesional(especialidad.getId(),profesional.getId());
    }

    @Override
    public void cancelarTurno(Long turnoId) {

        Turno turno = turnoDAO.findById(turnoId).orElseThrow(() ->
                new NoSuchElementException("Turno no encontrado con id: " + turnoId));

        if (LocalDateTime.now().isAfter(turno.getFechaHora().minusHours(1))) {
            throw new IllegalStateException("No se puede cancelar el turno con menos de una hora de anticipacion.");
        }

        turno.camcelar();

        turnoDAO.save(turno);



    }

    @Override
    public void modificarTurno(Long turnoId, LocalDateTime nuevaFechaHora, Profesional nuevoProfesional, int nuevoConsultorio) {

        Turno turno = turnoDAO.findById(turnoId).orElseThrow(() ->
                new NoSuchElementException("Turno no encontrado con id: " + turnoId));


        if (LocalDateTime.now().isAfter(turno.getFechaHora().minusHours(1))) {
            throw new IllegalStateException("No se puede modificar el turno con menos de una hora de anticipacion.");
        }

        turno.setFechaHora(nuevaFechaHora);
        turno.setProfesional(nuevoProfesional);
        turno.setConsultorio(nuevoConsultorio);

        turnoDAO.save(turno);

    }


    @Override
    public void clearAll() {

        turnoDAO.deleteAll();

    }



}
