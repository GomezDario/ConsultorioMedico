package org.consultorio.medico;

import org.consultorio.medico.modelo.Especialidad;
import org.consultorio.medico.modelo.Profesional;
import org.consultorio.medico.modelo.exception.CantidadProfesionalesPorEspecialidadException;
import org.consultorio.medico.service.interfaces.EspecialidadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EspecialidadServiceTest {

    @Autowired
    private EspecialidadService especialidadService;

    private Especialidad dermatologia;
    private Especialidad clinicaMedica;

    private Profesional profesional1;
    private Profesional profesional2;
    private Profesional profesional3;
    private Profesional profesional4;


    @BeforeEach
    public void prepare() {

        //Horario en el que trabajan los profesionales, el consultorio es de 8 a 23, estos profesionales tienen su horario particular
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(16, 0);

        dermatologia = new Especialidad("DERMATOLOGIA");


        profesional1 = new Profesional("Juan", dermatologia, horaInicio, horaFin,777222444);
        profesional2 = new Profesional("Dario", dermatologia, horaInicio, horaFin,111333555);
        profesional3 = new Profesional("Gabriel", dermatologia, horaInicio, horaFin,444111222);
        profesional4 = new Profesional("Franco", dermatologia, horaInicio, horaFin,222333444);

        dermatologia.agregarProfesional(profesional1);
        dermatologia.agregarProfesional(profesional2);
        dermatologia.agregarProfesional(profesional3);
        dermatologia.agregarProfesional(profesional4);


        especialidadService.guardarEspecialidad(dermatologia);


    }

    @Test
    void testGuardarEspecialidad() {

        Especialidad recuperada = especialidadService.recuperarEspecialidad(dermatologia.getId());

        assertEquals(dermatologia.getId(), recuperada.getId());
        assertEquals(4, recuperada.getProfesionales().size());
    }

    @Test
    void testGuardarEspecialidadConMenosDe4Profesionales() {

        Especialidad especialidadInvalida = new Especialidad("CLINICAMEDICA");
        Profesional profesional1 = new Profesional("Carlos", especialidadInvalida, LocalTime.of(9, 0), LocalTime.of(17, 0),111222333);
        Profesional profesional2 = new Profesional("Ana", especialidadInvalida, LocalTime.of(9, 0), LocalTime.of(17, 0),555444222);
        especialidadInvalida.agregarProfesional(profesional1);
        especialidadInvalida.agregarProfesional(profesional2);


        Exception exception = assertThrows(CantidadProfesionalesPorEspecialidadException.class, () -> {
            especialidadService.guardarEspecialidad(especialidadInvalida);
        });

        assertEquals("La cantidad de profesionales por especialidad debe ser como minimo 4, ahora la cantidad es: 2", exception.getMessage());
    }


    @Test
    void testRecuperarTodasLasEspecialidades() {

        Set<Especialidad> especialidades = especialidadService.allEspecialidad();
        assertEquals(1, especialidades.size());
    }


    @AfterEach
    void tearDown(){
        especialidadService.clearAll();
    }


}
