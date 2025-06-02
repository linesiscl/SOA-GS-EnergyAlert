package br.com.fiap.repository;

import br.com.fiap.model.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadaoRep extends JpaRepository<Cidadao, Long> {
    Cidadao findByEmail(String email);
}

