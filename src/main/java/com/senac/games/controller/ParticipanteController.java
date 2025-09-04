package com.senac.games.controller;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.request.ParticipanteDTOUpdateRequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.response.ParticipanteDTOUpdateResponse;
import com.senac.games.entity.Participante;
import com.senac.games.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participante")
@Tag(name="Participante", description="API para gerenciamento de participantes")
public class ParticipanteController {

    private ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar participantes", description = "Endpoint para listar todos os participantes")
    public ResponseEntity<List<Participante>> listarParticipantes(){
        return ResponseEntity.ok(participanteService.listarParticipantes());
    }

    @GetMapping("/listarPorParticipanteId/{participanteId}")
    @Operation(summary = "Listar participante pelo id de participante", description = "Endpoint para obter participante pelo id de participante")
    public ResponseEntity<Participante> listarPorParticipanteId(@PathVariable("participanteId") Integer participanteId){
        Participante participante = participanteService.listarPorParticipanteId(participanteId);
        if(participante == null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(participante);
        }
    }
    @PostMapping("/criar")
    @Operation(summary = "Criar novo participante", description = "Endpoint para criar um novo registro de participante")
    public ResponseEntity<ParticipanteDTOResponse> criarParticipante(@Valid @RequestBody ParticipanteDTORequest participante){
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.criarParticipante(participante));
    }

    @PutMapping("/atualizar/{participanteId}")
    @Operation(summary = "Atualizar todos os dados do participante",
            description = "Endpoint para atualizar participante")
    public ResponseEntity<ParticipanteDTOResponse>
    atualizarParticipante(@PathVariable("participanteId") Integer participanteId,
                          @RequestBody ParticipanteDTORequest participante){
        return ResponseEntity.ok(this.participanteService.atualizarParticipante(participanteId,participante));
    }

    @PatchMapping("/atualizarStatus/{participanteid}")
    @Operation(summary = "Atualizar o campo status", description = "Atualiza o status do participante")
    public ResponseEntity<ParticipanteDTOUpdateResponse> atualizarStatusParticipante(@PathVariable("participanteId") Integer participanteId,
            @RequestBody ParticipanteDTOUpdateRequest participanteStatus){
        return ResponseEntity.ok(this.participanteService.atualizarStatus(participanteId,participanteStatus));
    }

    @DeleteMapping("/apagar/{participanteId}")
    @Operation(summary = "Deletar um participante", description = "Deleta um participante")
    public ResponseEntity deletarParticipante(@PathVariable("participanteId") Integer participanteId){
        this.participanteService.apagarUsuario(participanteId);
        return ResponseEntity.noContent().build();
    }
}
