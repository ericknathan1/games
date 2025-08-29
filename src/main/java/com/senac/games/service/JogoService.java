package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {
    private JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> listarJogos(){
        return this.jogoRepository.findAll();
    }

    @Autowired
    private ModelMapper modelMapper;
    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest){
        Jogo jogo = this.modelMapper.map(jogoDTORequest,Jogo.class);
        Jogo jogoSalvo = this.jogoRepository.save(jogo);
        JogoDTOResponse jogoDTOResponse = this.modelMapper.map(jogoSalvo,JogoDTOResponse.class);
        return jogoDTOResponse;
    }
}