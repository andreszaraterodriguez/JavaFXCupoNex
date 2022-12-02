/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxcuponex.pojos.Administrador;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLAdminModAdminController implements Initializable {

    @FXML
    private TableView<Administrador> tbUsuarios;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidoP;
    @FXML
    private TableColumn<?, ?> colApellidoM;
    @FXML
    private TableColumn<?, ?> colCorreo;
    
    private ObservableList<Administrador> listaAdministrador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void configurarTabla(){
        listaAdministrador = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory ("nombre"));
        colApellidoP.setCellValueFactory(new PropertyValueFactory("apellidoP"));
        colApellidoM.setCellValueFactory(new PropertyValueFactory ("apellidoM"));
        
    }

    @FXML
    private void clickEditarUsuario(ActionEvent event) {
    }

    @FXML
    private void clickEliminarUsuario(ActionEvent event) {
    }

    @FXML
    private void clickAgregarUsuario(ActionEvent event) {
    }
    
}
