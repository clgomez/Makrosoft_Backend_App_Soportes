package co.makrosoft.soporte.backend_aplicacion_soportes.dtos;

import java.io.Serializable;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;


public class TrabajadorDTO implements Serializable {
    
    private Long id;
    private String nombre;
    private int complejidadAcumulada;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getComplejidadAcumulada() {
        return complejidadAcumulada;
    }

    public void setComplejidadAcumulada(int complejidadAcumulada) {
        this.complejidadAcumulada = complejidadAcumulada;
    }
       
    public TrabajadorDTO() {
    }

    public void convertir_Trabajador_a_DTO(Trabajador objTrabajador) {
        this.id = objTrabajador.getId();
        this.nombre = objTrabajador.getNombre();
        this.complejidadAcumulada = objTrabajador.getComplejidadAcumulada();
    }
   
}