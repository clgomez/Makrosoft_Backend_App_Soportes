package co.makrosoft.soporte.backend_aplicacion_soportes.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.makrosoft.soporte.backend_aplicacion_soportes.dtos.SoporteDTO;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Soporte;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;
import co.makrosoft.soporte.backend_aplicacion_soportes.services.ISoporteService;
import co.makrosoft.soporte.backend_aplicacion_soportes.services.ITrabajadorService;

@RestController
@RequestMapping("/api")
public class SoporteController {
    
    @Autowired
    private ISoporteService soporteService;

    @Autowired
    private ITrabajadorService trabajadorService;

    @GetMapping("/soportes")
 	public ResponseEntity<?> obtenerTodosLosSoportes(){

        Map<String, Object> respuesta = new HashMap<>();
   
        try{
            List <SoporteDTO> ArraySoportesDTO = new ArrayList<>();          
            List <Soporte> soportes = this.soporteService.obtenerTodosLosSoportes();
                           
            for(Soporte soporte: soportes)
            {
                SoporteDTO soporteDTO = new SoporteDTO();
                soporteDTO.convertir_Soporte_a_DTO(soporte);
                ArraySoportesDTO.add(soporteDTO);
                    
            }

                if(soportes.isEmpty())
                    respuesta.put("mensaje", "No hay soportes en la lista de soportes");
                
                 
                respuesta.put("soportes", ArraySoportesDTO);
               
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
                
           
        }catch(Exception e)
        {
            respuesta.put("mensaje", "Error al realizar la busqueda en la base de datos");
            //respuesta.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            respuesta.put("error", e.getMessage() + " " + e.getCause());
            return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
	
	}
 

    @PostMapping("/soporte")
    public ResponseEntity<?> asignarSoporte(@Valid @RequestBody SoporteDTO objSoporteDTO) {
        
        Map<String, Object> respuesta = new HashMap<>();

        try{
        Soporte objSoporte = new Soporte();    
        Soporte soporte = new Soporte();
        SoporteDTO soporteDTO = new SoporteDTO();
        Trabajador trabajador = new Trabajador();
        Optional <Trabajador> optTrabajador;
        int complejidadAcumuladaTrabajador;
        int complejidadSoporte;
        List <Trabajador> ArrayTrabajadores = new ArrayList<>();
        ArrayTrabajadores = this.soporteService.obtenerTrabajadoresPorComplejidadAcumuladaOrdenAscendente();
        if(!ArrayTrabajadores.isEmpty())
        { 
            optTrabajador = this.trabajadorService.obtenerTrabajadorPorId(objSoporteDTO.getIdTrabajador());
            if(optTrabajador.isPresent())
            { 
                 
                if(ArrayTrabajadores.get(0).getId() == objSoporteDTO.getIdTrabajador())
                {   
                    complejidadSoporte = objSoporteDTO.getComplejidad();
                    trabajador = optTrabajador.get();
                    complejidadAcumuladaTrabajador = trabajador.getComplejidadAcumulada();
                    trabajador.setComplejidadAcumulada(complejidadSoporte + complejidadAcumuladaTrabajador);
                    objSoporte.convertir_DTO_a_Soporte(objSoporteDTO);
                    soporte = this.soporteService.asignarSoporte(objSoporte);
                    this.trabajadorService.guardaTrabajador(trabajador);
                    soporteDTO.convertir_Soporte_a_DTO(soporte);
                    return new ResponseEntity<SoporteDTO>(soporteDTO, HttpStatus.OK);
                }
                else
                {
                    respuesta.put("mensaje", "Este Trabajador no tiene la menor complejidad para asignarle soporte");
                }
            }else
            {
                respuesta.put("mensaje", "El idTrabajador no existe");
            }
        }else
        {
            respuesta.put("mensaje", "No hay trabajadores para asignarles soportes");
        }

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
            
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}