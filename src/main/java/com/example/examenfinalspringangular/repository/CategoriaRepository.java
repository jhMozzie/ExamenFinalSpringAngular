package com.example.examenfinalspringangular.repository;

import com.example.examenfinalspringangular.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
