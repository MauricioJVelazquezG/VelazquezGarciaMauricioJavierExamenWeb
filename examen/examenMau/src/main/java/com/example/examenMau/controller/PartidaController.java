package com.example.examenMau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.examenMau.Service.partida.PartidaService;
import com.example.examenMau.dto.NuevaPartidaRequest;
import com.example.examenMau.models.Tiro.BeanTiro;
import com.example.examenMau.models.partida.BeanPartida;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/nueva")
    public ResponseEntity<BeanPartida> iniciarPartida(@RequestBody NuevaPartidaRequest request) {
        BeanPartida partida = partidaService.iniciarPartida(request.getJugadorId(), request.getApuesta());
        return ResponseEntity.ok(partida);
    }

    @PostMapping("/{partidaId}/lanzar")
    public ResponseEntity<BeanTiro> realizarTiro(@PathVariable Long partidaId) {
        BeanTiro tiro = partidaService.realizarTiro(partidaId);
        return ResponseEntity.ok(tiro);
    }

    @PatchMapping("/{partidaId}/cerrar")
    public ResponseEntity<BeanPartida> finalizarPartidaManual(@PathVariable Long partidaId) {
        BeanPartida partida = partidaService.finalizarPartidaManual(partidaId);
        return ResponseEntity.ok(partida);
    }
}
