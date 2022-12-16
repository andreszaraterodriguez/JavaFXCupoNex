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
import javafxcuponex.pojos.Promocion;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLAdminModPromocionesController implements Initializable {

    @FXML
    private TableView<Promocion> tbPromociones;
    @FXML
    private TextField tbBusquedaPromociones;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colDescripcion;
    @FXML
    private TableColumn<?, ?> colFechaI;
    @FXML
    private TableColumn<?, ?> colFechaT;
    @FXML
    private TableColumn<?, ?> colTipoP;
    @FXML
    private TableColumn<?, ?> colCosto;
    @FXML
    private TableColumn<?, ?> colCategoria;
    
     private ObservableList<Promocion> listaPromocion;

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
       listaPromocion = FXCollections.observableArrayList();
       colNombre.setCellValueFactory(new PropertyValueFactory ("nombre"));
       colDescripcion.setCellValueFactory(new PropertyValueFactory ("descripcion"));
       colFechaI.setCellValueFactory(new PropertyValueFactory ("fechaDeInicio"));
       colFechaT.setCellValueFactory(new PropertyValueFactory ("fechaDeTermino"));
       colCosto.setCellValueFactory(new PropertyValueFactory ("costo"));
       colCategoria.setCellValueFactory(new PropertyValueFactory ("categoria"));
       colTipoP.setCellValueFactory(new PropertyValueFactory("tipoDePromocion"));
       
    }
      
       private void cargarInfoUsuarioWS(){
        String urlWS = Constantes.URL_BASE+"promocion/leerTodas";
        try{
            String jsonRespuesta = ConsumirServiciosWeb.get(urlWS);
            Gson gson = new Gson();
            Type tipoListaAdministrador =  new TypeToken<List<Promocion>>(){}.getType(); 
            List promocionWS = gson.fromJson(jsonRespuesta, tipoListaAdministrador);           
            listaPromocion.addAll(promocionWS);
            tbPromociones.setItems(listaPromocion);
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error en conexion ", "Error al consultar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clickEditado(ActionEvent event) {
        int selectedTrow= tbPromociones.getSelectionModel().getSelectedIndex();
        if(selectedTrow== -1){
          Utilidades.mostrarAlertaSimple("Error ", "No seleciono ningun usuario", Alert.AlertType.ERROR);
          return;
        }
        
        Promocion promocion = listaPromocion.get(selectedTrow);
            try{
           FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLEditModPromociones.fxml"));
           Parent vistaEditar = cargador.load();
           FXMLEditModPromocionesController controlador = cargador.getController();
           controlador.recibirInfo(promocion);
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
        
    }

    @FXML
    private void clickAgregar(ActionEvent event) {
                try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLModPromociones.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
