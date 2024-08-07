package com.josemorejon.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemorejon.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
