package br.com.fiap.repository;

import br.com.fiap.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRep extends JpaRepository<Tecnico, Long> {
    Tecnico findByEmail(String email);
}

