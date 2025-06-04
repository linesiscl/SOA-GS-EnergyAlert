package br.com.fiap.controller;


import br.com.fiap.dto.TempoDTO;
import br.com.fiap.model.Alerta;
import br.com.fiap.repository.AlertaRep;
import br.com.fiap.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
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
        TempoDTO tempo = weatherService.buscarClimaPorCep(alerta.getCep());

        String descricao = tempo.getWeather().get(0).getDescription();
        Double temperatura = tempo.getMain().getTemp();

        alerta.setMensagem("Clima atual: " + descricao + ", temperatura: " + temperatura + "°C");

        alerta.setDataHora(LocalDateTime.now());

        return alertaRepository.save(alerta);
    }

    // Cidadão consulta alertas pelo CEP
    @GetMapping("/por-cep/{cep}")
    public List<Alerta> listarPorCep(@PathVariable String cep) {
        return alertaRepository.findByCep(cep);
    }

    // Listar todos (admin ou debug)
    @GetMapping
    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }
}

