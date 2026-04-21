package com.example.examenMau.models.jugador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface JugadorRepository extends JpaRepository <BeanJugador, Long> {
}
