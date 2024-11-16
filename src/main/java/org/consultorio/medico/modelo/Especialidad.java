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
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Profesional> profesionales = new HashSet<>();


    public Especialidad(String nombre){
        this.nombre = nombre;
    }

    public void agregarProfesional(Profesional profesional){
        this.profesionales.add(profesional);
    }


}
