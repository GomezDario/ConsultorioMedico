package org.consultorio.medico.controller.dto;

import java.util.Set;

public record EspecialidadRequestDTO(EspecialidadDTO especialidadDTO, Set<ProfesionalDTO> profesionalDTOS) {



}

