package org.consultorio.medico.controller;

import org.consultorio.medico.controller.dto.TurnoDTO;
import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Paciente;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.Turno;
import org.consultorio.medico.service.interfaces.EspecialidadService;
import org.consultorio.medico.service.interfaces.PacienteService;
import org.consultorio.medico.service.interfaces.ProfesionalService;
import org.consultorio.medico.service.interfaces.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/turno")
public class TurnoControllerREST {

    private final TurnoService turnoService;
    private final ProfesionalService profesionalService;
    private final PacienteService pacienteService;
    private final EspecialidadService especialidadService;

    public TurnoControllerREST(TurnoService turnoService, ProfesionalService profesionalService, PacienteService pacienteService, EspecialidadService especialidadService){
        this.turnoService = turnoService;
        this.profesionalService = profesionalService;
        this.pacienteService = pacienteService;
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public Set<TurnoDTO> getAllTurnos() {
        return turnoService.allTurnos().stream()
                .map(TurnoDTO::desdeModelo)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> getTurnoById(@PathVariable Long id) {
        Turno turno = turnoService.recuperarTurno(id);
        return ResponseEntity.ok(TurnoDTO.desdeModelo(turno));
    }


    @PostMapping
    public void createTurno(@RequestBody TurnoDTO turnoDTO) {
        Profesional profesional = profesionalService.recuperarProfesional(turnoDTO.profesionalId());
        Paciente paciente = pacienteService.recuperarPaciente(turnoDTO.pacienteId());
        Especialidad especialidad = especialidadService.recuperarEspecialidad(turnoDTO.especialidadId());

        turnoService.guardarTurno(turnoDTO.aModelo(profesional,paciente,especialidad));


    }

    @PutMapping("/{id}")
    public void modificarTurno(@PathVariable Long id,
                               @RequestBody TurnoDTO turnoDTO) {

        Profesional profesional = profesionalService.recuperarProfesional(turnoDTO.profesionalId());

        turnoService.modificarTurno(id, turnoDTO.fechaHora(),
                profesional, turnoDTO.consultorio());
    }

    @GetMapping("/profesional/{id}")
    public Set<TurnoDTO> getTurnosPorProfesional(@PathVariable Long id) {

        Profesional profesionalId = profesionalService.recuperarProfesional(id);

        return turnoService.turnosPorProfesional(profesionalId).stream()
                .map(TurnoDTO::desdeModelo)
                .collect(Collectors.toSet());
    }


    @GetMapping("/especialidad/{id}")
    public Set<TurnoDTO> getTurnosPorEspecialidad(@PathVariable Long id) {

        Especialidad especialidadId = especialidadService.recuperarEspecialidad(id);

        return turnoService.turnosPorEspecialidad(especialidadId).stream()
                .map(TurnoDTO::desdeModelo)
                .collect(Collectors.toSet());
    }

    @GetMapping("/profesional/{profId}/especialidad/{espId}")
    public Set<TurnoDTO> getTurnosPorProfesionalYEspecialidad(@PathVariable Long profId, @PathVariable Long espId) {

        Profesional profesionalId = profesionalService.recuperarProfesional(profId);

        Especialidad especialidadId = especialidadService.recuperarEspecialidad(espId);

        return turnoService.turnosPorProfesionalYPorEspecialidad(profesionalId, especialidadId).stream()
                .map(TurnoDTO::desdeModelo)
                .collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
    public void cancelarTurno(@PathVariable Long id) {
        turnoService.cancelarTurno(id);
    }




}
