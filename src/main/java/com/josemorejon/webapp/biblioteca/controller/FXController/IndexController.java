package com.josemorejon.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josemorejon.webapp.biblioteca.model.Categoria;
import com.josemorejon.webapp.biblioteca.service.CategoriaService;
import com.josemorejon.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class IndexController implements Initializable {
    
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre;
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn colId, colNombre;
    @FXML
    Button btnGuardar,btnEliminar,btnLimpiar;

    @Autowired
    CategoriaService categoriaService;

    @Override
    public void initialize(URL url,ResourceBundle resources){
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarCategoria();
            }else{
                editarCategoria();
            }
        }else if(event.getSource() == btnEliminar){
            eliminarCategoria();
        }else if(event.getSource() == btnLimpiar){
            limpiarTextField();
        }
    }

    public void cargarDatos() {
        tblCategorias.getItems().clear();
        tblCategorias.setItems(listarCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria"));
    }
    

    public ObservableList<Categoria> listarCategorias(){
        return FXCollections.observableArrayList(categoriaService.listarCategorias());
    }

    public void cargarTextField(){
        Categoria categoria = (Categoria)tblCategorias.getSelectionModel().getSelectedItem();
        if(categoria != null){
            tfId.setText(Long.toString(categoria.getId()));
            tfNombre.setText(categoria.getNombreCategoria());
        }
    }

    public void limpiarTextField(){
        tfId.clear();
        tfNombre.clear();
    }

    

    public void agregarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void editarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }

    
    
}
