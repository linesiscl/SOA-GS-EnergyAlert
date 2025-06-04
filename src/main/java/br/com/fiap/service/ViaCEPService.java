package br.com.fiap.service;

import br.com.fiap.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViaCEPService {

    private final WebClient webClient = WebClient.create("https://viacep.com.br/ws");

    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        return webClient
                .get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(EnderecoDTO.class)
                .block(); // Síncrono
    }
}
