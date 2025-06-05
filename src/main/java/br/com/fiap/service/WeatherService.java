package br.com.fiap.service;

import br.com.fiap.dto.EnderecoDTO;
import br.com.fiap.dto.TempoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.Normalizer;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private ViaCEPService viaCEPService;

    private final WebClient webClient = WebClient.create("https://api.openweathermap.org");

    public TempoDTO buscarClimaPorCep(String cep) {
        // Buscar cidade a partir do CEP
        var endereco = viaCEPService.buscarEnderecoPorCep(cep);
        String cidade = endereco.getLocalidade();

        if (cidade == null || cidade.isBlank()) {
            throw new RuntimeException("Cidade não encontrada para o CEP informado");
        }

        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/data/2.5/weather")
                            .queryParam("q", cidade + ",BR")  // exemplo: "São Paulo,BR"
                            .queryParam("appid", apiKey)
                            .queryParam("units", "metric")
                            .queryParam("lang", "pt_br")
                            .build())
                    .retrieve()
                    .bodyToMono(TempoDTO.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar clima: " + e.getMessage());
        }
    }

    private String formatar(String cidade) {
        // Remove acentos e espaços extras
        String normalizada = Normalizer.normalize(cidade, Normalizer.Form.NFD);
        return normalizada.replaceAll("[^\\p{ASCII}]", "").replace(" ", "%20");
    }
}

