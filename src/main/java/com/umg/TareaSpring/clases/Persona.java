package com.umg.TareaSpring.clases;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Persona {
    private String nombre;
    private LocalDate fecha;
    private String domicilio;

    public Persona() {
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    public Persona(String nombre, LocalDate fecha, String domicilio, Long id) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.domicilio = domicilio;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
