package com.senac.games.controller;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.entity.Premio;
import com.senac.games.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/premio")
@Tag(name="Premio", description="API para gerenciamento de premios")
public class PremioController {

    private PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar premios", description = "Endpoint para listar todos os premios")
    public ResponseEntity<List<Premio>> listarPremios(){
        return ResponseEntity.ok(premioService.listarPremios());
    }

    @PostMapping("/criar")
    public ResponseEntity<PremioDTOResponse> criarPremio(@Valid @RequestBody PremioDTORequest premio){
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.criarPremio(premio));
    }
}
