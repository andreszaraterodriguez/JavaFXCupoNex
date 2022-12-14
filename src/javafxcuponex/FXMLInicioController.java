
package javafxcuponex;


import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConsumirServiciosWeb;
import javafxcuponex.pojos.RespuestaLogin;
import javafxcuponex.util.Constantes;
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
      try{
          
          String url = Constantes.URL_BASE + "acceso/admin";
          String parametros = "noPersonal="+user+"&password="+password;
          String resultado = ConsumirServiciosWeb.post(url, parametros);
          
          Gson gson = new Gson();
          RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);
          if(!respuesta.getError()){
              Utilidades.mostrarAlertaSimple("Usuario verificado...", "Bienbenido"+ respuesta.getNombre()+"al sistema", Alert.AlertType.INFORMATION);
          }else{
              Utilidades.mostrarAlertaSimple("usuario incorrecto...", respuesta.getMensaje(), Alert.AlertType.ERROR);
          }
          
          
      }catch (Exception e){
          Utilidades.mostrarAlertaSimple("Error de conexion", e.getMessage(), Alert.AlertType.ERROR);
      }
    }
    
    private void irPantallaPrincipal(){
        try{
            Parent vistaPrincipal = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(vistaPrincipal);
           Stage escenarioBase = (Stage) tfNumPersonal.getScene().getWindow();
           escenarioBase.setScene(escenaPrincipal);
           escenarioBase.show();
        }catch (IOException ex){
             Utilidades.mostrarAlertaSimple("Error...", "Error al mostrar la pantalla principal...", Alert.AlertType.NONE);
        }
    }
    
    
    
    
    
}
