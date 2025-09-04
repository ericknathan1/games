package com.senac.games.controller;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinador")
@Tag(name="Patrocinador", description="API para gerenciamento de patrocinadores")
public class PatrocinadorController {

    private PatrocinadorService patrocinadorService;

    public PatrocinadorController(PatrocinadorService patrocinadorService) {
        this.patrocinadorService = patrocinadorService;
    }
    @PostMapping("/criar")
    @Operation(summary = "Criar novo patrocinador", description = "Endpoint para criar um novo registro de patrocinador")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(@Valid @RequestBody PatrocinadorDTORequest patrocinador){
        return ResponseEntity.status(HttpStatus.CREATED).body(patrocinadorService.criarPatrocinador(patrocinador));
    }
    @GetMapping("/listar")
    @Operation(summary="Listar patrocinadores", description = "Endpoint para listar todos os patrocinadores")
    public ResponseEntity<List<Patrocinador>> listarPatrocinadores(){
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadores());
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<PatrocinadorDTOResponse> retornarPatrocinador(@PathVariable("id")Integer id){
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadorPorId(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PatrocinadorDTOResponse> atualizarPatrocinador(@PathVariable("id")Integer id,
    @RequestBody PatrocinadorDTORequest request){
        return ResponseEntity.ok(this.patrocinadorService.atualizarPatrocinador(id,request));
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity deletarPatrocinador(@PathVariable("id")Integer id){
        this.patrocinadorService.apagarPatrocinador(id);
        return ResponseEntity.noContent().build();
    }
}
