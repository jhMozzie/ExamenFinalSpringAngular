package com.example.examenfinalspringangular.repository;

import com.example.examenfinalspringangular.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
