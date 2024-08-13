package com.josemorejon.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemorejon.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
