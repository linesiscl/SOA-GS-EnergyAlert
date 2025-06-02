package br.com.fiap.service;

import br.com.fiap.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViaCEPService {
    private final WebClient webClient;

    @Autowired
    public ViaCEPService(WebClient webClient) {
        this.webClient = webClient;
    }


    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        return webClient
                .get()
                .uri("https://viacep.com.br/ws/{cep}/json", cep)
                .retrieve()
                .bodyToMono(EnderecoDTO.class)
                .block(); // uso síncrono
    }
}
