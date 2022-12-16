/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConsumirServiciosWeb;
import javafxcuponex.pojos.Categoria;
import javafxcuponex.pojos.Empresa;
import javafxcuponex.pojos.Foto;
import javafxcuponex.pojos.Promocion;
import javafxcuponex.pojos.Respuesta;
import javafxcuponex.pojos.Usuario;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLEditModPromocionesController implements Initializable {

    @FXML
    private TextField tfNombreP;
    @FXML
    private TextField tfRestricciones;
    @FXML
    private TextField tfCostoP;
    
    private ObservableList<String> tipo;
     private ObservableList<Categoria> categorias;
    @FXML
    private Label lbFoto;
    @FXML
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private ComboBox<String> cbTipoP;
    @FXML
    private DatePicker dpFechaFin;
    
    private Promocion promocion;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private DatePicker dpFechaIn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarCombobox();
    }   
    
       private void llenarCombobox(){
        categorias = FXCollections.observableArrayList();
        categorias.addAll(recuperaC());
        cbCategoria.setItems(categorias);
        
        tipo = FXCollections.observableArrayList();
        tipo.addAll("porcentual", "fijo");
        cbTipoP.setItems(tipo);
        
    }
      public void recibirInfo(Promocion promocion){
     this.promocion = promocion;
     tfNombreP.setText(promocion.getNombre());
     tfDescripcion.setText(promocion.getDescripcion());
     tfCostoP.setText(promocion.getCosto().toString());
     cbTipoP.setValue(promocion.getTipoDePromocion());
     for(int i=0; i<categorias.size();i++){
         if(categorias.get(i).getId()== promocion.getCategoriaId()){
             cbCategoria.getSelectionModel().select(i);
             break;
         }
     }
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dpFechaIn.setValue( LocalDate.parse(promocion.getFechaDeInicio(), formatter));
              dpFechaFin.setValue( LocalDate.parse(promocion.getFechaDeTermino(), formatter));
             // cargarFoto();
    }
      /*private void cargarFoto(){
          Foto respuest = new Foto();
        Gson gson = new Gson();
        try{
              String url = Constantes.URL_BASE +"promocion/obtenerFoto/"+this.promocion.getId();
         
              String resultado = ConsumirServiciosWeb.get( url); 
             System.out.println(resultado);
              respuest  = gson.fromJson(resultado, Foto.class);
              
            foto = Base64.getDecoder().decode(respuest.getDatos());
        } catch(Exception e){
            e.printStackTrace();
             Utilidades.mostrarAlertaSimple("Error...", e.getMessage(), Alert.AlertType.ERROR);

        }
      }*/


    @FXML
    private void clickGuardar(ActionEvent event) {
        String nombre = tfNombreP.getText();
        String descripcion = tfDescripcion.getText();
  
        String tipo = cbTipoP.getValue();
        if(tipo == null){
        Utilidades.mostrarAlertaSimple("Complete datos...", "Seleccione tipo..", Alert.AlertType.INFORMATION);
        return;
        }
     
       Categoria categor = cbCategoria.getValue();
       
        if(categor == null){
        Utilidades.mostrarAlertaSimple("Complete datos...", "Seleccione categoria..", Alert.AlertType.INFORMATION);
        return;
        }
        
        LocalDate fechaInicioDate = dpFechaIn.getValue();
        if(fechaInicioDate == null){
        Utilidades.mostrarAlertaSimple("Complete datos...", "Seleccione fecha inicio..", Alert.AlertType.INFORMATION);
        return;
        }
        String fechaIncio = fechaInicioDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        
        LocalDate fechaFinDate = dpFechaFin.getValue();
        if(fechaFinDate == null){
        Utilidades.mostrarAlertaSimple("Complete datos...", "Seleccione fecha fin..", Alert.AlertType.INFORMATION);
        return;
        }
        String fechaFin = fechaFinDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
      
        promocion.setNombre(nombre);
        promocion.setDescripcion(descripcion);
        promocion.setTipoDePromocion(tipo);
        promocion.setEstatus(Boolean.TRUE);
        promocion.setFechaDeInicio(fechaIncio);
        promocion.setFechaDeTermino(fechaFin);
        promocion.setCategoriaId(categor.getId());
        Double costo = Double.parseDouble(tfCostoP.getText());
        if(tipo.equals("porcentual")){
            promocion.setDescuento(costo);
            promocion.setCosto(0.0);
        }else{
            promocion.setDescuento(0.0);
            promocion.setCosto(costo);
        }
        
        
        crearPromocion(promocion);
    }
    
    private byte [] foto = null;
    private void crearPromocion(Promocion prom){
        Respuesta respuest = new Respuesta();
        Gson gson = new Gson();
        try{
              String url = Constantes.URL_BASE +"promocion/actualizar/"+this.promocion.getId();
              String json = gson.toJson(prom);
              String resultado = ConsumirServiciosWeb.put( url, json); 
         
              respuest  = gson.fromJson(resultado, Respuesta.class);  
            if(respuest.getError()){
              Utilidades.mostrarAlertaSimple("Error...", respuest.getMensaje(), Alert.AlertType.ERROR);
              
            }else{
            Utilidades.mostrarAlertaSimple("Exito...", respuest.getMensaje(), Alert.AlertType.INFORMATION);
            if(foto != null){
                      subirFoto(foto,respuest.getId());
            }
      
            }
        } catch(Exception e){
            e.printStackTrace();
             Utilidades.mostrarAlertaSimple("Error...", e.getMessage(), Alert.AlertType.ERROR);

        }
    }

      
      
      private List<Categoria> recuperaC(){
            List<Categoria> categoria = new ArrayList();
        try{
              String url = Constantes.URL_BASE +"categoria/leerTodas";
              String resultado = ConsumirServiciosWeb.get(url); 
              System.out.println(resultado);
              Gson gson = new Gson();
              Type tipoListaEmpresas =  new TypeToken<List<Categoria>>(){}.getType(); 
              categoria  = gson.fromJson(resultado, tipoListaEmpresas);  
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return categoria;
      }

    @FXML
    private void clickSeleccionarFoto(ActionEvent event) {
        try{
       FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Selecciona imagen");
        Stage stage = (Stage) tfNombreP.getScene().getWindow();
        
        File file =   filechooser.showOpenDialog(stage);
        if(file == null){
            return;
        }
        foto = Files.readAllBytes(file.toPath());
      //  String prub = Base64.getEncoder().encodeToString(foto);
        
        }catch (Exception e){
           e.printStackTrace();
             Utilidades.mostrarAlertaSimple("Error...", e.getMessage(), Alert.AlertType.ERROR);
        }
  
    }
    private void subirFoto(byte [] foto, int id){
        Respuesta respuesta = new Respuesta();
        
        try{
              String url = Constantes.URL_BASE +"promocion/foto/"+id;
              String resultado = ConsumirServiciosWeb.postFoto(url, foto); 
              System.out.println(resultado);
              Gson gson = new Gson();
              respuesta  = gson.fromJson(resultado, Respuesta.class);  
              if(respuesta.getError()){
              Utilidades.mostrarAlertaSimple("Error...", respuesta.getMensaje(), Alert.AlertType.ERROR);
              
            }else{
            Utilidades.mostrarAlertaSimple("Exito...", respuesta.getMensaje(), Alert.AlertType.INFORMATION);

            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

  
    
  
    

    
}
