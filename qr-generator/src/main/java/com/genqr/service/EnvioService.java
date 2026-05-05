package com.genqr.service;

import com.genqr.model1.Envio;
import com.genqr.model1.Estado;
import com.genqr.model1.HistorialEstado;
import com.genqr.repository.EnvioRepository;
import com.genqr.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private HistorialRepository historialRepository;

    @Transactional
    public Envio crearEnvio(String descripcion, String destinatario) {
        Envio envio = new Envio();
        envio.setTrackingNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        envio.setDescripcion(descripcion);
        envio.setDestinatario(destinatario);
        envio.setEstado(Estado.PENDIENTE);
        envio.setFechaEstimadaEntrega(LocalDateTime.now().plusDays(3));
        
        Envio guardado = envioRepository.save(envio);

        // Registrar historial inicial
        registrarCambioEstado(guardado, null, Estado.PENDIENTE, "Envio registrado en el sistema");
        
        return guardado;
    }

    @Transactional
    public Envio actualizarEstado(Long id, Estado nuevoEstado, String notas, String evidenciaUrl) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        Estado estadoAnterior = envio.getEstado();
        envio.setEstado(nuevoEstado);
        if (evidenciaUrl != null) {
            envio.setEvidenciaUrl(evidenciaUrl);
        }

        Envio actualizado = envioRepository.save(envio);
        registrarCambioEstado(actualizado, estadoAnterior, nuevoEstado, notas);

        return actualizado;
    }

    private void registrarCambioEstado(Envio envio, Estado anterior, Estado nuevo, String notas) {
        HistorialEstado historial = new HistorialEstado();
        historial.setEnvio(envio);
        historial.setEstadoAnterior(anterior);
        historial.setEstadoNuevo(nuevo);
        historial.setNotas(notas);
        historialRepository.save(historial);
    }

    public List<HistorialEstado> obtenerHistorial(Long envioId) {
        Envio envio = envioRepository.findById(envioId)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));
        return historialRepository.findByEnvioOrderByFechaCambioDesc(envio);
    }
}
