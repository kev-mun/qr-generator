package com.genqr.controller;

import com.genqr.model1.QrEntity;
import com.genqr.repository.QrRepository;
import com.genqr.service.QrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qr")
@Tag(name = "Generador de QR", description = "Endpoints para crear, guardar y listar códigos QR")
public class QrController {

    @Autowired
    private QrService qrService;

    @Autowired
    private QrRepository qrRepository;

    @Operation(summary = "Generar y Guardar QR", description = "Crea la imagen del QR y guarda los datos en la base de datos H2")
    @GetMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generate(
            @RequestParam String text,
            @RequestParam String name) throws Exception {

        QrEntity nuevoQr = new QrEntity();
        nuevoQr.setNombre(name);
        nuevoQr.setContenido(text);

        qrRepository.save(nuevoQr);

        byte[] image = qrService.generarQR(text, 300, 300);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @Operation(summary = "Listar todos los QRs", description = "Recupera todos los registros almacenados en la base de datos")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QrEntity> getAllQrs() {
        return qrRepository.findAll();
    }
}