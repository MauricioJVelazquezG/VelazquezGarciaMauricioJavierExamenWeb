package com.example.examenMau.dto;  

public class NuevaPartidaRequest {

    private Long jugadorId;
    private double apuesta;

    public Long getJugadorId() { return jugadorId; }
    public void setJugadorId(Long jugadorId) { this.jugadorId = jugadorId; }

    public double getApuesta() { return apuesta; }
    public void setApuesta(double apuesta) { this.apuesta = apuesta; }
}
