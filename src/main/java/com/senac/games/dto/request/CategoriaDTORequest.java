package com.senac.games.dto.request;

import com.senac.games.entity.Jogo;

import java.util.Set;

public class CategoriaDTORequest {
    private String nome;
    private int status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
