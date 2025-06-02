package br.com.fiap.repository;

import br.com.fiap.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AlertaRep extends JpaRepository<Alerta, Long>{
    List<Alerta> findByCep(String cep);
}
