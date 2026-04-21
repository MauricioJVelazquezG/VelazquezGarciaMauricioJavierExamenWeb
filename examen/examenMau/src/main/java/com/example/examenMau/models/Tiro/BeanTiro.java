package com.example.examenMau.models.Tiro;

import com.example.examenMau.models.partida.BeanPartida;

import jakarta.persistence.*;

@Entity
@Table(name = "tiro")
public class BeanTiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partida_id", nullable = false)
    private BeanPartida partida;

    @Column(nullable = false)
    private int valorDado1;

    @Column(nullable = false)
    private int valorDado2;

    @Column(nullable = false)
    private int suma;

    @Column(nullable = false)
    private boolean esGanador;
}
