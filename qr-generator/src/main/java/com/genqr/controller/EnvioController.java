package com.genqr.controller;

import com.genqr.model1.Envio;
import com.genqr.model1.Estado;
import com.genqr.model1.HistorialEstado;
import com.genqr.model1.TrackingDTO;
import com.genqr.repository.EnvioRepository;
import com.genqr.service.EnvioService;
import com.genqr.service.QrService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private QrService qrService;

    @PostMapping("/crear")
    @Operation(summary = "Crear nuevo envío")
    public Envio crearEnvio(
            @RequestParam String descripcion, 
            @RequestParam String destinatario,
            @RequestParam String ciudadDestino,
            @RequestParam(required = false) String peso,
            @RequestParam(required = false) String dimensiones) {
        return envioService.crearEnvio(descripcion, destinatario, ciudadDestino, peso, dimensiones);
    }

    @GetMapping("/all")
    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Envio obtenerDetalle(@PathVariable Long id) {
        return envioRepository.findById(id).orElseThrow();
    }

    @GetMapping("/public/track/{code}")
    public TrackingDTO obtenerSeguimientoPublico(
            @PathVariable String code, 
            @RequestParam(required = false) String pin) {
        return envioService.obtenerSeguimientoPublico(code, pin);
    }

    @GetMapping("/{id}/historial")
    public List<HistorialEstado> obtenerHistorial(@PathVariable Long id) {
        return envioService.obtenerHistorial(id);
    }

    @PostMapping("/{id}/actualizar")
    public Envio actualizarEstado(
            @PathVariable Long id,
            @RequestParam Estado nuevoEstado,
            @RequestParam(required = false) String notas,
            @RequestParam(required = false) String evidenciaUrl) {
        return envioService.actualizarEstado(id, nuevoEstado, notas, evidenciaUrl);
    }

    @GetMapping(value = "/{id}/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> obtenerQr(@PathVariable Long id) throws Exception {
        Envio envio = envioRepository.findById(id).orElseThrow();
        // La URL que contendrá el QR ahora usa el código de rastreo (trackingNumber)
        String urlSeguimiento = "http://localhost:8080/seguimiento.html?code=" + envio.getTrackingNumber();
        byte[] image = qrService.generarQR(urlSeguimiento, 300, 300);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
