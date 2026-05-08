package com.genqr.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class QrService {

    public byte[] generarQR(String contenido, int ancho, int alto) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // 1. Usamos 'ancho' y 'alto' dinámicos en lugar de 250 fijo
        BitMatrix bitMatrix = qrCodeWriter.encode(contenido, BarcodeFormat.QR_CODE, ancho, alto);

        // 2. Definimos los colores (Formato ARGB: 0xFF + Hexadecimal)
        // Ejemplo: 0xFF000000 es Negro, 0xFFFFFFFF es Blanco
        MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        // 3. Pasamos el objeto 'config' para que aplique los colores
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, config);

        return pngOutputStream.toByteArray();
    }
}