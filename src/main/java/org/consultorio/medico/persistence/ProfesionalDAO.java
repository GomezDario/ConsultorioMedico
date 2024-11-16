package org.consultorio.medico.persistence;

import org.consultorio.medico.modelo.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalDAO extends JpaRepository<Profesional, Long> {
}
