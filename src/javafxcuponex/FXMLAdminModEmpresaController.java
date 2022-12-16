
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
import javafxcuponex.pojos.Empresa;
import javafxcuponex.pojos.Respuesta;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;


public class FXMLAdminModEmpresaController implements Initializable {

    @FXML
    private TableView<Empresa> tbEmpresas;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colNombreC;
    
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TableColumn<?, ?> colCodigoP;
    @FXML
    private TableColumn<?, ?> colCiudad;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colPaginaW;
    @FXML
    private TableColumn<?, ?> colRfc;
    
    private ObservableList<Empresa> listaEmpresas;
    @FXML
    private TextField wBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInfoEmpresaWS();
    }    
    
    private void configurarTabla(){
        listaEmpresas =  FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory ("nombre"));
        colNombreC.setCellValueFactory(new PropertyValueFactory ("nombreComercial"));
        colEmail.setCellValueFactory(new PropertyValueFactory ("correo"));
        colDireccion.setCellValueFactory(new PropertyValueFactory ("direccion"));
        colCodigoP.setCellValueFactory(new PropertyValueFactory ("codigoPostal"));
        colCiudad.setCellValueFactory(new PropertyValueFactory ("ciudad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory ("telefono"));
        colPaginaW.setCellValueFactory(new PropertyValueFactory ("paginaWeb"));
        colRfc.setCellValueFactory(new PropertyValueFactory ("rfc"));
        
    }
    
    private void cargarInfoEmpresaWS(){
        String urlWS = Constantes.URL_BASE+"empresa/leerTodas";
        try{
            String jsonRespuesta = ConsumirServiciosWeb.get(urlWS);
            Gson gson = new Gson();
            Type tipoListaEmpresa =  new TypeToken<List<Empresa>>(){}.getType(); 
            List empresaWS = gson.fromJson(jsonRespuesta, tipoListaEmpresa);
            listaEmpresas.addAll(empresaWS);
            tbEmpresas.setItems(listaEmpresas);
        }catch (Exception e ){
            Utilidades.mostrarAlertaSimple("Error en conexion ", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    
    
    
    
    

    @FXML
    private void clickEditarEmpresa(ActionEvent event) {
           int selectedTrow= tbEmpresas.getSelectionModel().getSelectedIndex();
        if(selectedTrow== -1){
          Utilidades.mostrarAlertaSimple("Error ", "No seleciono ninguno...", Alert.AlertType.ERROR);
          return;
        }
        
        Empresa empresa= listaEmpresas.get(selectedTrow);
            try{
           FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLEditModEmpresa.fxml"));
           Parent vistaEditar = cargador.load();
           FXMLEditModEmpresaController controlador = cargador.getController();
           controlador.recibirInfo(empresa);
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
    private void clickEliminarEmpresa(ActionEvent event) {
        int selectedTrow= tbEmpresas.getSelectionModel().getSelectedIndex();
        if(selectedTrow== -1){
          Utilidades.mostrarAlertaSimple("Error ", "No seleciono ninguna empresa", Alert.AlertType.ERROR);
          return;
        }
        int empresaSeleccionadoId= listaEmpresas.get(selectedTrow).getId();
        Boolean result=Utilidades.mostrarAlertaConexion("Eliminar empresa", "Seguro que desea eliminar empresa", Alert.AlertType.NONE);
        if(result){
            eliminarEmpresa(empresaSeleccionadoId);
        }
    }
        private void eliminarEmpresa(int id){
            String urlWS = Constantes.URL_BASE+"empresa/eliminar/"+id;
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
    private void clickAgregarEmpresa(ActionEvent event) {
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLModEmpresa.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        } catch (Exception e){
            
        }
    }

    @FXML
    private void clickBuscar(ActionEvent event) {
    }
    
}
