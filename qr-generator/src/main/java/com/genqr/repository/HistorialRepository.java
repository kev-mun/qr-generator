package com.genqr.repository;

import com.genqr.model1.Envio;
import com.genqr.model1.HistorialEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialRepository extends JpaRepository<HistorialEstado, Long> {
    List<HistorialEstado> findByEnvioOrderByFechaCambioDesc(Envio envio);
}
