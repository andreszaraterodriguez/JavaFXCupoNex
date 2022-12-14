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
import javafxcuponex.pojos.Empresa;
import javafxcuponex.pojos.Respuesta;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLModEmpresaController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreComercial;
    
    private TextField tfEmail;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCodigoP;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfPaginaW;
    @FXML
    private TextField tfRfc;
    @FXML
    private TextField tfEstatus;
    @FXML
    private TextField tfRepresentanteL;
    @FXML
    private TextField tfCorreo;

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
        String nombre =tfNombre.getText();
        String nombreC = tfNombreComercial.getText();
        String correo = tfCorreo.getText();
        String direccion = tfDireccion.getText();
        String codigoP = tfCodigoP.getText();
        String ciudad = tfCiudad.getText();
        String telefono = tfTelefono.getText();
        String paginaW = tfPaginaW.getText();
        String rfc = tfRfc.getText();
        String estatus = tfEstatus.getText();
    //    String repre = tfRepresentanteL();
     
        
        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreC);
        empresa.setCorreo(correo);
        empresa.setDireccion(direccion);
        empresa.setCodigoPostal(codigoP);
        empresa.setCiudad(ciudad);
        empresa.setTelefono(telefono);
        empresa.setPaginaWeb(paginaW);
        empresa.setRfc(rfc);
        empresa.setEstatus(estatus);
       // empresa.setRepresentanteLegal(representanteLegal);
        verificarCreacionEmpresa(empresa);
        
    }
     private void verificarCreacionEmpresa(Empresa empresa){
      try{
          
          String url = Constantes.URL_BASE + "empresa/insertar";
          
          Gson gson = new Gson();
          String parametros = gson.toJson(empresa);
          String resultado = ConsumirServiciosWeb.post(url, parametros);
          System.out.println(parametros);
          Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
          if(!respuesta.getError()){
              Utilidades.mostrarAlertaSimple("Empresa registrada...", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
             
          }else{
              Utilidades.mostrarAlertaSimple("No se pudo registrar la empresa...", respuesta.getMensaje(), Alert.AlertType.ERROR);
          }
       
         System.out.println(resultado);
          
      }catch (Exception e){
          
          e.printStackTrace();
          Utilidades.mostrarAlertaSimple("Error de conexion", e.getMessage(), Alert.AlertType.ERROR);
      }
    }
    
    
    
    
}
