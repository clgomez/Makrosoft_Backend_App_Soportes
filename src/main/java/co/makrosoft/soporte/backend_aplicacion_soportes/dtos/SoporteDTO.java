package co.makrosoft.soporte.backend_aplicacion_soportes.dtos;

import java.io.Serializable;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Soporte;

public class SoporteDTO implements Serializable {
    
    private Long id;
    private String descripcion;
    private int complejidad;
    private Long idTrabajador;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }

    public Long getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }
          
    public SoporteDTO() {
    }

    public void convertir_Soporte_a_DTO(Soporte objSoporte) {
        this.id = objSoporte.getId();
        this.descripcion = objSoporte.getDescripcion();
        this.complejidad = objSoporte.getComplejidad();
        this.idTrabajador = objSoporte.getObjTrabajador().getId();
    }
  
}