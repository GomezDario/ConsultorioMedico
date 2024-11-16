package org.consultorio.medico.service.impl;

import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.exception.CantidadProfesionalesPorEspecialidadException;
import org.consultorio.medico.persistence.EspecialidadDAO;
import org.consultorio.medico.service.interfaces.EspecialidadService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    private EspecialidadDAO especialidadDao;

    public EspecialidadServiceImpl(EspecialidadDAO especialidadDao){
        this.especialidadDao = especialidadDao;
    }



    @Override
    public Set<Especialidad> allEspecialidad() {
        return Set.copyOf(especialidadDao.findAll());
    }

    @Override
    public void guardarEspecialidad(Especialidad especialidad) {

        Set<Profesional> profesionales = especialidad.getProfesionales();

        if (profesionales.size() < 4){
            throw new CantidadProfesionalesPorEspecialidadException("La cantidad de profesionales por especialidad debe ser como minimo 4, ahora la cantidad es: " + profesionales.size());
        }

        for (Profesional profesional : profesionales){
            profesional.setEspecialidad(especialidad);
        }

        especialidadDao.save(especialidad);


    }

    @Override
    public Especialidad recuperarEspecialidad(Long especialidadId) {
        return especialidadDao.findById(especialidadId).orElseThrow(() -> new NoSuchElementException("Especialidad no encontrada con id: " + especialidadId));
    }


    @Override
    public void clearAll() {
        especialidadDao.deleteAll();

    }
}
