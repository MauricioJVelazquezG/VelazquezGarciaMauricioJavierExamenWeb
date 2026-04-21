package com.example.examenMau.models.jugador;

import jakarta.persistence.*;

@Entity
@Table(name = "jugador")
public class BeanJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double saldo;

    @Column(nullable = false)
    private boolean activo;
}
