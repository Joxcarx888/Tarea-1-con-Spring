package com.josemorejon.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.josemorejon.webapp.biblioteca.model.Cliente;
import com.josemorejon.webapp.biblioteca.service.ClienteService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RestController
@RequestMapping("cliente")
public class ClienteController{
    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping("/")
    public ResponseEntity<Map<String,String >> agregarCliente(@RequestBody Cliente cliente) {
        Map<String,String> response = new HashMap<>();
        try {
            clienteService.guardarCliente(cliente);
            response.put("message","Cliente agregado con exito" );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado el Cliente" );
            return ResponseEntity.badRequest().body(response);
        }
    }


    
    
    
}
