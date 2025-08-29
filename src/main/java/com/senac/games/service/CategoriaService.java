package com.senac.games.service;

import com.senac.games.dto.request.CategoriaDTORequest;
import com.senac.games.dto.response.CategoriaDTOResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    private ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest){
        Categoria categoria = this.modelMapper.map(categoriaDTORequest,Categoria.class);
        Categoria categoriaSalva = this.categoriaRepository.save(categoria);
        CategoriaDTOResponse categoriaDTOResponse = this.modelMapper.map(categoriaSalva,CategoriaDTOResponse.class);
        return categoriaDTOResponse;
    }

    public List<Categoria> listarCategorias(){
        return this.categoriaRepository.findAll();
    }
}
