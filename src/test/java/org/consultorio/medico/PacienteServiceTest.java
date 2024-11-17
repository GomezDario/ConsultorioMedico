package org.consultorio.medico;

import org.consultorio.medico.modelo.Paciente;
import org.consultorio.medico.service.interfaces.PacienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    private Paciente paciente1;

    private Paciente paciente2;

    private Paciente paciente3;

    private Paciente paciente4;


    @BeforeEach
    public void prepare() {

        paciente1 = new Paciente("Dario", "Gomez", 24,111222333);
        paciente2 = new Paciente("Gabriel", "Gomez", 24,222333444);
        paciente3 = new Paciente("Juan","Perez", 38, 222111333);
        paciente4 = new Paciente("Jose","Lopez",19,666444222);

        pacienteService.guardarPaciente(paciente1);
        pacienteService.guardarPaciente(paciente2);
        pacienteService.guardarPaciente(paciente3);
        pacienteService.guardarPaciente(paciente4);

    }

    @Test
    void testRecuperarTodosLosPacientes(){

        Set<Paciente> pacientes = pacienteService.allPacientes();

        assertEquals(pacientes.size(),4);

    }

    @Test
    void testRecuperarPaciente(){

        Paciente pacienteRecuperado = pacienteService.recuperarPaciente(paciente1.getId());

        assertEquals(pacienteRecuperado.getId(), paciente1.getId());


    }

    @AfterEach
    void tearDown(){
        pacienteService.clearAll();
    }






}

