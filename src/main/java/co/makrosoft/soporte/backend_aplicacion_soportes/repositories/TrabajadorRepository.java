package co.makrosoft.soporte.backend_aplicacion_soportes.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Trabajador;


public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    public List<Trabajador> findByOrderByComplejidadAcumuladaAsc();
}
