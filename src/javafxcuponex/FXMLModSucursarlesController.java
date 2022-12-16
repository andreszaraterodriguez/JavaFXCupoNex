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
    private ComboBox<Usuario> cbEncargado;
    
    private ObservableList<Usuario> usuarios;
    @FXML
    private ComboBox<Empresa> cbEmpresas;
       private ObservableList<Empresa> empresas;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       llenarCombobox();
    }    

    
    private void llenarCombobox(){
        usuarios = FXCollections.observableArrayList();
        usuarios.addAll(recuperaAdmin());
        cbEncargado.setItems(usuarios);
        
        empresas = FXCollections.observableArrayList();
        empresas.addAll(recuperaEmpresas());
        cbEmpresas.setItems(empresas);
        
    }
      private List<Empresa> recuperaEmpresas(){
        List<Empresa> empresas = new ArrayList();
        try{
              String url = Constantes.URL_BASE +"empresa/leerTodas";
              String resultado = ConsumirServiciosWeb.get(url); 
              Gson gson = new Gson();
              Type tipoListaEmpresas =  new TypeToken<List<Empresa>>(){}.getType(); 
              empresas  = gson.fromJson(resultado, tipoListaEmpresas);  
            
        } catch(Exception e){
            e.printStackTrace();
        }
       return empresas;
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

    @FXML
    private void clickGuardar(ActionEvent event) {
        try{
             String nombre = tfNombre.getText();
        String direccion = tfDireccion.getText();
        String codigoP = tfCodP.getText();
        String colonia = tfColonia.getText();
        String ciudad = tfCiudad.getText();
        String telefono = tfTelefono.getText();
  
        Double longitud = Double.parseDouble(tfLongitud.getText());
        Double latitud = Double.parseDouble(tfLatitud.getText());
        Usuario usuarioEncargado = cbEncargado.getValue();
        Empresa empresaSelect = cbEmpresas.getValue();
        
        if(usuarioEncargado == null){
         Utilidades.mostrarAlertaSimple("Complete datos...", "Seleccione encargado..", Alert.AlertType.INFORMATION);
         return;
        }
         if(empresaSelect == null){
         Utilidades.mostrarAlertaSimple("Complete datos...", "Seleccione empresa..", Alert.AlertType.INFORMATION);
         return;
        }
        
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        sucursal.setDireccion(direccion);
        sucursal.setCodigoPostal(codigoP);
        sucursal.setColonia(colonia);
        sucursal.setCiudad(ciudad);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setEncargadoId(usuarioEncargado.getId());
        sucursal.setEmpresaId(empresaSelect.getId());
        registrarS(sucursal);
        } catch (Exception e){
           Utilidades.mostrarAlertaSimple("Algo salio mal...", e.getMessage(), Alert.AlertType.INFORMATION);

        }
   
        
        
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
