package com.example.examenMau.models.partida;

import java.time.LocalDateTime;

import com.example.examenMau.models.jugador.BeanJugador;
import jakarta.persistence.*;

@Entity
@Table(name = "partida")
public class BeanPartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ENUM_ESTADO estado;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private BeanJugador jugador;

    @Column(nullable = false)
    private double apuesta;

}
