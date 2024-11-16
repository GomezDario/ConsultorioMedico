package org.consultorio.medico.service.impl;

import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.exception.DniDeProfesionalRepetido;
import org.consultorio.medico.persistence.ProfesionalDAO;
import org.consultorio.medico.service.interfaces.ProfesionalService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {

    private ProfesionalDAO profesionalDAO;

    public ProfesionalServiceImpl(ProfesionalDAO profesionalDAO) {
        this.profesionalDAO = profesionalDAO;
    }

    @Override
    public Set<Profesional> allProfesionales() {
        return Set.copyOf(profesionalDAO.findAll());
    }

    @Override
    public void guardarProfesional(Profesional profesional) {


        try {
            profesionalDAO.save(profesional);
        }catch (DataIntegrityViolationException e){
            throw new DniDeProfesionalRepetido("El numero de dni: " + profesional.getNumeroDNI() + " se encuetra repetido");
        }

    }

    @Override
    public Profesional recuperarProfesional(Long profesionalId) {
        return profesionalDAO.findById(profesionalId).orElseThrow(() -> new NoSuchElementException("Profesional no encontrado con id: " + profesionalId));
    }

    @Override
    public void clearAll() {
        profesionalDAO.deleteAll();
    }
}
