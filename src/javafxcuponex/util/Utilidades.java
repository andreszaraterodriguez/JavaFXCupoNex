
package javafxcuponex.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class Utilidades {
     public static void mostrarAlertaSimple(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
        
    }
       public static Boolean mostrarAlertaConexion(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        
     Optional<ButtonType>result=  alerta.showAndWait();
        if(!result.isPresent()){
            return false;
        }
        return result.get()==ButtonType.OK;
            
        
    }
    
}
