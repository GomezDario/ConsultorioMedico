package org.consultorio.medico.controller;

import org.consultorio.medico.controller.dto.ProfesionalDTO;
import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.service.interfaces.EspecialidadService;
import org.consultorio.medico.service.interfaces.ProfesionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/profesional")
public class ProfesionalControllerREST {

    private final ProfesionalService profesionalService;
    private final EspecialidadService especialidadService;

    public ProfesionalControllerREST(ProfesionalService profesionalService, EspecialidadService especialidadService){
        this.profesionalService = profesionalService;
        this.especialidadService = especialidadService;

    }

    @GetMapping("/all")
    public Set<ProfesionalDTO> getAllProfesionales() {
        return profesionalService.allProfesionales().stream()
                .map(ProfesionalDTO::desdeModelo)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> getProfesionalById(@PathVariable Long id) {
        Profesional profesional = profesionalService.recuperarProfesional(id);
        return ResponseEntity.ok(ProfesionalDTO.desdeModelo(profesional));
    }


    @PostMapping
    public void createProfesional(@RequestBody ProfesionalDTO profesionalDTO) {
        Especialidad especialidad = especialidadService.recuperarEspecialidad(profesionalDTO.especialidadId());
        profesionalService.guardarProfesional(profesionalDTO.aModelo(especialidad));
    }


}
