package org.consultorio.medico.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Profesional profesional;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(nullable = false, name = "especialidad_id")
    private Especialidad especialidad;

    @Min(1) @Max(3)
    private int consultorio;

    private int cancelacion = 0;

    public Turno(LocalDateTime fechaHora, Profesional profesional, Paciente paciente, int consultorio){

        this.fechaHora = fechaHora;
        this.profesional = profesional;
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.especialidad = profesional.getEspecialidad();

    }

    public void camcelar() {
        this.setCancelacion(1);
    }
}
