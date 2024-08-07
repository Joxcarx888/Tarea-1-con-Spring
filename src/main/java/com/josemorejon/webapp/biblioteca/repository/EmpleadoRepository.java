package com.josemorejon.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemorejon.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
