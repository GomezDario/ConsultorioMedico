package org.consultorio.medico.controller;

import org.consultorio.medico.controller.dto.PacienteDTO;
import org.consultorio.medico.modelo.Paciente;
import org.consultorio.medico.service.interfaces.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/paciente")
public class PacienteControllerREST {

    private final PacienteService pacienteService;

    public PacienteControllerREST(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }


    @GetMapping("/all")
    public Set<PacienteDTO> allPacientes(){
        return pacienteService.allPacientes().stream()
                .map(PacienteDTO::desdeModelo)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        Paciente paciente = pacienteService.recuperarPaciente(id);
        return ResponseEntity.ok(PacienteDTO.desdeModelo(paciente));
    }


    @PostMapping
    public void createPaciente(@RequestBody PacienteDTO paciente){
        pacienteService.guardarPaciente(paciente.aModelo());
    }


}
