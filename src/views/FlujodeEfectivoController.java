package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.CategoriasDAO;
import persistencia.FlujodeEfectivo;
import persistencia.FlujodeEfectivoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlujodeEfectivoController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCerrar;
    @FXML
    private RadioButton rbEntrada;
    @FXML
    private RadioButton rbSalida;

    @FXML
    private ComboBox<String> cmbCategoria;
    @FXML
    private ComboBox<String> cmbSubCategoria;
    @FXML
    private TextField txtNumSemana;
    @FXML
    private DatePicker dtpkrFecha;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtCantidad;

    @FXML
    private TableView<FlujodeEfectivo> tblListaFlujodeEfectivo;
    @FXML
    private TableColumn<FlujodeEfectivo, String> clmnFechas;
    @FXML
    private TableColumn<FlujodeEfectivo, String> clmnDescripcion;
    @FXML
    private TableColumn<FlujodeEfectivo, String> clmnCategoria;
    @FXML
    private TableColumn<FlujodeEfectivo, String> clmnSubCategoria;

    private ObservableList<FlujodeEfectivo> olListaFlujodeEfectivo;

    private FlujodeEfectivoDAO flujodeEfectivoDAO;
    private CategoriasDAO categoriasDAO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        olListaFlujodeEfectivo = FXCollections.observableArrayList();
        flujodeEfectivoDAO = new FlujodeEfectivoDAO();
        categoriasDAO = new CategoriasDAO();

        olListaFlujodeEfectivo.addAll(flujodeEfectivoDAO.listaFlujodeEfectivo());
        tblListaFlujodeEfectivo.setItems(olListaFlujodeEfectivo);

        cmbCategoria.getItems().addAll(categoriasDAO.listaCategoria());
        cmbSubCategoria.getItems().addAll(categoriasDAO.listaSubCategoria());

        clmnFechas.setCellValueFactory(new PropertyValueFactory<>("Fechas"));
        clmnDescripcion.setCellValueFactory(tf -> tf.getValue().descripcion());
        clmnCategoria.setCellValueFactory(tf -> tf.getValue().categoria());
        clmnSubCategoria.setCellValueFactory(tf -> tf.getValue().subCategoria());
       // gestionDeEventos();
    }

    public void gestionDeEventos() {
         ToggleGroup group = new ToggleGroup();
         rbEntrada = new RadioButton("E");
         System.out.println(rbEntrada.getText());
        rbEntrada.setToggleGroup(group);
        rbSalida = new RadioButton("S");
        rbSalida.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle valorAnterior, Toggle valorNuevo) {
                System.out.println(valorNuevo);
                if(valorNuevo!=null) {
                    if(valorNuevo.equals("E")){
                        System.out.println("Selecciono Entrada");
                        for(int i=0; i<categoriasDAO.listaCategorias().size(); i++){
                            if(categoriasDAO.listaCategorias().get(i).clasificacion().equals("Ingreso")){
                                cmbCategoria.getItems().add(categoriasDAO.listaCategorias().get(i).getCategoria());
                                cmbSubCategoria.getItems().add(categoriasDAO.listaCategorias().get(i).getSubCategoria());
                            }

                        }

                    } else {
                        if (valorNuevo.equals("S")){

                            cmbCategoria.getItems().addAll(categoriasDAO.listaCategoria());
                            cmbSubCategoria.getItems().addAll(categoriasDAO.listaSubCategoria());
                        }
                    }
                }
            }
        });
    }

    @FXML
    public void BtnGuardar(){
        FlujodeEfectivoDAO dao = new FlujodeEfectivoDAO();
        olListaFlujodeEfectivo = FXCollections.observableArrayList();
        String Clasific = "";
        for(int i=0; i<categoriasDAO.listaCategorias().size(); i++){
            if(cmbCategoria.getSelectionModel().getSelectedItem().equals(categoriasDAO.listaCategorias().get(i).getCategoria())){
                Clasific = String.valueOf(categoriasDAO.listaCategorias().get(i).getClasificacion());
            }
        }

        dao.GuardarDatos(dtpkrFecha.getValue(), String.valueOf(rbEntrada.isSelected()?"E":"S"), txtDescripcion.getText(), cmbCategoria.getSelectionModel().getSelectedItem(), cmbSubCategoria.getSelectionModel().getSelectedItem(), Double.valueOf(txtCantidad.getText()), Integer.valueOf(txtNumSemana.getText()), Clasific );

        olListaFlujodeEfectivo.addAll(dao.listaFlujodeEfectivo());
        tblListaFlujodeEfectivo.setItems(olListaFlujodeEfectivo);
    }


    @FXML
    private void cerrarVentana(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        cargarMenuprincipal();
    }

    public void cargarMenuprincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuFinanzas.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(page));
            stage.setTitle("MenuFinanzas");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
