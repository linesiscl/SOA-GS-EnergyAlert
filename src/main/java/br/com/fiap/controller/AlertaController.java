package br.com.fiap.controller;


import br.com.fiap.dto.TempoDTO;
import br.com.fiap.model.Alerta;
import br.com.fiap.repository.AlertaRep;
import br.com.fiap.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaRep alertaRepository;

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/emitir")
    public Alerta emitir(@RequestBody Alerta alerta) {
        try {
            TempoDTO tempo = weatherService.buscarClimaPorCep(alerta.getCep());

            if (tempo == null || tempo.getWeather() == null || tempo.getWeather().isEmpty()) {
                alerta.setMensagem("Não foi possível obter o clima para o CEP informado.");
            } else {
                String descricao = tempo.getWeather().get(0).getDescription();
                Double temperatura = tempo.getMain().getTemp();
                alerta.setMensagem("Clima atual: " + descricao + ", temperatura: " + temperatura + "°C");
            }

        } catch (Exception e) {
            alerta.setMensagem("Erro ao buscar clima: " + e.getMessage());
        }

        alerta.setDataHora(LocalDateTime.now());
        return alertaRepository.save(alerta);
    }

    @GetMapping("/cep/{cep}")
    public List<Alerta> buscarPorCep(@PathVariable String cep) {
        return alertaRepository.findByCep(cep);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (alertaRepository.existsById(id)) {
            alertaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

}

