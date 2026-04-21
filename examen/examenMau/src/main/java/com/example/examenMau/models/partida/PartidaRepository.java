package com.example.examenMau.models.partida;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<BeanPartida, Long> {
        List<BeanPartida> findByJugador_IdOrderByFechaDesc(Long jugadorId);
}
