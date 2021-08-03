package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.*;

import java.io.IOException;
import java.sql.Date;

public class InformacionIndicadoresdeDineroController {

    @FXML
    private Button btnGuardar1;
    @FXML
    private TextField txtNSem1;
    @FXML
    private DatePicker dtpkrCxCobrar;
    @FXML
    private TextField txtRazonSocial1;
    @FXML
    private TextField txtMonto1;

    @FXML
    private Button btnGuardar2;
    @FXML
    private TextField txtNSem2;
    @FXML
    private DatePicker dtpkrCxPagar;
    @FXML
    private TextField txtRazonSocial2;
    @FXML
    private TextField txtMonto2;



    @FXML
    private Button btnGuardar3;
    @FXML
    private TextField txtNSem3;
    @FXML
    private DatePicker dtpkrBancos;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtMonto3;


    @FXML
    private Button btnCerrar;


    @FXML
    public void BtnGuardar1() {
    CuentasporCobrarDAO dao = new CuentasporCobrarDAO();
        dao.GuardarDatos(Integer.valueOf(txtNSem1.getText()), txtRazonSocial1.getText(),Double.valueOf(txtMonto1.getText()), Date.valueOf(dtpkrCxCobrar.getValue()) );
    }


    @FXML
    public void BtnGuardar2(){
        CuentasporpagarDAO dao = new CuentasporpagarDAO();
        dao.GuardarDatos(Integer.valueOf(txtNSem2.getText()), txtRazonSocial2.getText(),Double.valueOf(txtMonto2.getText()), Date.valueOf(dtpkrCxPagar.getValue()) );
    }

    @FXML
    public void BtnGuardar3(){
        BancosDAO dao = new BancosDAO();
        dao.GuardarDatos(Integer.valueOf(txtNSem3.getText()), txtDescripcion.getText(), Double.valueOf(txtMonto3.getText()), Date.valueOf(dtpkrBancos.getValue()));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuIndicadoresdeDinero.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(page));
            stage.setTitle("MenuIndicadoresdeDinero");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
