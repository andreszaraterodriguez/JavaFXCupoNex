
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxcuponex.modelo.ConsumirServiciosWeb;
import javafxcuponex.pojos.Empresas;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;


public class FXMLAdminModEmpresaController implements Initializable {

    @FXML
    private TableView<Empresas> tbEmpresas;
    @FXML
    private TextField tfBuscarEmpresa;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colNombreC;
    @FXML
    private TableColumn<?, ?> colNombreRL;
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
    
    private ObservableList<Empresas> listaEmpresas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void configurarTabla(){
        listaEmpresas =  FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory ("nombre"));
        colNombreC.setCellValueFactory(new PropertyValueFactory ("nombreC"));
        colNombreRL.setCellValueFactory(new PropertyValueFactory ("nombreRL"));
        colEmail.setCellValueFactory(new PropertyValueFactory ("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory ("direccion"));
        colCodigoP.setCellValueFactory(new PropertyValueFactory ("codigoP"));
        colCiudad.setCellValueFactory(new PropertyValueFactory ("ciudad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory ("telefono"));
        colPaginaW.setCellValueFactory(new PropertyValueFactory ("paginaW"));
        colRfc.setCellValueFactory(new PropertyValueFactory ("rfc"));
        
    }
    
    private void cargarInfoEmpresaWS(){
        String urlWS = Constantes.URL_BASE+"Empresas/all";
        try{
            String jsonRespuesta = ConsumirServiciosWeb.consumirServicioGET(urlWS);
            Gson gson = new Gson();
            Type tipoListaEmpresas =  new TypeToken<List<Empresas>>(){}.getType(); 
            List usuarioWS = gson.fromJson(jsonRespuesta, tipoListaEmpresas);
            listaEmpresas.addAll(usuarioWS);
            tbEmpresas.setItems(listaEmpresas);
        }catch (Exception e ){
            Utilidades.mostrarAlertaSimple("Error en conexion ", "Error al consultar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clickEditarEmpresa(ActionEvent event) {
    }

    @FXML
    private void clickEliminarEmpresa(ActionEvent event) {
    }

    @FXML
    private void clickAgregarEmpresa(ActionEvent event) {
        try{
            
        } catch (Exception e){
            
        }
    }
    
}
