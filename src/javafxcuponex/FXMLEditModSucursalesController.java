/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafxcuponex.pojos.Sucursal;
import javafxcuponex.pojos.Usuario;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLEditModSucursalesController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCodP;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfLatitud;
    @FXML
    private TextField tfLongitud;
    private Sucursal sucursal;
    @FXML
    private ComboBox<?> cbEncargado;
    @FXML
    private ComboBox<?> cbEmpresas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clickGuardar(ActionEvent event) {
    }
    
     public void recibirInfo(Sucursal sucursal){
      this.sucursal = sucursal;
      
    }
    
}
