/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLEditModAdminController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoP;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfApellidoM;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfFechaN;
    private Usuario usuario;
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
        String apellidop = tfApellidoP.getText();
        String apellidoM = tfApellidoM.getText();
        String correo = tfCorreo.getText();
        String password = tfPassword.getText();
        String direccion =tfDireccion.getText();
        String fechaN = tfFechaN.getText();
        String telefono = tfTelefono.getText();
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidop);
        usuario.setApellidoMaterno(apellidoM);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setFechaDeNacimiento(fechaN);
        usuario.setAdministracion(Boolean.FALSE);
        //verificarCreacionU(usuario);
    }
     
    public void recibirInfo(Usuario usuario){
        this.usuario= usuario;
        tfNombre.setText(usuario.getNombre());
        tfApellidoP.setText(usuario.getApellidoPaterno());
        tfApellidoM.setText(usuario.getApellidoMaterno());
        tfCorreo.setText(usuario.getCorreo());
        tfPassword.setText(usuario.getPassword());
        tfDireccion.setText(usuario.getDireccion());
        tfFechaN.setText(usuario.getFechaDeNacimiento());
        tfTelefono.setText(usuario.getTelefono());
    }
    
    private void loadInfoAdmin(){
       String url = Constantes.URL_BASE +"usuario/" + this;
       
       try{
           
           
       }catch(Exception e){
           
       }
    }
    
}
