package org.consultorio.medico.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private int numeroDni;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Turno> turnos = new HashSet<>();

    public Paciente(String nombre, String apellido, int edad, int numeroDni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.numeroDni = numeroDni;
    }

    public void agregarTurno(Turno turno){
        this.getTurnos().add(turno);
    }


}