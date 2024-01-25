package co.makrosoft.soporte.backend_aplicacion_soportes.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.makrosoft.soporte.backend_aplicacion_soportes.dtos.TrabajadorDTO;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;
import co.makrosoft.soporte.backend_aplicacion_soportes.services.ITrabajadorService;

@RestController
@RequestMapping("/api")
public class TrabajadorController {
    @Autowired
    private ITrabajadorService trabajadorService;

    @GetMapping("/trabajadores")
 	public ResponseEntity<?> obtenerTodosLosTrabajadores(){

        Map<String, Object> respuesta = new HashMap<>();
   
        try{
            List <TrabajadorDTO> ArrayTrabajadoresDTO = new ArrayList<>();          
            List <Trabajador> trabajadores = this.trabajadorService.obtenerTodosLosTrabajadores();
                           
            for(Trabajador trabajador: trabajadores)
            {
                TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
                trabajadorDTO.convertir_Trabajador_a_DTO(trabajador);
                ArrayTrabajadoresDTO.add(trabajadorDTO);
                    
            }

                if(trabajadores.isEmpty())
                    respuesta.put("mensaje", "No hay trabajadores en la lista de trabajadores");
                
                 
                respuesta.put("trabajadores", ArrayTrabajadoresDTO);
               
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
                
           
        }catch(Exception e)
        {
            respuesta.put("mensaje", "Error al realizar la busqueda en la base de datos");
            //respuesta.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            respuesta.put("error", e.getMessage() + " " + e.getCause());
            return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
	
	}
    
    @PostMapping("/trabajador")
    public ResponseEntity<?> guardarTrabajador(@Valid @RequestBody TrabajadorDTO objTrabajadorDTO){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            Trabajador objTrabajador = new Trabajador();
            Trabajador trabajador = new Trabajador();
            TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
            objTrabajador.convertir_DTO_a_Trabajador(objTrabajadorDTO);
            trabajador = this.trabajadorService.guardaTrabajador(objTrabajador);
            trabajadorDTO.convertir_Trabajador_a_DTO(trabajador);
            
            return new ResponseEntity<TrabajadorDTO>(trabajadorDTO, HttpStatus.OK);
                            
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

}