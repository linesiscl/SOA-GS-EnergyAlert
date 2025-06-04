package br.com.fiap.controller;

import br.com.fiap.model.Tecnico;
import br.com.fiap.repository.TecnicoRep;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoRep tecnicoRepository;

    @PostMapping("/cadastro")
    public Tecnico cadastrar(@RequestBody @Valid Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    @GetMapping
    public List<Tecnico> listarTodos() {
        return tecnicoRepository.findAll();
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha) {
        Tecnico tecnico = tecnicoRepository.findByEmail(email);
        if (tecnico != null && tecnico.getSenha().equals(senha)) {
            return "Login de técnico bem-sucedido!";
        } else {
            return "Email ou senha inválidos!";
        }
    }
}

