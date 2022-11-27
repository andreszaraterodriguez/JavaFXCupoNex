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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLInicioController implements Initializable {

    @FXML
    private TextField tfNumPersonal;
    @FXML
    private PasswordField pfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickIniciarSesion(ActionEvent event) {
        String user = tfNumPersonal.getText();
        String password = pfPassword.getText();
        
        if(!user.isEmpty() && !password.isEmpty()){
            verificarInicioSesion(user, password);
        }else{
            Utilidades.mostrarAlertaSimple("Campos requeridos...", "Es necesario ingresar el ", Alert.AlertType.WARNING);
        }
    }
    
    private void verificarInicioSesion(String user, String password){
      
    }
    
}
