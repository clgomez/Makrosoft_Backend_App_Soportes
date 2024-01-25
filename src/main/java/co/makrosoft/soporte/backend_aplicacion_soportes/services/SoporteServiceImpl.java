
package co.makrosoft.soporte.backend_aplicacion_soportes.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Soporte;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;
import co.makrosoft.soporte.backend_aplicacion_soportes.repositories.SoporteRepository;
import co.makrosoft.soporte.backend_aplicacion_soportes.repositories.TrabajadorRepository;
import lombok.NonNull;

@Service
public class SoporteServiceImpl implements ISoporteService {
    
   @Autowired
   private SoporteRepository soporteRepository;

   @Autowired
   private TrabajadorRepository trabajadorRepository;
      
    @Override
    public List<Trabajador> obtenerTrabajadoresPorComplejidadAcumuladaOrdenAscendente() {
       
        return this.trabajadorRepository.findByOrderByComplejidadAcumuladaAsc();
    }

    
    @Override
    public Soporte asignarSoporte(@NonNull Soporte soporte) {
        
        return this.soporteRepository.save(soporte);
        
    }

    @Override
    public List<Soporte> obtenerTodosLosSoportes() {
        return this.soporteRepository.findAll();
    }


}
