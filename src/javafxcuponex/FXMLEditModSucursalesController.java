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
    @FXML
    private ComboBox<?> cbEncargado;
    @FXML
    private ComboBox<?> cbEmpresas;
    
    private Sucursal sucursal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clickGuardar(ActionEvent event) {
        
        String nombre = tfNombre.getText();
        String direccinn= tfDireccion.getText();
        String codigoP = tfCodP.getText();
        String colonia = tfColonia.getText();
        String ciudad = tfCiudad.getText();
        String telefono = tfTelefono.getText();
        
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        sucursal.setDireccion(direccinn);
        sucursal.setCodigoPostal(codigoP);
        sucursal.setColonia(colonia);
        sucursal.setCiudad(ciudad);
        sucursal.setTelefono(telefono);
    }
    
     public void recibirInfo(Sucursal sucursal){
      this.sucursal = sucursal;
      tfNombre.setText(sucursal.getNombre());
      tfDireccion.setText(sucursal.getDireccion());
      tfCodP.setText(sucursal.getCodigoPostal());
      tfColonia.setText(sucursal.getColonia());
      tfCiudad.setText(sucursal.getCiudad());
      tfTelefono.setText(sucursal.getTelefono());
      
    }
    
}
