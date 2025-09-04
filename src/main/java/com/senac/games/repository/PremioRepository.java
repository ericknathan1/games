package com.senac.games.repository;

import com.senac.games.entity.Categoria;
import com.senac.games.entity.Premio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Premio p SET p.status = -1 WHERE p.id = :id")
    void apagarPremio (@Param("id")Integer premioId);

    @Query("SELECT p FROM Premio p WHERE p.status >= 0")
    List<Premio> listarPremio();
    @Query("SELECT p FROM Premio p WHERE p.id = :id")
    Premio obterPremioPorId (@Param("id")Integer premioId);
}
