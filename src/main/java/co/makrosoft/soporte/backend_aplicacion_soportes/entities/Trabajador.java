package co.makrosoft.soporte.backend_aplicacion_soportes.entities;

import java.util.*;
import javax.persistence.*;

import co.makrosoft.soporte.backend_aplicacion_soportes.dtos.TrabajadorDTO;

@Table(name = "Trabajador")
@Entity
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int complejidadAcumulada;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objTrabajador")
	private List <Soporte> soportes; 


    // Getters y setters
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
    
    public List<Soporte> getSoportes() {
        return soportes;
    }
    public void setSoportes(List<Soporte> soportes) {
        this.soportes = soportes;
    } 

    public void agregarSoporte(Soporte objSoporte)
	{
		this.soportes.add(objSoporte);
	}

    //Constructor
    public Trabajador() {
        this.complejidadAcumulada = 0;
        this.soportes = new ArrayList<Soporte>();
    }

    public void convertir_DTO_a_Trabajador(TrabajadorDTO objTrabajadorDTO) {
        this.id = objTrabajadorDTO.getId();
        this.nombre = objTrabajadorDTO.getNombre();
        this.complejidadAcumulada = objTrabajadorDTO.getComplejidadAcumulada();
    }
    
    
}
