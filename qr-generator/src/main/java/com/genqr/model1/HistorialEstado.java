package com.genqr.model1;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistorialEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "envio_id")
    private Envio envio;

    @Enumerated(EnumType.STRING)
    private Estado estadoAnterior;

    @Enumerated(EnumType.STRING)
    private Estado estadoNuevo;

    private LocalDateTime fechaCambio = LocalDateTime.now();
    private String notas;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Envio getEnvio() { return envio; }
    public void setEnvio(Envio envio) { this.envio = envio; }

    public Estado getEstadoAnterior() { return estadoAnterior; }
    public void setEstadoAnterior(Estado estadoAnterior) { this.estadoAnterior = estadoAnterior; }

    public Estado getEstadoNuevo() { return estadoNuevo; }
    public void setEstadoNuevo(Estado estadoNuevo) { this.estadoNuevo = estadoNuevo; }

    public LocalDateTime getFechaCambio() { return fechaCambio; }
    public void setFechaCambio(LocalDateTime fechaCambio) { this.fechaCambio = fechaCambio; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}
