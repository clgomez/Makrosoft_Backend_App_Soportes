package co.makrosoft.soporte.backend_aplicacion_soportes.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;
import co.makrosoft.soporte.backend_aplicacion_soportes.repositories.TrabajadorRepository;
import lombok.NonNull;


@Service
public class TrabajadorServiceImpl implements ITrabajadorService{
    
    @Autowired
    private TrabajadorRepository trabajadorRepository;


    @Override
    public Trabajador guardaTrabajador(@NonNull Trabajador objTrabajador) {
        
        return this.trabajadorRepository.save(objTrabajador);
    }
    
    @Override
    public List<Trabajador> obtenerTodosLosTrabajadores() {
        return this.trabajadorRepository.findAll();
    }

    @Override
    public Optional<Trabajador> obtenerTrabajadorPorId(@NonNull Long idTrabajador) {
        
        return this.trabajadorRepository.findById(idTrabajador);

    }
    
 
}
