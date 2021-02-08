package io.github.vagnereix.desafioUrbana.model.repository;

import io.github.vagnereix.desafioUrbana.model.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

//    @Query("SELECT c FROM Cartao c WHERE c.id_usuario = :id")
//    List<Cartao> findByIdUsuario(@Param("id_usuario") int id);
}
