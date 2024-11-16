package org.consultorio.medico;

import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Paciente;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.Turno;
import org.consultorio.medico.service.interfaces.EspecialidadService;
import org.consultorio.medico.service.interfaces.PacienteService;
import org.consultorio.medico.service.interfaces.ProfesionalService;
import org.consultorio.medico.service.interfaces.TurnoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProfesionalServiceTest {

    @Autowired
    private ProfesionalService profesionalService;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EspecialidadService especialidadService;


    private Especialidad dermatologia;

    private Profesional profesional1;
    private Profesional profesional2;
    private Profesional profesional3;
    private Profesional profesional4;

    private Turno turno;

    private Paciente paciente1;


    @BeforeEach
    public void prepare() {
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(16, 0);

        dermatologia = new Especialidad("DERMATOLOGIA");



        profesional1 = new Profesional("Juan", dermatologia,horaInicio, horaFin);
        profesional2 = new Profesional("Dario", dermatologia, horaInicio, horaFin);
        profesional3 = new Profesional("Gabriel", dermatologia, horaInicio, horaFin);
        profesional4 = new Profesional("Franco", dermatologia, horaInicio, horaFin);

        dermatologia.agregarProfesional(profesional1);
        dermatologia.agregarProfesional(profesional2);
        dermatologia.agregarProfesional(profesional3);
        dermatologia.agregarProfesional(profesional4);

        paciente1 = new Paciente("Dario");

        turno = new Turno(LocalDateTime.now(),profesional1,paciente1,1);


    }

    @Test
    void testGuardarProfesional(){

        especialidadService.guardarEspecialidad(dermatologia);
        profesionalService.guardarProfesional(profesional1);
        pacienteService.guardarPaciente(paciente1);

        turnoService.guardarTurno(turno);

        Profesional profesionalRecuperado = profesionalService.recuperarProfesional(profesional1.getId());
        assertEquals("Juan", profesionalRecuperado.getNombre());

        assertEquals(profesional1.getTurnos().size(),1);
        assertEquals(paciente1.getTurnos().size(),1);

    }

    @AfterEach
    void tearDown(){
        profesionalService.clearAll();
    }

}
