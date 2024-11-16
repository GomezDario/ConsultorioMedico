package org.consultorio.medico.persistence;

import org.consultorio.medico.modelo.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoDAO extends JpaRepository<Turno, Long> {

    @Query("SELECT t FROM Turno t WHERE t.profesional.id = :profesionalId")
    List<Turno> findByProfesional(@Param("profesionalId") Long profesionalId);

    @Query("SELECT t FROM Turno t WHERE t.especialidad.id = :especialidadId")
    List<Turno> findByEspecialidad(@Param("especialidadId") Long especialidad);

    // Listado de turnos por especialidad y por profesional
    @Query("SELECT t FROM Turno t WHERE t.especialidad.id = :especialidad AND t.profesional.id = :profesionalId")
    List<Turno> findByEspecialidadAndProfesional(@Param("especialidad") Long especialidad,
                                                 @Param("profesionalId") Long profesionalId);


}
