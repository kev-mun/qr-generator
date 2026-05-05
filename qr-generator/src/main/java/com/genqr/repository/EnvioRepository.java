package com.genqr.repository;

import com.genqr.model1.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    Optional<Envio> findByTrackingNumber(String trackingNumber);
}
