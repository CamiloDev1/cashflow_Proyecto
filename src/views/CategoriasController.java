package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.Categorias;
import persistencia.CategoriasDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoriasController  implements Initializable {

    @FXML
    private Button idNew;
    @FXML
    private Button idGuardar;
    @FXML
    private Button idEditar;
    @FXML
    private Button idEliminar;
    @FXML
    private Button btnCerrar;
    @FXML
    private TextField idCategoria;
    @FXML
    private TextField idSubCategoria;
    @FXML
    private ComboBox<String> cmbClasificacion;


    @FXML
    private TableView<Categorias> tblListaCategorias;
    @FXML
    private TableColumn<Categorias, String> clmnClasificacion;
    @FXML
    private TableColumn<Categorias, String> clmnCategoria;
    @FXML
    private TableColumn<Categorias, String> clmnSubCategoria;

    private ObservableList<Categorias> olListaCategorias;
    private CategoriasDAO categoriasDAO;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        olListaCategorias = FXCollections.observableArrayList();
        categoriasDAO = new CategoriasDAO();
        olListaCategorias.addAll(categoriasDAO.listaCategorias());
        tblListaCategorias.setItems(olListaCategorias);
        cmbClasificacion.getItems().addAll("GAO", "Ingreso", "Costo-Venta");

        clmnClasificacion.setCellValueFactory(tf -> tf.getValue().clasificacion());
        clmnCategoria.setCellValueFactory(tf -> tf.getValue().categoria());

        clmnSubCategoria.setCellValueFactory(tf -> tf.getValue().subCategoria());
    }


    @FXML
    public void BtnGuardar(){
        CategoriasDAO dao = new CategoriasDAO();
        olListaCategorias = FXCollections.observableArrayList();
        dao.GuardarDatos(cmbClasificacion.getSelectionModel().getSelectedItem(), idCategoria.getText(), idSubCategoria.getText() );
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Registro exitoso");
        mensaje.setContentText("La Categoria se a registrado exitosamente :D");
        mensaje.setHeaderText("Resultado:");
        mensaje.show();
        olListaCategorias.addAll(dao.listaCategorias());
        tblListaCategorias.setItems(olListaCategorias);
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
