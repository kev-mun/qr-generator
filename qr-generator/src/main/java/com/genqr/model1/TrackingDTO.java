package com.genqr.model1;

import java.time.LocalDateTime;
import java.util.List;

public class TrackingDTO {
    private String trackingNumber;
    private String descripcion;
    private Estado estado;
    private String ciudadDestino;
    private LocalDateTime fechaEstimadaEntrega;
    private String evidenciaUrl;
    private String peso;
    private String dimensiones;
    private List<HistorialDTO> historial;

    public static class HistorialDTO {
        private Estado estadoNuevo;
        private LocalDateTime fechaCambio;
        private String notas;

        public HistorialDTO(Estado estadoNuevo, LocalDateTime fechaCambio, String notas) {
            this.estadoNuevo = estadoNuevo;
            this.fechaCambio = fechaCambio;
            this.notas = notas;
        }

        // Getters
        public Estado getEstadoNuevo() {
            return estadoNuevo;
        }

        public LocalDateTime getFechaCambio() {
            return fechaCambio;
        }

        public String getNotas() {
            return notas;
        }
    }

    // Getters and Setters
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public LocalDateTime getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(LocalDateTime fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public String getEvidenciaUrl() {
        return evidenciaUrl;
    }

    public void setEvidenciaUrl(String evidenciaUrl) {
        this.evidenciaUrl = evidenciaUrl;
    }

    public List<HistorialDTO> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialDTO> historial) {
        this.historial = historial;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }
}
