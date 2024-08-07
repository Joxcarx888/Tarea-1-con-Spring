package com.josemorejon.webapp.biblioteca.service;

import java.util.List;

import com.josemorejon.webapp.biblioteca.model.Empleado;

public interface IEmpleadoService {
    public List<Empleado> listarEmpleados();

    public Empleado guardarEmpleado(Empleado empleado);

    public Empleado buscarEmpleadoPorId(Long id);

    public void eliminarEmpleado(Empleado empleado);
}
