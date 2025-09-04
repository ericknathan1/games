package com.senac.games.service;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.request.ParticipanteDTOUpdateRequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.response.ParticipanteDTOUpdateResponse;
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
    @Autowired
    private ModelMapper modelMapper;

    public ParticipanteService(ParticipanteRepository participanteRepository, ModelMapper modelMapper) {
        this.participanteRepository = participanteRepository;
        this.modelMapper = modelMapper;
    }

    public List<Participante> listarParticipantes(){
        return this.participanteRepository.listarParticipante();
    }

    public Participante listarPorParticipanteId(Integer participanteId) {
        return this.participanteRepository.obterParticipantePorId(participanteId);
    }

    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {
        Participante participante = this.modelMapper.map(participanteDTORequest,Participante.class);
        Participante participanteSave = this.participanteRepository.save(participante);
        ParticipanteDTOResponse participanteDTOResponse = this.modelMapper.map(participanteDTORequest, ParticipanteDTOResponse.class);
        return participanteDTOResponse;

    }


    public ParticipanteDTOResponse atualizarParticipante(Integer participanteId,
                                                               ParticipanteDTORequest participanteDTOAtualizado) {
        Participante participanteBuscado = this.listarPorParticipanteId(participanteId);
        if (participanteBuscado != null){
            modelMapper.map(participanteDTOAtualizado, participanteBuscado);
            Participante participanteSalvo = this.participanteRepository.save(participanteBuscado);
            return modelMapper.map(participanteSalvo, ParticipanteDTOResponse.class);
        }else{
            throw new IllegalArgumentException("Id buscado não existe: "+participanteId);
        }
    }
    public ParticipanteDTOUpdateResponse atualizarStatus(Integer participanteId,
                                                         ParticipanteDTOUpdateRequest participanteStatus) {
        Participante participanteBuscado = this.listarPorParticipanteId(participanteId);
        participanteBuscado.setStatus(participanteStatus.getStatus());
        Participante tempResponse = this.participanteRepository.save(participanteBuscado);
        return modelMapper.map(tempResponse, ParticipanteDTOUpdateResponse.class);
    }
}
