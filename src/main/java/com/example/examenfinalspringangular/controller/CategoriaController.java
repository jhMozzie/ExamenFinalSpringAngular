package com.example.examenfinalspringangular.controller;

import com.example.examenfinalspringangular.entity.Categoria;
import com.example.examenfinalspringangular.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*") // Permite el acceso desde cualquier dominio (útil para Angular)
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        // Devuelve todas las categorías
        return categoriaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoria(@PathVariable Long id) {
        // Devuelve una categoría por ID
        return categoriaService.obtenerPorId(id);
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        // Crea una nueva categoría
        return categoriaService.crearProducto(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        // Actualiza una categoría existente
        return categoriaService.actualizarProducto(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Long id) {
        // Elimina una categoría por ID
        categoriaService.eliminarCategoria(id);
    }
}