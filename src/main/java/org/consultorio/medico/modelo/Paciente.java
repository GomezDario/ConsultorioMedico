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

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Turno> turnos = new HashSet<>();

    public Paciente(String nombre){
        this.nombre = nombre;
    }

    public void agregarTurno(Turno turno){
        this.getTurnos().add(turno);
    }


}