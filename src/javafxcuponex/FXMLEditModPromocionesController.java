/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxcuponex.pojos.Empresa;
import javafxcuponex.pojos.Promocion;
import javafxcuponex.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLEditModPromocionesController implements Initializable {

    @FXML
    private TextField tfNombreP;
    @FXML
    private TextField tfFechaPromocionI;
    @FXML
    private TextField tfRestricciones;
    @FXML
    private TextField tfCostoP;
    
       private ObservableList<Promocion> listaPromociones;
    @FXML
    private Label lbFoto;
    @FXML
    private ComboBox<?> cbCategoria;
    @FXML
    private ComboBox<?> cbTipoP;
    @FXML
    private DatePicker dpFechaFin;
    
    private Promocion promocion;
    @FXML
    private TextField tfDescripcion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clickGuardar(ActionEvent event) {
        String nombre = tfNombreP.getText();
        String descripcion = tfDescripcion.getText();
        
        Promocion promocion = new Promocion();
        promocion.setNombre(nombre);
        promocion.setDescripcion(descripcion);
        
    }

    @FXML
    private void clickSeleccionarFoto(ActionEvent event) {
    }
    
    public void recibirInfo(Promocion promocion){
     this.promocion = promocion;
     tfNombreP.setText(promocion.getNombre());
     tfDescripcion.setText(promocion.getDescripcion());
     
    }
    

    
}
