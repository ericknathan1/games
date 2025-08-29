package com.senac.games.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="jogo_id")
    private int id;

    @Column(name="jogo_nome")
    private String nome;

    @Column(name="jogo_status")
    private int status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="categoria_id", nullable=false)
    private Categoria categoria;

    public Jogo() {
    }

    public Jogo(int id, String nome, int status, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.categoria = categoria;
    }

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
