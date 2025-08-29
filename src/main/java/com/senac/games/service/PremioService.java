package com.senac.games.service;

import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.entity.Premio;
import com.senac.games.repository.PatrocinadorRepository;
import com.senac.games.repository.PremioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {
    private ModelMapper modelMapper;
    private PremioRepository premioRepository;

    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest){
        Premio premio = modelMapper.map(premioDTORequest,Premio.class);
        Premio premioSalvo = this.premioRepository.save(premio);
        PremioDTOResponse premioDTOResponse = modelMapper.map(premioSalvo,PremioDTOResponse.class);
        return premioDTOResponse;
    }
    public List<Premio> listarPremios(){
        return this.premioRepository.findAll();
    }
}
