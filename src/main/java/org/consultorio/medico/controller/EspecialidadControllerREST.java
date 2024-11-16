package org.consultorio.medico.controller;

import org.consultorio.medico.controller.dto.EspecialidadDTO;
import org.consultorio.medico.controller.dto.EspecialidadRequestDTO;
import org.consultorio.medico.controller.dto.ProfesionalDTO;
import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.service.interfaces.EspecialidadService;
import org.consultorio.medico.service.interfaces.ProfesionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/especialidad")
public class EspecialidadControllerREST {

    private final EspecialidadService especialidadService;
    private final ProfesionalService profesionalService;

    public EspecialidadControllerREST(EspecialidadService especialidadService, ProfesionalService profesionalService){
        this.especialidadService = especialidadService;
        this.profesionalService = profesionalService;
    }

    @GetMapping("/all")
    public Set<EspecialidadDTO> getAllEspecialidades() {
        return especialidadService.allEspecialidad().stream()
                .map(EspecialidadDTO::desdeModelo)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> getEspecialidadById(@PathVariable Long id) {
        Especialidad especialidad = especialidadService.recuperarEspecialidad(id);
        return ResponseEntity.ok(EspecialidadDTO.desdeModelo(especialidad));
    }


    @PostMapping
    public void createEspecialidad(@RequestBody EspecialidadRequestDTO especialidadRequestDTO) {
        // Convertir especialidadDTO a una instancia de Especialidad
        Especialidad especialidad = especialidadRequestDTO.especialidadDTO().aModelo();

        // Guardar cada profesional asociado a la especialidad
        for (ProfesionalDTO profesionalDTO : especialidadRequestDTO.profesionalDTOS()) {
            especialidad.agregarProfesional(profesionalDTO.aModelo(especialidad));
        }

        // Guardar la especialidad sin asignar su retorno
        especialidadService.guardarEspecialidad(especialidad);

    }


}
