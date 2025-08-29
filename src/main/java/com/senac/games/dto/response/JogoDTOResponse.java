package com.senac.games.dto.response;

import com.senac.games.entity.Categoria;

public class JogoDTOResponse {
    private int id;
    private String nome;
    private int status;
    private Categoria Categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public com.senac.games.entity.Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(com.senac.games.entity.Categoria categoria) {
        Categoria = categoria;
    }
}
