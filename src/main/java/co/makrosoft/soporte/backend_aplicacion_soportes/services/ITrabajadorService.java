package co.makrosoft.soporte.backend_aplicacion_soportes.services;

import java.util.*;


import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;

public interface ITrabajadorService {
   
   public Trabajador guardaTrabajador(Trabajador objTrabajador);
   public List<Trabajador> obtenerTodosLosTrabajadores(); 
   public Optional<Trabajador> obtenerTrabajadorPorId(Long idTrabajador);
  

   
}