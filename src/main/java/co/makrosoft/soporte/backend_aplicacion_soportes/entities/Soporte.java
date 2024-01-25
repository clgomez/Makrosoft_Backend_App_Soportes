package co.makrosoft.soporte.backend_aplicacion_soportes.entities;

import javax.persistence.*;
import co.makrosoft.soporte.backend_aplicacion_soportes.dtos.SoporteDTO;


@Table(name = "Soporte")
@Entity
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private int complejidad;

    // Relación Many-to-One con Trabajador
    @ManyToOne
    @JoinColumn(name = "id_trabajador", nullable = false)
    private Trabajador objTrabajador;


    // Getters y setters
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

    
    public Trabajador getObjTrabajador() {
        return objTrabajador;
    }

    public void setObjTrabajador(Trabajador objTrabajador) {
        this.objTrabajador = objTrabajador;
    }


    //Constructor
    public Soporte() {
    }


    public void convertir_DTO_a_Soporte(SoporteDTO objSoporteDTO) {
        this.id = objSoporteDTO.getId();
        this.descripcion = objSoporteDTO.getDescripcion();
        this.complejidad = objSoporteDTO.getComplejidad();
        this.objTrabajador = new Trabajador();
        this.objTrabajador.setId(objSoporteDTO.getIdTrabajador());
        
    }
    
    
}
