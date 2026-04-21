package com.example.examenMau.controller;

import com.example.examenMau.Service.partida.PartidaService;
import com.example.examenMau.dto.RecargaRequest;
import com.example.examenMau.models.jugador.BeanJugador;
import com.example.examenMau.models.partida.BeanPartida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final PartidaService partidaService;

    public JugadorController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PatchMapping("/{jugadorId}/recargar")
    public ResponseEntity<BeanJugador> recargarSaldo(@PathVariable Long jugadorId,
                                                  @RequestBody RecargaRequest request) {
        BeanJugador jugador = partidaService.recargarSaldo(jugadorId, request.getMonto());
        return ResponseEntity.ok(jugador);
    }

    @GetMapping("/{jugadorId}/partidas")
    public ResponseEntity<List<BeanPartida>> obtenerHistorial(@PathVariable Long jugadorId) {
        List<BeanPartida> historial = partidaService.obtenerHistorialPartidas(jugadorId);
        return ResponseEntity.ok(historial);
    }
}
