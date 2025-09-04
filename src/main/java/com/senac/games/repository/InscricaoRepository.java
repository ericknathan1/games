package com.senac.games.repository;

import com.senac.games.entity.Categoria;
import com.senac.games.entity.Inscricao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Inscricao i SET i.status = -1 WHERE i.id = :id")
    void apagarInscricao (@Param("id")Integer categoriaId);

    @Query("SELECT i FROM Inscricao i WHERE i.status  >= 0")
    List<Inscricao> listarInscricao();

    @Query("SELECT i FROM Inscricao i WHERE i.id = :id")
    Inscricao obterInscricaoPorId (@Param("id")Integer inscricaoId);
}
