package com.senac.games.controller;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.service.CategoriaService;
import com.senac.games.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jogo")
@Tag(name="Jogo", description="API para gerenciamento de jogos")
public class JogoController {
    private JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<JogoDTOResponse> criarJogo(@RequestBody JogoDTORequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.jogoService.criarJogo(request));
    }

    @GetMapping("/listar")
    @Operation(summary="Listar jogos", description = "Endpoint para listar todos os jogos")
    public ResponseEntity<List<Jogo>> listarJogos(){
        return ResponseEntity.ok(jogoService.listarJogos());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<JogoDTOResponse> listarJogoPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.jogoService.listarJogoId(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<JogoDTOResponse> atualizarJogo(@PathVariable("id")Integer id,
                                                         @RequestBody JogoDTORequest request){
        return ResponseEntity.ok(this.jogoService.atualizarJogo(id,request));
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity apagarJogo(@PathVariable("id")Integer id){
        this.jogoService.apagarJogo(id);
        return ResponseEntity.noContent().build();
    }
}
