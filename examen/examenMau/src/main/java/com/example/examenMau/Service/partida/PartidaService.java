package com.example.examenMau.Service.partida;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.examenMau.models.Tiro.BeanTiro;
import com.example.examenMau.models.Tiro.TiroRepository;
import com.example.examenMau.models.jugador.BeanJugador;
import com.example.examenMau.models.jugador.JugadorRepository;
import com.example.examenMau.models.partida.BeanPartida;
import com.example.examenMau.models.partida.ENUM_ESTADO;
import com.example.examenMau.models.partida.PartidaRepository;

@Service
public class PartidaService {

    private final JugadorRepository jugadorRepository;
    private final PartidaRepository partidaRepository;
    private final TiroRepository tiroRepository;
    private final Random random = new Random();

    public PartidaService(JugadorRepository jugadorRepository,PartidaRepository partidaRepository,TiroRepository tiroRepository) {
        this.jugadorRepository = jugadorRepository;
        this.partidaRepository = partidaRepository;
        this.tiroRepository = tiroRepository;
    }

    public BeanPartida iniciarPartida(Long jugadorId, double apuesta) {
        BeanJugador jugador = jugadorRepository.findById(jugadorId).get();

        if (!jugador.isActivo()) {
            throw new RuntimeException("El jugador no esta activo :c");
        }

        if (jugador.getSaldo() < apuesta) {
            throw new RuntimeException("Sin saldo suficiente");
        }

        jugador.setSaldo(jugador.getSaldo() - apuesta);
        jugadorRepository.save(jugador);

        BeanPartida partida = new BeanPartida();
        partida.setJugador(jugador);
        partida.setFecha(LocalDateTime.now());
        partida.setApuesta(apuesta);
        partida.setEstado(ENUM_ESTADO.EN_JUEGO);

        return partidaRepository.save(partida);
    }

    public BeanTiro realizarTiro(Long partidaId) {
        BeanPartida partida = partidaRepository.findById(partidaId).get();

        int dado1 = lanzarDado();
        int dado2 = lanzarDado();
        int suma = dado1 + dado2;

        boolean esGanador = false;

        if (suma == 7 || suma == 11) {
            esGanador = true;
            partida.setEstado(EstadoPartida.FINALIZADA);
            BeanJugador jugador = partida.getJugador();
            jugador.setSaldo(jugador.getSaldo() + partida.getApuesta() * 2);
            jugadorRepository.save(jugador);
        } else if (suma == 2 || suma == 3 || suma == 12) {
            partida.setEstado(EstadoPartida.FINALIZADA);
        }

        partidaRepository.save(partida);

        BeanTiro tiro = new BeanTiro();
        tiro.setPartida(partida);
        tiro.setValorDado1(dado1);
        tiro.setValorDado2(dado2);
        tiro.setSuma(suma);
        tiro.setEsGanador(esGanador);

        return tiroRepository.save(tiro);
    }

    public BeanPartida finalizarPartidaManual(Long partidaId) {
        BeanPartida partida = partidaRepository.findById(partidaId).orElseThrow(() -> new RuntimeException("Partida no encontrada con id: " + partidaId));

        partida.setEstado(EstadoPartida.FINALIZADA);
        return partidaRepository.save(partida);
    }

}
