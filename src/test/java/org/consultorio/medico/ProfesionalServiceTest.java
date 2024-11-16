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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


        profesional1 = new Profesional("Juan", dermatologia,horaInicio, horaFin,11222333);
        profesional2 = new Profesional("Dario", dermatologia, horaInicio, horaFin,222111333);
        profesional3 = new Profesional("Gabriel", dermatologia, horaInicio, horaFin,444555666);
        profesional4 = new Profesional("Franco", dermatologia, horaInicio, horaFin,777111222);



        dermatologia.agregarProfesional(profesional1);
        dermatologia.agregarProfesional(profesional2);
        dermatologia.agregarProfesional(profesional3);
        dermatologia.agregarProfesional(profesional4);

        especialidadService.guardarEspecialidad(dermatologia);


        profesionalService.guardarProfesional(profesional1);
        profesionalService.guardarProfesional(profesional2);
        profesionalService.guardarProfesional(profesional3);
        profesionalService.guardarProfesional(profesional4);


        paciente1 = new Paciente("Dario");
        pacienteService.guardarPaciente(paciente1);

        turno = new Turno(LocalDateTime.of(2024, 11, 15, 14, 30),profesional1,paciente1,1);
        turnoService.guardarTurno(turno);

    }

    @Test
    void testRecuperarProfesional(){

        Profesional profesionalRecuperado = profesionalService.recuperarProfesional(profesional1.getId());
        assertEquals("Juan", profesionalRecuperado.getNombre());

        assertEquals(profesional1.getTurnos().size(),1);
        assertEquals(paciente1.getTurnos().size(),1);

    }

    @Test
    void testRecuperarTodosLosProfesionales() {

        Set<Profesional> profesionales = profesionalService.allProfesionales();

        assertEquals(4, profesionales.size());

    }






    @AfterEach
    void tearDown(){
        profesionalService.clearAll();
        especialidadService.clearAll();
        pacienteService.clearAll();
        turnoService.clearAll();
    }

}
