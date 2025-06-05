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

}

