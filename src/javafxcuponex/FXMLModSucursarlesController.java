/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafxcuponex.modelo.ConsumirServiciosWeb;
import javafxcuponex.pojos.Respuesta;
import javafxcuponex.pojos.Sucursal;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLModSucursarlesController implements Initializable {

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
    private TextField tfNombreE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickCancelar(ActionEvent event) {
    }

    @FXML
    private void clickGuardar(ActionEvent event) {
        String nombre = tfNombre.getText();
        String direccion = tfDireccion.getText();
        String codigoP = tfCodP.getText();
        String colonia = tfColonia.getText();
        String ciudad = tfCiudad.getText();
        String telefono = tfTelefono.getText();
        String latitud = tfLatitud.getText();
       // Double longitud = tfLongitud
       // String nombreE = tfNombreE
        
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        sucursal.setDireccion(direccion);
        sucursal.setCodigoPostal(codigoP);
        sucursal.setColonia(colonia);
        sucursal.setCiudad(ciudad);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(Double.NaN);
        sucursal.setLongitd(Double.NaN);
        registrarS(sucursal);
        
        
    }
    
     private void registrarS(Sucursal sucursal){
      try{
          
          String url = Constantes.URL_BASE + "sucursal/insertar";
          
          Gson gson = new Gson();
          String parametros = gson.toJson(sucursal);
          String resultado = ConsumirServiciosWeb.post(url, parametros);
          System.out.println(parametros);
          Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
          if(!respuesta.getError()){
              Utilidades.mostrarAlertaSimple("Sucursal registrada..", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
             
          }else{
              Utilidades.mostrarAlertaSimple("No se puedo registrar...", respuesta.getMensaje(), Alert.AlertType.ERROR);
          }
       
         System.out.println(resultado);
          
      }catch (Exception e){
          
          e.printStackTrace();
          Utilidades.mostrarAlertaSimple("Error de conexion", e.getMessage(), Alert.AlertType.ERROR);
      }
    }
    
}
