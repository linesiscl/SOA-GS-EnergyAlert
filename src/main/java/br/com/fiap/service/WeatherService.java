package br.com.fiap.service;

import br.com.fiap.dto.TempoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final String API_KEY = "e347ee388d4c161a4050feb25ab4fd1f";

    @Autowired
    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public TempoDTO obterClimaPorCidade(String cidade) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.openweathermap.org")
                        .path("/data/2.5/weather")
                        .queryParam("q", cidade)
                        .queryParam("appid", API_KEY)
                        .queryParam("units", "metric")
                        .queryParam("lang", "pt_br")
                        .build()
                )
                .retrieve()
                .bodyToMono(TempoDTO.class)
                .block(); // síncrono
    }
}

