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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConsumirServiciosWeb;
import javafxcuponex.pojos.Respuesta;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLAdminModAdminController implements Initializable {

    @FXML
    private TableView<Usuario> tbUsuarios;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidoP;
    @FXML
    private TableColumn<?, ?> colApellidoM;
    @FXML
    private TableColumn<?, ?> colCorreo;
    
    private ObservableList<Usuario> listaAdministrador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInfoUsuarioWS();
    }    
    
    
    
    private void configurarTabla(){
        listaAdministrador = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory ("nombre"));
        colApellidoP.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoM.setCellValueFactory(new PropertyValueFactory ("apellidoMaterno"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    private void cargarInfoUsuarioWS(){
        String urlWS = Constantes.URL_BASE+"usuario/leerTodos";
        try{
            String jsonRespuesta = ConsumirServiciosWeb.get(urlWS);
            Gson gson = new Gson();
            Type tipoListaAdministrador =  new TypeToken<List<Usuario>>(){}.getType(); 
            List usuarioWS = gson.fromJson(jsonRespuesta, tipoListaAdministrador);           
            listaAdministrador.addAll(usuarioWS);
            tbUsuarios.setItems(listaAdministrador);
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error en conexion ", "Error al consultar", Alert.AlertType.ERROR);
        }
    }
    
    
    
    

    @FXML
    private void clickEditarUsuario(ActionEvent event) {
         int selectedTrow= tbUsuarios.getSelectionModel().getSelectedIndex();
        if(selectedTrow== -1){
          Utilidades.mostrarAlertaSimple("Error ", "No seleciono ningun usuario", Alert.AlertType.ERROR);
          return;
        }
        
        Usuario usuario= listaAdministrador.get(selectedTrow);
            try{
           FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLEditModAdmin.fxml"));
           Parent vistaEditar = cargador.load();
           FXMLEditModAdminController controlador = cargador.getController();
           controlador.recibirInfo(usuario);
           Scene editUs = new Scene(vistaEditar);
           Stage stage = new Stage();
           stage.setScene(editUs);
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.showAndWait();
        } catch(Exception e){
            e.printStackTrace();
             Utilidades.mostrarAlertaSimple("Error ", e.getMessage(), Alert.AlertType.ERROR);

        }
    }
  

    @FXML
    private void clickEliminarUsuario(ActionEvent event) {
        int selectedTrow= tbUsuarios.getSelectionModel().getSelectedIndex();
        if(selectedTrow== -1){
          Utilidades.mostrarAlertaSimple("Error ", "No seleciono ningun usuario", Alert.AlertType.ERROR);
          return;
        }
        int usuarioSeleccionadoId= listaAdministrador.get(selectedTrow).getId();
        Boolean result=Utilidades.mostrarAlertaConexion("Eliminar Usuario", "Seguro que desea eliminar usuario", Alert.AlertType.NONE);
        if(result){
            eliminarUsuario(usuarioSeleccionadoId);
        }
    }
    
    private void eliminarUsuario(int id){
            String urlWS = Constantes.URL_BASE+"usuario/eliminar/"+id;
        try{
            String jsonRespuesta = ConsumirServiciosWeb.delete(urlWS);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(jsonRespuesta, Respuesta.class); 
            if(respuesta.getError()){
                  Utilidades.mostrarAlertaSimple("Error ", respuesta.getMensaje() , Alert.AlertType.ERROR);
                  return;
            }
            Utilidades.mostrarAlertaSimple("Realizado.. ", respuesta.getMensaje() , Alert.AlertType.INFORMATION);

        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error en conexion ", "Error al consultar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clickAgregarUsuario(ActionEvent event) {
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLModAdmin.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        } catch(Exception e){
            
        }
    }

    
}
