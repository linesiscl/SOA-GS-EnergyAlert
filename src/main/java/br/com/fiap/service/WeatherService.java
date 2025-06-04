package br.com.fiap.service;

import br.com.fiap.dto.TempoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://api.openweathermap.org");

    public TempoDTO buscarClimaPorCep(String cep) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.openweathermap.org")
                        .path("/data/2.5/weather")
                        .queryParam("q", cep) // ou usar "zip" se for CEP no formato ZIP+PAÍS
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .queryParam("lang", "pt_br")
                        .build())
                .retrieve()
                .bodyToMono(TempoDTO.class)
                .block();
    }
}

