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
import javafx.scene.control.TextField;
import javafxcuponex.pojos.Empresa;
import javafxcuponex.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLEditModEmpresaController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfRepresentanteL;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCodigoP;
    @FXML
    private TextField tfPaginaW;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfRfc;
    @FXML
    private TextField tfEstatus;
    
    private Empresa empresa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void recibirInfo(Empresa empresa){
        this.empresa = empresa;
        tfNombre.setText(empresa.getNombre());
        tfNombreComercial.setText(empresa.getNombreComercial());
        tfCorreo.setText(empresa.getCorreo());
        tfDireccion.setText(empresa.getDireccion());
        tfCodigoP.setText(empresa.getCodigoPostal());
        tfPaginaW.setText(empresa.getPaginaWeb());
        tfTelefono.setText(empresa.getTelefono());
        tfCiudad.setText(empresa.getCiudad());
        tfRfc.setText(empresa.getRfc());
    }

    @FXML
    private void clickGuardar(ActionEvent event) {
        String nombre = tfNombre.getText();
        String nombreC = tfNombreComercial.getText();
        String correo =  tfCorreo.getText();
        String direccion = tfDireccion.getText();
        String codigoP = tfCodigoP.getText();
        String paginaW = tfPaginaW.getText();
        String telefono = tfTelefono.getText();
        String ciudad = tfCiudad.getText();
        String rfc = tfRfc.getText();
        
        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreC);
        empresa.setCorreo(correo);
        empresa.setDireccion(direccion);
        empresa.setCodigoPostal(codigoP);
        empresa.setPaginaWeb(paginaW);
        empresa.setTelefono(telefono);
        empresa.setCiudad(ciudad);
        empresa.setRfc(rfc);
    }
    
}
