package com.josemorejon.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.josemorejon.webapp.biblioteca.model.Categoria;
import com.josemorejon.webapp.biblioteca.service.CategoriaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RestController
@RequestMapping("")
public class CategoriaController{
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias(){
        return categoriaService.listarCategorias();
    }


    @GetMapping("/categoria")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id){
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    @PostMapping("/categoria")
    public ResponseEntity<Map<String,String >> agregarCategoria(@RequestBody Categoria categoria) {
        Map<String,String> response = new HashMap<>();
        try {
            if(!categoriaService.verificarCategoriaDuplicada(categoria)){
                categoriaService.guardarCategoria(categoria);
                response.put("message","Categoria agregada con exito" );
                return ResponseEntity.ok(response);
            }else{
                response.put("message" ,"error" );
                response.put("err" ,"Algo de Valor unico esta siendo duplicado" );
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado la Categoria" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/categoria")
    public ResponseEntity <Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNew){
        Map<String,String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNew.getNombreCategoria());

            if(!categoriaService.verificarCategoriaDuplicada(categoria)){
                categoriaService.guardarCategoria(categoria);
                response.put("message", "Se he modificado correctamente");
                return ResponseEntity.ok(response);
            }else{
                response.put("message" ,"error" );
                response.put("err" ,"Algo de Valor unico esta siendo duplicado" );
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha agregado la Categoria" );
            return ResponseEntity.badRequest().body(response);
        }


    }

    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);

            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Se ha elimnado con exito");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message" ,"error" );
            response.put("err" ,"No se ha eliminado con exito" );
            return ResponseEntity.badRequest().body(response);
        }
    }

    
}


