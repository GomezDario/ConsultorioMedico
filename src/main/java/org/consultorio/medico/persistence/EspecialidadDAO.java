package org.consultorio.medico.persistence;

import org.consultorio.medico.modelo.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadDAO extends JpaRepository<Especialidad, Long> {
}
