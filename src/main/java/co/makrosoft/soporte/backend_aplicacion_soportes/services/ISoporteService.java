package co.makrosoft.soporte.backend_aplicacion_soportes.services;

import java.util.*;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Soporte;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;

public interface ISoporteService {
   
    public List<Trabajador> obtenerTrabajadoresPorComplejidadAcumuladaOrdenAscendente();
    public Soporte asignarSoporte(Soporte objSoporte);
    public List<Soporte> obtenerTodosLosSoportes();
        
}




