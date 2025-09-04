package com.senac.games.service;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.entity.Participante;
import com.senac.games.repository.InscricaoRepository;
import com.senac.games.repository.ParticipanteRepository;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscricaoService {
    private ModelMapper mapper;
    private InscricaoRepository inscricaoRepository;
    private ParticipanteRepository participanteRepository;

    public InscricaoService(ModelMapper mapper, InscricaoRepository inscricaoRepository, ParticipanteRepository participanteRepository) {
        this.mapper = mapper;
        this.inscricaoRepository = inscricaoRepository;
        this.participanteRepository = participanteRepository;
    }

    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest request){
        Inscricao inscricao = mapper.map(request,Inscricao.class);
        inscricao.setData(LocalDateTime.now());

        Participante participante = participanteRepository.findById(request.getParticipanteId())
                .orElseThrow(() -> new RuntimeException("Participante não encontrado!"));

        inscricao.setParticipante(participante);
        Inscricao inscricaoSalvo = this.inscricaoRepository.save(inscricao);
        return mapper.map(inscricaoSalvo,InscricaoDTOResponse.class);
    }
    public void apagarInscricao(Integer inscricaoId){
        this.inscricaoRepository.apagarInscricao(inscricaoId);
    }
    public InscricaoDTOResponse atualizarInscricao(Integer inscricaoId, InscricaoDTORequest request){
        Inscricao inscricao = this.inscricaoRepository.obterInscricaoPorId(inscricaoId);
        if (inscricao != null){
            mapper.map(request,inscricao);
            Inscricao inscricaoSalva = this.inscricaoRepository.save(inscricao);
            return mapper.map(inscricaoSalva,InscricaoDTOResponse.class);
        }else{
            throw new IllegalArgumentException("Inscrição não existe");
        }
    }
    public List<InscricaoDTOResponse> listarInscricao(){
        List<Inscricao> list = this.inscricaoRepository.listarInscricao();
        return list.stream().map(inscricao -> mapper.map(inscricao,InscricaoDTOResponse.class)).collect(Collectors.toList());
    }
    public InscricaoDTOResponse listarInscricaoPorId(Integer inscricaoId){
        return mapper.map(this.inscricaoRepository.obterInscricaoPorId(inscricaoId),InscricaoDTOResponse.class);
    }
}
