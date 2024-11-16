package org.consultorio.medico;

import org.consultorio.medico.modelo.*;
import org.consultorio.medico.service.interfaces.EspecialidadService;
import org.consultorio.medico.service.interfaces.PacienteService;
import org.consultorio.medico.service.interfaces.ProfesionalService;
import org.consultorio.medico.service.interfaces.TurnoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @MockBean
    private ClockProvider clockProvider;

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

        //Horario en el que trabajan los profesionales, el consultorio es de 8 a 23, estos profesionales tienen su horario particular
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(16, 0);


        dermatologia = new Especialidad("DERMATOLOGIA");


        profesional1 = new Profesional("Juan", dermatologia, horaInicio, horaFin,222333444);
        profesional2 = new Profesional("Dario", dermatologia, horaInicio, horaFin,444555666);
        profesional3 = new Profesional("Gabriel", dermatologia, horaInicio, horaFin,777111222);
        profesional4 = new Profesional("Franco", dermatologia, horaInicio, horaFin,888222333);

        dermatologia.agregarProfesional(profesional1);
        dermatologia.agregarProfesional(profesional2);
        dermatologia.agregarProfesional(profesional3);
        dermatologia.agregarProfesional(profesional4);


        especialidadService.guardarEspecialidad(dermatologia);



        profesionalService.guardarProfesional(profesional1);
        profesionalService.guardarProfesional(profesional2);
        profesionalService.guardarProfesional(profesional3);
        profesionalService.guardarProfesional(profesional4);


        paciente1 = new Paciente("Dario", "Gomez", 24,111222333);
        pacienteService.guardarPaciente(paciente1);



        turno1 = new Turno(LocalDateTime.of(2024, 11, 15, 14, 30), profesional1, paciente1, 1);
        turno2 = new Turno(LocalDateTime.of(2024, 11, 15, 15, 30), profesional1, paciente1, 2);


        turnoCancelable = new Turno(LocalDateTime.of(2024, 11, 15, 14, 30), profesional1, paciente1, 1);
        turnoNoCancelable = new Turno(LocalDateTime.of(2024, 11, 15, 16, 30), profesional1, paciente1, 2);

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
        assertEquals(4, turnos.size());
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

        // Simular un tiempo actual antes del límite de cancelación
        when(clockProvider.now()).thenReturn(LocalDateTime.of(2024, 11, 15, 12, 30));

        turnoService.cancelarTurno(turnoCancelable.getId());

        Turno turnoCancelado = turnoService.recuperarTurno(turnoCancelable.getId());
        assertEquals(turnoCancelado.getCancelacion(), 1);

    }

    @Test
    void testNoPuedeCancelarTurno(){

        when(clockProvider.now()).thenReturn(LocalDateTime.of(2024, 11, 15, 16, 0));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            turnoService.cancelarTurno(turnoNoCancelable.getId());
        });

        assertEquals("No se puede cancelar el turno con menos de una hora de anticipacion.", exception.getMessage());

    }

    @Test
    void testPuedeModificarTurno() {

        when(clockProvider.now()).thenReturn(LocalDateTime.of(2024, 11, 15, 12, 30));

        LocalDateTime nuevaFechaHora = LocalDateTime.of(2024, 11, 16, 14, 0);
        Profesional nuevoProfesional = profesional2;
        int nuevoConsultorio = 3;

        turnoService.modificarTurno(turno1.getId(), nuevaFechaHora, nuevoProfesional, nuevoConsultorio);

        Turno turnoModificado = turnoService.recuperarTurno(turno1.getId());

        assertEquals(nuevaFechaHora, turnoModificado.getFechaHora());
        assertEquals(nuevoProfesional.getId(), turnoModificado.getProfesional().getId());
        assertEquals(nuevoConsultorio, turnoModificado.getConsultorio());
    }

    @Test
    void testNoSePuedeModificarTurnoPorRestriccionDeTiempo() {

        when(clockProvider.now()).thenReturn(LocalDateTime.of(2024, 11, 15, 14, 0));

        LocalDateTime nuevaFechaHora = LocalDateTime.of(2024, 11, 16, 14, 0);
        Profesional nuevoProfesional = profesional2;
        int nuevoConsultorio = 3;

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            turnoService.modificarTurno(turno1.getId(), nuevaFechaHora, nuevoProfesional, nuevoConsultorio);
        });

        assertEquals("No se puede modificar el turno con menos de una hora de anticipacion.", exception.getMessage());
    }








    @AfterEach
    void tearDown(){
        especialidadService.clearAll();
        turnoService.clearAll();
        profesionalService.clearAll();
        pacienteService.clearAll();
    }



}
