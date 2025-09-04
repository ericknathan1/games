package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {
    private JogoRepository jogoRepository;
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public JogoService(JogoRepository jogoRepository, CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.jogoRepository = jogoRepository;
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    public List<Jogo> listarJogos(){
        return this.jogoRepository.listarJogo();
    }

    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest){
        Jogo jogo = this.modelMapper.map(jogoDTORequest,Jogo.class);
        Categoria categoria = categoriaRepository.obterCategoriaPorId(jogoDTORequest.getCategoriaId());
        jogo.setCategoria(categoria);
        Jogo jogoSalvo = this.jogoRepository.save(jogo);
        JogoDTOResponse jogoDTOResponse = this.modelMapper.map(jogoSalvo,JogoDTOResponse.class);
        return jogoDTOResponse;
    }

    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest request){
        Jogo jogoBuscado = this.jogoRepository.obterJogoPorId(jogoId);
        if (jogoBuscado != null){
            modelMapper.map(request,jogoBuscado);
            Jogo jogoSalvo = this.jogoRepository.save(jogoBuscado);
            return modelMapper.map(jogoSalvo,JogoDTOResponse.class);
        }else{
            throw new IllegalArgumentException("Jogo não existe");
        }
    }

    public void apagarJogo(Integer jogoId){
        this.jogoRepository.apagarJogo(jogoId);
    }

    public JogoDTOResponse listarJogoId(Integer id) {
        return modelMapper.map(this.jogoRepository.obterJogoPorId(id),JogoDTOResponse.class);
    }
}