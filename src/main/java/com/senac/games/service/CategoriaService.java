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
    public CategoriaService(CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }
    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest){
        Categoria categoria = this.modelMapper.map(categoriaDTORequest,Categoria.class);
        Categoria categoriaSalva = this.categoriaRepository.save(categoria);
        CategoriaDTOResponse categoriaDTOResponse = this.modelMapper.map(categoriaSalva,CategoriaDTOResponse.class);
        return categoriaDTOResponse;
    }
    public List<Categoria> listarCategorias(){
        return this.categoriaRepository.listarCategoria();
    }

    public CategoriaDTOResponse retornarCategoria (Integer id){
        return  modelMapper.map(this.categoriaRepository.obterCategoriaPorId(id),CategoriaDTOResponse.class);
    }
    public CategoriaDTOResponse atualizarCategoria(Integer categoriaId, CategoriaDTORequest request){
        Categoria categoria = this.categoriaRepository.obterCategoriaPorId(categoriaId);
        if (categoria != null){
            modelMapper.map(request,categoria);
            Categoria categoriaSalvo = this.categoriaRepository.save(categoria);
            return modelMapper.map(categoriaSalvo,CategoriaDTOResponse.class);
        }else{
            throw new IllegalArgumentException("Categoria não existe");
        }
    }

    public void apagarCategoria(Integer categoriaId){
        this.categoriaRepository.apagarCategoria(categoriaId);
    }
}
