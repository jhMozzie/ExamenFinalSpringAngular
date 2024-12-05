package com.example.examenfinalspringangular.service;

import com.example.examenfinalspringangular.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> obtenerTodos();
    Categoria obtenerPorId(Long id);
    Categoria crearProducto(Categoria categoria);
    Categoria actualizarProducto(Long id, Categoria categoria);
    void eliminarCategoria(Long id);
}
