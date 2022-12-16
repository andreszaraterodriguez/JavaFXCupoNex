/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafxcuponex.modelo.ConsumirServiciosWeb;
import javafxcuponex.pojos.Empresa;
import javafxcuponex.pojos.Respuesta;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

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
    
    private Empresa empresa;
    @FXML
    private ComboBox<Usuario> cbRepresentannte;
    private ObservableList<Usuario> respresentantes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarCombobox();
    }    
     private void llenarCombobox(){
        respresentantes = FXCollections.observableArrayList();
        respresentantes.addAll(recuperaAdmin());
        cbRepresentannte.setItems(respresentantes);
        
        
    }
        private List<Usuario> recuperaAdmin(){
        List<Usuario> administradores = new ArrayList();
        try{
              String url = Constantes.URL_BASE +"usuario/leerTodos";
              String resultado = ConsumirServiciosWeb.get(url); 
              Gson gson = new Gson();
              Type tipoListaAdministrador =  new TypeToken<List<Usuario>>(){}.getType(); 
              List<Usuario> aux  = gson.fromJson(resultado, tipoListaAdministrador);  
              System.out.println(aux);
              for(int i=0; i < aux.size();i++){
                  Usuario usuario = aux.get(i);
                  if(usuario.getAdministracion()){
                      administradores.add(usuario);
                  }
              }
        } catch(Exception e){
            e.printStackTrace();
        }
       return administradores;
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
       // tfRfc.setText(empresa.getRfc());
       for(int i=0; i<respresentantes.size();i++){
           if(respresentantes.get(i).getId()==this.empresa.getRepresentanteLegalId()){
               cbRepresentannte.getSelectionModel().select(i);
               break;
           }
       }
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
        //String rfc = tfRfc.getText();
        
      
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreC);
        empresa.setCorreo(correo);
        empresa.setDireccion(direccion);
        empresa.setCodigoPostal(codigoP);
        empresa.setPaginaWeb(paginaW);
        empresa.setTelefono(telefono);
        empresa.setCiudad(ciudad);
       // empresa.setRfc(rfc);
        verificarCreacionEmpresa(empresa);
    }
    
       private void verificarCreacionEmpresa(Empresa empresa){
      try{
          
          String url = Constantes.URL_BASE + "empresa/actualizar/"+empresa.getId();
          
          Gson gson = new Gson();
          String parametros = gson.toJson(empresa);
          String resultado = ConsumirServiciosWeb.put(url, parametros);
          System.out.println(parametros);
          Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
          if(!respuesta.getError()){
              Utilidades.mostrarAlertaSimple("Empresa actualizada...", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
             
          }else{
              Utilidades.mostrarAlertaSimple("No se pudo actualizada...", respuesta.getMensaje(), Alert.AlertType.ERROR);
          }
       
         System.out.println(resultado);
          
      }catch (Exception e){
          
          e.printStackTrace();
          Utilidades.mostrarAlertaSimple("Error de conexion", e.getMessage(), Alert.AlertType.ERROR);
      }
    }
    
}
