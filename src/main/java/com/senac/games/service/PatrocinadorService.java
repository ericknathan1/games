package com.senac.games.service;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrocinadorService {
    @Autowired
    private ModelMapper modelMapper;
    private PatrocinadorRepository patrocinadorRepository;

    public PatrocinadorService(ModelMapper modelMapper, PatrocinadorRepository patrocinadorRepository) {
        this.modelMapper = modelMapper;
        this.patrocinadorRepository = patrocinadorRepository;
    }

    public List<Patrocinador> listarPatrocinadores(){
        return this.patrocinadorRepository.listarPatrocinador();
    }

    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest){
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);
        Patrocinador patrocinadorSalvo = this.patrocinadorRepository.save(patrocinador);
        PatrocinadorDTOResponse patrocinadorDTOResponse = modelMapper.map(patrocinadorSalvo,PatrocinadorDTOResponse.class);
        return patrocinadorDTOResponse;
    }

    public PatrocinadorDTOResponse retornarPatrocinador(Integer patrocinadorId){
        Patrocinador p = this.patrocinadorRepository.obterPatrocinadorPorId(patrocinadorId);
        return modelMapper.map(p,PatrocinadorDTOResponse.class);
    }

    public PatrocinadorDTOResponse atualizarPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest request){
        Patrocinador patrocinadorBuscado = this.patrocinadorRepository.obterPatrocinadorPorId(patrocinadorId);
        if (patrocinadorBuscado != null){
            modelMapper.map(request,patrocinadorBuscado);
            Patrocinador patrocinadorSalvo = this.patrocinadorRepository.save(patrocinadorBuscado);
            return modelMapper.map(patrocinadorSalvo,PatrocinadorDTOResponse.class);
        }else{
            throw new IllegalArgumentException("Patrocinador inexistente");
        }
    }

    public void apagarPatrocinador(Integer id) {
        this.patrocinadorRepository.apagarPatrocinador(id);
    }

    public PatrocinadorDTOResponse listarPatrocinadorPorId(Integer id) {
        return modelMapper.map(this.patrocinadorRepository.obterPatrocinadorPorId(id), PatrocinadorDTOResponse.class);
    }
}
