package co.makrosoft.soporte.backend_aplicacion_soportes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import co.makrosoft.soporte.backend_aplicacion_soportes.entities.Soporte;
import co.makrosoft.soporte.backend_aplicacion_soportes.repositories.SoporteRepository;

public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    
}

