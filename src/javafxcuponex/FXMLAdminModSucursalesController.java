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
import javafxcuponex.pojos.Sucursal;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLAdminModSucursalesController implements Initializable {

    @FXML
    private TableView<Sucursal> tbSucursales;
    @FXML
    private TextField tfBusquedaSucursal;
    
    
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TableColumn<?, ?> colCodigoP;
    @FXML
    private TableColumn<?, ?> colColonia;
    @FXML
    private TableColumn<?, ?> colCiudad;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colLatitud;
    @FXML
    private TableColumn<?, ?> colLongitud;

    
    
     private ObservableList<Sucursal> listaSucursal;
    @FXML
    private TableColumn<?, ?> colNombreEncargado;
    @FXML
    private TableColumn<?, ?> colNombreEmpresa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOconfigurarTabla
        configurarTabla();
        cargarInfoUsuarioWS();
    }    
      private void configurarTabla(){
      listaSucursal = FXCollections.observableArrayList();
      colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
      colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
      colCodigoP.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
      colCiudad.setCellValueFactory(new PropertyValueFactory("ciudad"));
      colColonia.setCellValueFactory(new PropertyValueFactory("colonia"));
      colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
      colLatitud.setCellValueFactory(new PropertyValueFactory ("latitud"));
      colLongitud.setCellValueFactory(new PropertyValueFactory("longitud"));
      colNombreEncargado.setCellValueFactory(new PropertyValueFactory ("nombreEncargado"));
      colNombreEmpresa.setCellValueFactory(new PropertyValueFactory("nombreEmpresa"));
   
    }
    
        private void cargarInfoUsuarioWS(){
       String urlWS = Constantes.URL_BASE+"sucursal/leerTodas";
        try{
            String jsonRespuesta = ConsumirServiciosWeb.get(urlWS);
            System.out.println(jsonRespuesta);
            Gson gson = new Gson();
            Type tipoListaAdministrador =  new TypeToken<List<Sucursal>>(){}.getType(); 
            List sucursalWS = gson.fromJson(jsonRespuesta, tipoListaAdministrador);           
            listaSucursal.addAll(sucursalWS);
            tbSucursales.setItems(listaSucursal);
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error en conexion ", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void clickEditar(ActionEvent event) {
        int selectedTrow= tbSucursales.getSelectionModel().getSelectedIndex();
        if(selectedTrow== -1){
          Utilidades.mostrarAlertaSimple("Error ", "No seleciono ninguno....", Alert.AlertType.ERROR);
          return;
        }
        
        Sucursal sucursal = listaSucursal.get(selectedTrow);
            try{
           FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLEditModSucursales.fxml"));
           Parent vistaEditar = cargador.load();
           FXMLEditModSucursalesController controlador = cargador.getController();
           controlador.recibirInfo(sucursal);
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
    private void clickEliminar(ActionEvent event) {
        int selectedTrow= tbSucursales.getSelectionModel().getSelectedIndex();
        if(selectedTrow== -1){
          Utilidades.mostrarAlertaSimple("Error ", "No seleciono ninguna sucursal..", Alert.AlertType.ERROR);
          return;
        }
        int sucursalSeleccionadoId= listaSucursal.get(selectedTrow).getId();
        Boolean result=Utilidades.mostrarAlertaConexion("Eliminar sucursal", "Seguro que desea eliminar sucursal", Alert.AlertType.NONE);
        if(result){
            eliminarSucursal(sucursalSeleccionadoId);
        }
    }
       private void eliminarSucursal(int id){
            String urlWS = Constantes.URL_BASE+"sucursal/eliminar/"+id;
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
    private void clickAgregar(ActionEvent event) {
            try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLModSucursarles.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        } catch(Exception e){
            
        }
    }
    
}
