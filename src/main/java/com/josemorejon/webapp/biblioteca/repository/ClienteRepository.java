package com.josemorejon.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemorejon.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
