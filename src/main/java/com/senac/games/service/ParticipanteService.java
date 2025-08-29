package com.senac.games.service;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.entity.Participante;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.ParticipanteRepository;
import com.senac.games.repository.PatrocinadorRepository;
import jakarta.servlet.http.Part;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Autowired
    private ModelMapper modelMapper;
    public List<Participante> listarParticipantes(){
        return this.participanteRepository.findAll();
    }

    public Participante listarPorParticipanteId(Integer participanteId) {
        return this.participanteRepository.findById(participanteId).orElse(null);
    }

    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {
        Participante participante = this.modelMapper.map(participanteDTORequest,Participante.class);
        Participante participanteSave = this.participanteRepository.save(participante);
        ParticipanteDTOResponse participanteDTOResponse = this.modelMapper.map(participanteDTORequest, ParticipanteDTOResponse.class);
        return participanteDTOResponse;


//        Participante participante = new Participante();
//        participante.setNome(participanteDTO.getNome());
//        participante.setEmail(participanteDTO.getEmail());
//        participante.setIdentificacao(participanteDTO.getIdentificacao());
//        participante.setEndereco(participanteDTO.getEndereco());
//        participante.setStatus(participanteDTO.getStatus());
//        ParticipanteDTOResponse participanteDTOResponse = new ParticipanteDTOResponse();
//        participanteDTOResponse.setId(participanteSave.getId());
//        participanteDTOResponse.setNome(participanteSave.getNome());
//        participanteDTOResponse.setEmail(participanteSave.getEmail());
//        participanteDTOResponse.setIdentificacao(participanteSave.getIdentificacao());
//        participanteDTOResponse.setEndereco(participanteSave.getEndereco());
//        participanteDTOResponse.setStatus(participanteSave.getStatus());

    }
}
