package br.com.fiap.controller;

import br.com.fiap.dto.EnderecoDTO;
import br.com.fiap.model.Cidadao;
import br.com.fiap.repository.CidadaoRep;
import br.com.fiap.service.ViaCEPService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidadaos")
public class CidadaoController {

    @Autowired
    private CidadaoRep cidadaoRepository;

    @Autowired
    private ViaCEPService viaCepService;

    @PostMapping("/cadastro")
    public Cidadao cadastrar(@RequestBody @Valid Cidadao cidadao) {
        EnderecoDTO endereco = viaCepService.buscarEnderecoPorCep(cidadao.getCep());

        cidadao.setBairro(endereco.getBairro());
        cidadao.setLocalidade(endereco.getLocalidade());
        cidadao.setUf(endereco.getUf());

        return cidadaoRepository.save(cidadao);
    }

    @GetMapping
    public List<Cidadao> listarTodos() {
        return cidadaoRepository.findAll();
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha) {
        Cidadao cidadao = cidadaoRepository.findByEmail(email);
        if (cidadao != null && cidadao.getSenha().equals(senha)) {
            return "Login de cidadão bem-sucedido!";
        } else {
            return "Email ou senha inválidos!";
        }
    }
}

