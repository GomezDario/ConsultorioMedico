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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TurnoServiceTest {

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

    private Turno turno1;
    private Turno turno2;

    private Turno turnoCancelable;
    private Turno turnoNoCancelable;

    private Paciente paciente1;


    @BeforeEach
    public void prepare() {
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(16, 0);


        dermatologia = new Especialidad("DERMATOLOGIA");


        profesional1 = new Profesional("Juan", dermatologia, horaInicio, horaFin);
        profesional2 = new Profesional("Dario", dermatologia, horaInicio, horaFin);
        profesional3 = new Profesional("Gabriel", dermatologia, horaInicio, horaFin);
        profesional4 = new Profesional("Franco", dermatologia, horaInicio, horaFin);

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

        turno1 = new Turno(LocalDateTime.now(), profesional1, paciente1, 1);
        turno2 = new Turno(LocalDateTime.now().plusDays(1), profesional1, paciente1, 2);



        turnoCancelable = new Turno(LocalDateTime.now().plusHours(2), profesional1, paciente1, 1);
        turnoNoCancelable = new Turno(LocalDateTime.now().plusMinutes(30), profesional1, paciente1, 2);

        turnoService.guardarTurno(turno1);
        turnoService.guardarTurno(turno2);


        turnoService.guardarTurno(turnoCancelable);
        turnoService.guardarTurno(turnoNoCancelable);

    }



    @Test
    void testTurnosPorProfesional() {

        List<Turno> turnos = turnoService.turnosPorProfesional(profesional1);

        assertEquals(4, turnos.size());
        assertEquals(turnos.get(0).getId(), turno1.getId());
        assertEquals(turnos.get(1).getId(), turno2.getId());


    }


    @Test
    void testTurnosPorEspecialidad(){

        List<Turno> turnos = turnoService.turnosPorEspecialidad(dermatologia);
        assertEquals(2, turnos.size());
        assertEquals(turnos.get(0).getId(), turno1.getId());
        assertEquals(turnos.get(1).getId(), turno2.getId());

    }


    @Test
    void testTurnosPorEspecialidadYProfesionalJuntos(){

        List<Turno> turnos = turnoService.turnosPorProfesionalYPorEspecialidad(profesional1,dermatologia);

        assertEquals(4, turnos.size());
    }


    @Test
    void testPuedeCancelarTurno(){

        turnoService.cancelarTurno(turnoCancelable.getId());

        Turno turnoCancelado = turnoService.recuperarTurno(turnoCancelable.getId());
        assertEquals(turnoCancelado.getCancelacion(), 1);

    }

    @Test
    void testNoPuedeCancelarTurno(){

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            turnoService.cancelarTurno(turnoNoCancelable.getId());
        });

        assertEquals("No se puede cancelar el turno con menos de una hora de anticipacion.", exception.getMessage());

    }






    /*@AfterEach
    void tearDown(){
        especialidadService.clearAll();
    }*/



}
