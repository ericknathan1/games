package com.senac.games.controller;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.service.InscricaoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    private InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }
    @PostMapping("/criar")
    public ResponseEntity<InscricaoDTOResponse> criarInscricao(@Valid @RequestBody InscricaoDTORequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.criarInscricao(request));
    }
    @GetMapping("/listar")
    public ResponseEntity<List<InscricaoDTOResponse>> listarInscricao(){
        return ResponseEntity.ok(service.listarInscricao());
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<InscricaoDTOResponse> listarPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.listarInscricaoPorId(id));
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<InscricaoDTOResponse> atualizarInscricao(@PathVariable("id") Integer id,
    @RequestBody InscricaoDTORequest request){
        return ResponseEntity.ok(this.service.atualizarInscricao(id, request));
    }
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity apagarInscricao(@PathVariable("id") Integer id){
        service.apagarInscricao(id);
        return ResponseEntity.noContent().build();
    }
}
