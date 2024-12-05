package com.example.examenfinalspringangular.service.impl;

import com.example.examenfinalspringangular.entity.Categoria;
import com.example.examenfinalspringangular.repository.CategoriaRepository;
import com.example.examenfinalspringangular.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> obtenerTodos() {
        // Obtiene todas las categorías de la base de datos
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerPorId(Long id) {
        // Busca una categoría por su ID
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return categoria.get();
        }
        throw new IllegalArgumentException("Categoría no encontrada con ID: " + id);
    }

    @Override
    public Categoria crearProducto(Categoria categoria) {
        // Crea una nueva categoría
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarProducto(Long id, Categoria categoria) {
        // Actualiza una categoría existente
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isPresent()) {
            Categoria categoriaActualizada = categoriaExistente.get();
            categoriaActualizada.setNombre(categoria.getNombre());
            return categoriaRepository.save(categoriaActualizada);
        }
        throw new IllegalArgumentException("Categoría no encontrada con ID: " + id);
    }

    @Override
    public void eliminarCategoria(Long id) {
        // Elimina una categoría por su ID
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Categoría no encontrada con ID: " + id);
        }
    }
}