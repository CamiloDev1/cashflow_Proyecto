package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ReportesController  implements Initializable {

    @FXML
    private Button btnCerrar;
    @FXML
    private ComboBox<String> cmbMes;
    @FXML
    private ListView<String> lvCuentasxCobrar;
    @FXML
    private ListView<String> lvFinalCuentasxCobrar;
    @FXML
    private ListView<String> lvTotalCuentasxCobrar;
    @FXML
    private ListView<String> lvFinalTotalCuentasxCobrar;


    @FXML
    private ListView<String> lvCuentasxPagar;
    @FXML
    private ListView<String> lvFinalCuentasxPagar;
    @FXML
    private ListView<String> lvTotalCuentasxPagar;
    @FXML
    private ListView<String> lvFinalTotalCuentasxPagar;


    @FXML
    private ListView<String> lvIngresos;
    @FXML
    private ListView<String> lvFinalIngresos;
    @FXML
    private ListView<String> lvTotalIngresos;
    @FXML
    private ListView<String> lvFinalTotalIngresos;


    @FXML
    private ListView<String> lvGastos;
    @FXML
    private ListView<String> lvFinalGastos;
    @FXML
    private ListView<String> lvTotalGastos;
    @FXML
    private ListView<String> lvFinalTotalGastos;

    @FXML
    private ListView<String> lvDiferencia;
    @FXML
    private ListView<String> lvFinalDiferencia;

    @FXML
    private ListView<String> lvMargendeDiferencia;
    @FXML
    private ListView<String> lvFinalMargendeDiferencia;


    @FXML
    private ListView<String> lvBancos;
    @FXML
    private ListView<String> lvFinalBancos;
    @FXML
    private ListView<String> lvTotalBancos;
    @FXML
    private ListView<String> lvFinalTotalBancos;

    private ReportesDAO reportesDAO;
    private Bancos bancos;


    CuentasporCobrarDAO cuentasporCobrarDAO;
    CuentasporpagarDAO cuentasporpagarDAO;
    BancosDAO bancosDAO;
    FlujodeEfectivoDAO flujodeEfectivoDAO;

    Double totalsem1Ingreso=0.0;
    Double totalsem2Ingreso=0.0;
    Double totalsem3Ingreso=0.0;
    Double totalsem4Ingreso=0.0;
    Double totalsem5Ingreso=0.0;
    Double totalsem1Gastos=0.0;
    Double totalsem2Gastos=0.0;
    Double totalsem3Gastos=0.0;
    Double totalsem4Gastos=0.0;
    Double totalsem5Gastos=0.0;

    Double totalIngresos=0.0;
    Double totalGastos=0.0;


    String totalMargenRentabilidad="";
    String totalDifencia="";
    String totalCuentasxCobrarMes="";
    String totalCuentasxPagarMes="";
    String totalIngresosMes="";
    String totalGastosMes="";
    String totalBancosMes="";


    ArrayList<String> arrayCuentasxCobrar = new ArrayList<String>();
    ArrayList<String> arrayCuentasxPagar = new ArrayList<String>();
    ArrayList<String> arrayIngresos = new ArrayList<String>();
    ArrayList<String> arrayGastos = new ArrayList<String>();
    ArrayList<String> arrayBancos = new ArrayList<String>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbMes.getItems().addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        cmbMes.getSelectionModel().select(0);

        cuentasporCobrarDAO = new CuentasporCobrarDAO();
        cuentasporpagarDAO = new CuentasporpagarDAO();
        bancosDAO = new BancosDAO();
        flujodeEfectivoDAO = new FlujodeEfectivoDAO();
        mostrarCuentasPorCobrar();
        mostrarCuentasPorPagar();
        mostrarIngresos();
        mostrarGastos();
        mostrarBancos();
        gestionDeEventos();
        }

    public void mostrarCuentasPorCobrar(){
            String mesSeleccionado = cmbMes.getSelectionModel().getSelectedItem();
            verificarMes(mesSeleccionado);

            cuentasporCobrarDAO.listaCuentasporCobrar();
            //cuentasporpagarDAO.listaCuentasporpagar();
          //  flujodeEfectivoDAO.listaFlujodeEfectivo();
//bancosDAO.listaBancos();


            ArrayList<String> listaCuentasxCobrar = new ArrayList<String>();
            ArrayList<String> listaMeses = new ArrayList<String>();
            ArrayList<String> listaCuentasxCobrarCompleta = new ArrayList<String>();

            for (int i = 0; i < cuentasporCobrarDAO.listaCuentasporCobrar().size(); i++) {
                String mesCompleto = String.valueOf(cuentasporCobrarDAO.listaCuentasporCobrar().get(i).getFecha());
                String[] mesArreglo;
                mesArreglo = mesCompleto.split("-");
                if(mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                    listaCuentasxCobrar.add(cuentasporCobrarDAO.listaCuentasporCobrar().get(i).getRazonSocial());
                }
            }
            Set<String> set = new HashSet<>(listaCuentasxCobrar);
            listaCuentasxCobrar.clear();
            listaCuentasxCobrar.addAll(set);
       /* for (int i=0; i<listaCuentasxCobrar.size(); i++){
            System.out.println(listaCuentasxCobrar.get(i));
        }*/
            Double totalSemana1=0.0;
            Double totalSemana2=0.0;
            Double totalSemana3=0.0;
            Double totalSemana4=0.0;
            Double totalSemana5=0.0;
            Double totalMes=0.0;
            for (int i = 0; i < listaCuentasxCobrar.size(); i++) {

                Double semana1 = 0.0;
                Double semana2 = 0.0;
                Double semana3 = 0.0;
                Double semana4 = 0.0;
                Double semana5 = 0.0;
                Double total = 0.0;

                for (int k = 0; k < cuentasporCobrarDAO.listaCuentasporCobrar().size(); k++) {
                    String mesCompleto = String.valueOf(cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getFecha());
                    String[] mesArreglo;
                    mesArreglo = mesCompleto.split("-");
                    System.out.println(listaCuentasxCobrar.get(i) + "Va a  comparar con :" + cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getRazonSocial());
                    if (listaCuentasxCobrar.get(i).equals(cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getRazonSocial())) {
                        System.out.println("Si Cumple");
                        if (cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getNdeSemana().equals(1) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                            semana1 = semana1 + cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getMonto();
                        } else {
                            if (cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getNdeSemana().equals(2) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                semana2 = semana2 + cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getMonto();

                            } else {
                                if (cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getNdeSemana().equals(3) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                    semana3 = semana3 + cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getMonto();
                                } else {
                                    if (cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getNdeSemana().equals(4) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                        semana4 = semana4 + cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getMonto();
                                    }
                                    else {
                                        if (cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getNdeSemana().equals(5) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                            semana5 = semana5 + cuentasporCobrarDAO.listaCuentasporCobrar().get(k).getMonto();
                                        }
                                    }
                                }
                            }
                        }

                    }
                    System.out.println("No cumple");
                }
                total = total + Double.valueOf(semana1);
                total = total + Double.valueOf(semana2);
                total = total + Double.valueOf(semana3);
                total = total + Double.valueOf(semana4);
                total = total + Double.valueOf(semana5);
                listaCuentasxCobrarCompleta.add(listaCuentasxCobrar.get(i) +
                        "                                           $ " + semana1 +
                        "                                $ " + semana2 +
                        "                              $ " + semana3 +
                        "                                $ " + semana4 +
                        "                                $ " + semana5 +
                        "                        $ " + total
                );

                arrayCuentasxCobrar.add(listaCuentasxCobrar.get(i) +
                        "                                           $ " + semana1 +
                        "                                $ " + semana2 +
                        "                              $ " + semana3 +
                        "                                $ " + semana4 +
                        "                                $ " + semana5 +
                        "                        $ " + total
                );

                lvCuentasxCobrar.getItems().add(listaCuentasxCobrarCompleta.get(i));
                totalSemana1 = totalSemana1 + semana1;
                totalSemana2 = totalSemana2 + semana2;
                totalSemana3 = totalSemana3 + semana3;
                totalSemana4 = totalSemana4 + semana4;
                totalSemana5 = totalSemana5 + semana5;
                totalMes= totalMes + total;
            }

            totalCuentasxCobrarMes = totalCuentasxCobrarMes + "Total Cuentas por Cobrar             "+ "$ "+totalSemana1+
                    "                            "+"$ "+totalSemana2+
                    "                              "+"$ "+totalSemana3+
                    "                              "+"$ "+totalSemana4+
                    "                              "+"$ "+totalSemana5+
                    "                              "+"$ "+totalMes;

            lvTotalCuentasxCobrar.getItems().add("Total Cuentas por Cobrar             "+ "$ "+totalSemana1+
                    "                            "+"$ "+totalSemana2+
                            "                              "+"$ "+totalSemana3+
                            "                              "+"$ "+totalSemana4+
                            "                              "+"$ "+totalSemana5
                    );
            lvFinalTotalCuentasxCobrar.getItems().add("           $ "+totalMes);
        }

    public void mostrarCuentasPorPagar(){
        String mesSeleccionado = cmbMes.getSelectionModel().getSelectedItem();
        verificarMes(mesSeleccionado);

        cuentasporpagarDAO.listaCuentasporpagar();

//bancosDAO.listaBancos();


        ArrayList<String> listaCuentasxPagar = new ArrayList<String>();
        ArrayList<String> listaMeses = new ArrayList<String>();
        ArrayList<String> listaCuentasxPagarCompleta = new ArrayList<String>();

        for (int i = 0; i < cuentasporpagarDAO.listaCuentasporpagar().size(); i++) {
            String mesCompleto = String.valueOf(cuentasporpagarDAO.listaCuentasporpagar().get(i).getFecha());
            String[] mesArreglo;
            mesArreglo = mesCompleto.split("-");
            if(mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                listaCuentasxPagar.add(cuentasporpagarDAO.listaCuentasporpagar().get(i).getRazonSocial());
            }
        }
        Set<String> set = new HashSet<>(listaCuentasxPagar);
        listaCuentasxPagar.clear();
        listaCuentasxPagar.addAll(set);
       /* for (int i=0; i<listaCuentasxCobrar.size(); i++){
            System.out.println(listaCuentasxCobrar.get(i));
        }*/
        Double totalSemana1=0.0;
        Double totalSemana2=0.0;
        Double totalSemana3=0.0;
        Double totalSemana4=0.0;
        Double totalSemana5=0.0;
        Double totalMes=0.0;
        for (int i = 0; i < listaCuentasxPagar.size(); i++) {
            Double semana1 = 0.0;
            Double semana2 = 0.0;
            Double semana3 = 0.0;
            Double semana4 = 0.0;
            Double semana5 = 0.0;
            Double total = 0.0;

            for (int k = 0; k < cuentasporpagarDAO.listaCuentasporpagar().size(); k++) {
                String mesCompleto = String.valueOf(cuentasporpagarDAO.listaCuentasporpagar().get(k).getFecha());
                String[] mesArreglo;
                mesArreglo = mesCompleto.split("-");
                System.out.println(listaCuentasxPagar.get(i) + "Va a  comparar con :" + cuentasporpagarDAO.listaCuentasporpagar().get(k).getRazonSocial());
                if (listaCuentasxPagar.get(i).equals(cuentasporpagarDAO.listaCuentasporpagar().get(k).getRazonSocial())) {
                    System.out.println("Si Cumple");
                    if (cuentasporpagarDAO.listaCuentasporpagar().get(k).getNdeSemana().equals(1) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                        semana1 = semana1 + cuentasporpagarDAO.listaCuentasporpagar().get(k).getMonto();
                    } else {
                        if (cuentasporpagarDAO.listaCuentasporpagar().get(k).getNdeSemana().equals(2) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                            semana2 = semana2 + cuentasporpagarDAO.listaCuentasporpagar().get(k).getMonto();

                        } else {
                            if (cuentasporpagarDAO.listaCuentasporpagar().get(k).getNdeSemana().equals(3) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                semana3 = semana3 + cuentasporpagarDAO.listaCuentasporpagar().get(k).getMonto();
                            } else {
                                if (cuentasporpagarDAO.listaCuentasporpagar().get(k).getNdeSemana().equals(4) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                    semana4 = semana4 + cuentasporpagarDAO.listaCuentasporpagar().get(k).getMonto();
                                } else {
                                    if (cuentasporpagarDAO.listaCuentasporpagar().get(k).getNdeSemana().equals(5) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                        semana5 = semana5 + cuentasporpagarDAO.listaCuentasporpagar().get(k).getMonto();
                                    }
                                }
                            }
                        }
                    }

                }
                System.out.println("No cumple");
            }
            total = total + Double.valueOf(semana1);
            total = total + Double.valueOf(semana2);
            total = total + Double.valueOf(semana3);
            total = total + Double.valueOf(semana4);
            total = total + Double.valueOf(semana5);
            listaCuentasxPagarCompleta.add(listaCuentasxPagar.get(i) +
                    "                                           $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );
            arrayCuentasxPagar.add(listaCuentasxPagar.get(i) +
                    "                                           $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );

            lvCuentasxPagar.getItems().add(listaCuentasxPagarCompleta.get(i));
            totalSemana1 = totalSemana1 + semana1;
            totalSemana2 = totalSemana2 + semana2;
            totalSemana3 = totalSemana3 + semana3;
            totalSemana4 = totalSemana4 + semana4;
            totalSemana5 = totalSemana5 + semana5;
            totalMes= totalMes + total;
        }
        totalCuentasxPagarMes = totalCuentasxPagarMes + "Total Cuentas por Pagar              "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5+
                "                              "+"$ "+totalMes;


        lvTotalCuentasxPagar.getItems().add("Total Cuentas por Pagar              "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5
        );
        lvFinalTotalCuentasxPagar.getItems().add("           $ "+totalMes);
    }

    public void mostrarIngresos(){
        String mesSeleccionado = cmbMes.getSelectionModel().getSelectedItem();
       String tipodeFlujo = "E";
        verificarMes(mesSeleccionado);
        flujodeEfectivoDAO.listaFlujodeEfectivo();

        ArrayList<String> listaIngresos = new ArrayList<String>();
        ArrayList<String> listaMeses = new ArrayList<String>();
        ArrayList<String> listaIngresosCompleta = new ArrayList<String>();

        for (int i = 0; i < flujodeEfectivoDAO.listaFlujodeEfectivo().size(); i++) {
            String mesCompleto = String.valueOf(flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getFechas());
            String[] mesArreglo;
            mesArreglo = mesCompleto.split("-");
            if(mesArreglo[1].equals(verificarMes(mesSeleccionado)) & flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getEntrada().equals(tipodeFlujo)) {
                listaIngresos.add(flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getSubCategoria());
            }
        }
        Set<String> set = new HashSet<>(listaIngresos);
        listaIngresos.clear();
        listaIngresos.addAll(set);
       /* for (int i=0; i<listaCuentasxCobrar.size(); i++){
            System.out.println(listaCuentasxCobrar.get(i));
        }*/
        Double totalSemana1=0.0;
        Double totalSemana2=0.0;
        Double totalSemana3=0.0;
        Double totalSemana4=0.0;
        Double totalSemana5=0.0;
        Double totalMes=0.0;
        for (int i = 0; i < listaIngresos.size(); i++) {
            Double semana1 = 0.0;
            Double semana2 = 0.0;
            Double semana3 = 0.0;
            Double semana4 = 0.0;
            Double semana5 = 0.0;
            Double total = 0.0;

            for (int k = 0; k < flujodeEfectivoDAO.listaFlujodeEfectivo().size(); k++) {
                String mesCompleto = String.valueOf(flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getFechas());
                String[] mesArreglo;
                mesArreglo = mesCompleto.split("-");
                System.out.println(listaIngresos.get(i) + "Va a  comparar con :" + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getSubCategoria());

                if (listaIngresos.get(i).equals(flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getSubCategoria())) {
                    System.out.println("Si Cumple " + flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getNdeSemana() );
                    if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(1) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                        semana1 = semana1 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();

                    } else {
                        if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(2) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                            semana2 = semana2 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();

                        } else {
                            if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(3) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                semana3 = semana3 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();
                            } else {
                                if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(4) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                    semana4 = semana4 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();
                                } else {
                                    if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(5) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                        semana5 = semana5 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();
                                    }
                                }
                            }
                        }
                    }

                }
                System.out.println("No cumple");
            }
            total = total + Double.valueOf(semana1);
            total = total + Double.valueOf(semana2);
            total = total + Double.valueOf(semana3);
            total = total + Double.valueOf(semana4);
            total = total + Double.valueOf(semana5);
            listaIngresosCompleta.add(listaIngresos.get(i) +
                    "                                           $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );

            arrayIngresos.add(listaIngresos.get(i) +
                    "                                           $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );

            lvIngresos.getItems().add(listaIngresosCompleta.get(i));
            totalSemana1 = totalSemana1 + semana1;
            totalSemana2 = totalSemana2 + semana2;
            totalSemana3 = totalSemana3 + semana3;
            totalSemana4 = totalSemana4 + semana4;
            totalSemana5 = totalSemana5 + semana5;
            totalMes= totalMes + total;
        }
        totalsem1Ingreso= totalsem1Ingreso+ totalSemana1;
        totalsem2Ingreso= totalsem2Ingreso+ totalSemana2;
        totalsem3Ingreso= totalsem3Ingreso+ totalSemana3;
        totalsem4Ingreso= totalsem4Ingreso+ totalSemana4;
        totalsem5Ingreso= totalsem5Ingreso+ totalSemana5;
        totalIngresos=totalIngresos+totalMes;
        System.out.println(totalsem1Ingreso);

        totalIngresosMes=totalIngresosMes+ "Total Ingresos                                 "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5+
                "                              "+"$ "+totalMes;

        lvTotalIngresos.getItems().add("Total Ingresos                                 "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5
        );
        lvFinalTotalIngresos.getItems().add("           $ "+totalMes);

    }

    public void mostrarGastos(){
        String mesSeleccionado = cmbMes.getSelectionModel().getSelectedItem();
        String tipodeFlujo = "S";
        verificarMes(mesSeleccionado);
        flujodeEfectivoDAO.listaFlujodeEfectivo();

        ArrayList<String> listaGastos = new ArrayList<String>();
        ArrayList<String> listaMeses = new ArrayList<String>();
        ArrayList<String> listaGastosCompleta = new ArrayList<String>();
        for (int i = 0; i < flujodeEfectivoDAO.listaFlujodeEfectivo().size(); i++) {
            String mesCompleto = String.valueOf(flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getFechas());
            String[] mesArreglo;
            mesArreglo = mesCompleto.split("-");
            if(mesArreglo[1].equals(verificarMes(mesSeleccionado)) & flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getEntrada().equals(tipodeFlujo)) {
                listaGastos.add(flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getClasificacion());
            }
        }
        Set<String> set = new HashSet<>(listaGastos);
        listaGastos.clear();
        listaGastos.addAll(set);
       /* for (int i=0; i<listaCuentasxCobrar.size(); i++){
            System.out.println(listaCuentasxCobrar.get(i));
        }*/
        Double totalSemana1=0.0;
        Double totalSemana2=0.0;
        Double totalSemana3=0.0;
        Double totalSemana4=0.0;
        Double totalSemana5=0.0;
        Double totalMes=0.0;
        for (int i = 0; i < listaGastos.size(); i++) {
            Double semana1 = 0.0;
            Double semana2 = 0.0;
            Double semana3 = 0.0;
            Double semana4 = 0.0;
            Double semana5 = 0.0;
            Double total = 0.0;

            for (int k = 0; k < flujodeEfectivoDAO.listaFlujodeEfectivo().size(); k++) {
                String mesCompleto = String.valueOf(flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getFechas());
                String[] mesArreglo;
                mesArreglo = mesCompleto.split("-");
                System.out.println(listaGastos.get(i) + "Va a  comparar con :" + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getClasificacion());

                if (listaGastos.get(i).equals(flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getClasificacion())) {
                    System.out.println("Si Cumple " + flujodeEfectivoDAO.listaFlujodeEfectivo().get(i).getNdeSemana() );
                    if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(1) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                        semana1 = semana1 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();

                    } else {
                        if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(2) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                            semana2 = semana2 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();

                        } else {
                            if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(3) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                semana3 = semana3 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();
                            } else {
                                if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(4) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                    semana4 = semana4 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();
                                } else {
                                    if (flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getNdeSemana().equals(5) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                        semana5 = semana5 + flujodeEfectivoDAO.listaFlujodeEfectivo().get(k).getCantidad();
                                    }
                                }
                            }
                        }
                    }

                }
                System.out.println("No cumple");
            }
            total = total + Double.valueOf(semana1);
            total = total + Double.valueOf(semana2);
            total = total + Double.valueOf(semana3);
            total = total + Double.valueOf(semana4);
            total = total + Double.valueOf(semana5);
            listaGastosCompleta.add(listaGastos.get(i) +
                    "                                 $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );
            arrayGastos.add(listaGastos.get(i) +
                    "                                 $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );
            lvGastos.getItems().add(listaGastosCompleta.get(i));
            totalSemana1 = totalSemana1 + semana1;
            totalSemana2 = totalSemana2 + semana2;
            totalSemana3 = totalSemana3 + semana3;
            totalSemana4 = totalSemana4 + semana4;
            totalSemana5 = totalSemana5 + semana5;
            totalMes= totalMes + total;
        }
        totalsem1Gastos=totalsem1Gastos +totalSemana1;
        totalsem2Gastos=totalsem2Gastos +totalSemana2;
        totalsem3Gastos=totalsem3Gastos +totalSemana3;
        totalsem4Gastos=totalsem4Gastos +totalSemana4;
        totalsem5Gastos=totalsem5Gastos +totalSemana5;
        totalGastos=totalGastos+totalMes;
        totalGastosMes = totalGastosMes + "Total Gastos                                 "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5+
                "                              "+"$ "+totalMes;
        lvTotalGastos.getItems().add("Total Gastos                                 "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5
        );
        lvFinalTotalGastos.getItems().add("           $ "+totalMes);
        diferencia();

    }

    public void diferencia(){

        Double difSem1=0.0;
        Double difSem2=0.0;
        Double difSem3=0.0;
        Double difSem4=0.0;
        Double difSem5=0.0;

        Double difTotal=0.0;

        //if(totalsem1Ingreso>0 || totalsem1Gastos>0){


            difSem1 =  totalsem1Ingreso - totalsem1Gastos ;
             //if(difSem1<0){
                // difSem1 =  difSem1*-1;




            difSem2 = totalsem2Ingreso -  totalsem2Gastos  ;

               // difSem2 = difSem2 * -1;




            difSem3 = totalsem3Ingreso - totalsem3Gastos  ;

             //   difSem3 = difSem3 *-1;






            difSem4 = totalsem4Ingreso -  totalsem4Gastos ;

        difSem5 = totalsem5Ingreso -  totalsem5Gastos ;

                //difSem4 = difSem4 * -1;




            difTotal =  totalIngresos - totalGastos ;

           // difTotal = difTotal * -1;




        totalDifencia= totalDifencia + "Total Utilidad                             "+
                "$"+String.valueOf(difSem1)+"                            " +
                "$"+String.valueOf(difSem2)+"                           "+"$"+difSem3+"                              "+
                "$"+difSem4+"                        "+"$"+difSem5+"                        "+"$"+difTotal;

        lvDiferencia.getItems().add("Total Utilidad                                "+
                "$"+String.valueOf(difSem1)+"                            " +
                "$"+String.valueOf(difSem2)+"                           "+"$"+difSem3+"                              "+
                "$"+difSem4+"                              "+"$"+difSem5);
        lvFinalDiferencia.getItems().add("          "+"$"+difTotal);
        margenRentabilidad();
    }

    public void margenRentabilidad(){

        Double difSem1=0.0;
        Double difSem2=0.0;
        Double difSem3=0.0;
        Double difSem4=0.0;
        Double difSem5=0.0;
        Double difTotal=0.0;

        //if(totalsem1Ingreso>0 || totalsem1Gastos>0){

        if(totalsem1Ingreso<1){
            difSem1 =  totalsem1Gastos - totalsem1Ingreso;
            difSem1 =  difSem1*-1;
        }
        else {
            difSem1 =  totalsem1Gastos / totalsem1Ingreso;
            difSem1=  difSem1 * 100;
            difSem1 =  difSem1 -100;
            difSem1 =  difSem1*-1;
        }

if(totalsem2Ingreso<1){
    difSem2 = totalsem2Gastos - totalsem2Ingreso ;
    difSem2 = difSem2 * -1;

} else{
    difSem2 = totalsem2Gastos / totalsem2Ingreso ;
    difSem2= difSem2 *100;
    difSem2 = difSem2 -100;
    difSem2 = difSem2 * -1;
}


            if(totalsem3Ingreso<1){
                difSem3 = totalsem3Gastos - totalsem3Ingreso;
                difSem3 = difSem3 *-1;
            } else {
                difSem3 =  totalsem3Gastos / totalsem3Ingreso;
                difSem3= difSem3 *100;
                difSem3 = difSem3 -100;
                difSem3 = difSem3 *-1;
            }


      if(totalsem4Ingreso<1){
          difSem4 =  totalsem4Gastos - totalsem4Ingreso;
          difSem4 = difSem4 * -1;
      }
      else {
          difSem4 =  totalsem4Gastos / totalsem4Ingreso;
          difSem4= difSem4 * 100;
          difSem4 = difSem4 -100;
          difSem4 = difSem4 * -1;
      }

        if(totalsem5Ingreso<1){
            difSem5 =  totalsem5Gastos - totalsem5Ingreso;
            difSem5 = difSem5 * -1;
        }
        else {
            difSem5 =  totalsem5Gastos / totalsem5Ingreso;
            difSem5= difSem5 * 100;
            difSem5 = difSem5 -100;
            difSem5 = difSem5 * -1;
        }


if(totalIngresos<1){
    difTotal = totalGastos - totalIngresos ;
    difTotal = difTotal * -1;
}
else {
    difTotal = totalGastos / totalIngresos ;
    difTotal= difTotal * 100;
    difTotal= difTotal -100;
    difTotal = difTotal * -1;

}

        totalMargenRentabilidad= totalMargenRentabilidad + "Margen de rentabilidad              "+
                String.valueOf(difSem1)+"%                      " +
                String.valueOf(difSem2)+"%                   "+difSem3+"%                  "+
                difSem4+"%            "+difSem5+"%                 "+difTotal+"%";


        lvMargendeDiferencia.getItems().add("Margen de rentabilidad            "+
                String.valueOf(difSem1)+"%                    " +
                String.valueOf(difSem2)+"%                 "+difSem3+"%                  "+
                difSem4+"%                "+difSem5+"%");
        if (difTotal<0 || difTotal>0){
            lvFinalMargendeDiferencia.getItems().add(difTotal+"%");
        }


    }
    public void mostrarBancos(){
        String mesSeleccionado = cmbMes.getSelectionModel().getSelectedItem();
        verificarMes(mesSeleccionado);
            bancosDAO.listaBancos();


        ArrayList<String> listaBancos = new ArrayList<String>();
        ArrayList<String> listaMeses = new ArrayList<String>();
        ArrayList<String> listaBancosCompleta = new ArrayList<String>();

        for (int i = 0; i < bancosDAO.listaBancos().size(); i++) {
            String mesCompleto = String.valueOf(bancosDAO.listaBancos().get(i).getFecha());
            String[] mesArreglo;
            mesArreglo = mesCompleto.split("-");
            if(mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                listaBancos.add(bancosDAO.listaBancos().get(i).getDescripcion());
            }
        }
        Set<String> set = new HashSet<>(listaBancos);
        listaBancos.clear();
        listaBancos.addAll(set);
       /* for (int i=0; i<listaCuentasxCobrar.size(); i++){
            System.out.println(listaCuentasxCobrar.get(i));
        }*/
        Double totalSemana1=0.0;
        Double totalSemana2=0.0;
        Double totalSemana3=0.0;
        Double totalSemana4=0.0;
        Double totalSemana5=0.0;
        Double totalMes=0.0;
        for (int i = 0; i < listaBancos.size(); i++) {
            Double semana1 = 0.0;
            Double semana2 = 0.0;
            Double semana3 = 0.0;
            Double semana4 = 0.0;
            Double semana5 = 0.0;
            Double total = 0.0;

            for (int k = 0; k < bancosDAO.listaBancos().size(); k++) {
                String mesCompleto = String.valueOf(bancosDAO.listaBancos().get(k).getFecha());
                String[] mesArreglo;
                mesArreglo = mesCompleto.split("-");
                System.out.println(listaBancos.get(i) + "Va a  comparar con :" + bancosDAO.listaBancos().get(k).getDescripcion());
                if (listaBancos.get(i).equals(bancosDAO.listaBancos().get(k).getDescripcion())) {
                    System.out.println("Si Cumple");
                    if (bancosDAO.listaBancos().get(k).getNdeSemana().equals(1) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                        semana1 = semana1 + bancosDAO.listaBancos().get(k).getMonto();
                    } else {
                        if (bancosDAO.listaBancos().get(k).getNdeSemana().equals(2) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                            semana2 = semana2 + bancosDAO.listaBancos().get(k).getMonto();

                        } else {
                            if (bancosDAO.listaBancos().get(k).getNdeSemana().equals(3) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                semana3 = semana3 + bancosDAO.listaBancos().get(k).getMonto();
                            } else {
                                if (bancosDAO.listaBancos().get(k).getNdeSemana().equals(4) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                    semana4 = semana4 + bancosDAO.listaBancos().get(k).getMonto();
                                } else {
                                    if (bancosDAO.listaBancos().get(k).getNdeSemana().equals(5) && mesArreglo[1].equals(verificarMes(mesSeleccionado))) {
                                        semana5 = semana5 + bancosDAO.listaBancos().get(k).getMonto();
                                    }

                                }
                            }
                        }
                    }

                }
                System.out.println("No cumple");
            }
            total = total + Double.valueOf(semana1);
            total = total + Double.valueOf(semana2);
            total = total + Double.valueOf(semana3);
            total = total + Double.valueOf(semana4);
            total = total + Double.valueOf(semana5);
            listaBancosCompleta.add(listaBancos.get(i) +
                    "                                           $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );

            arrayBancos.add(listaBancos.get(i) +
                    "                                           $ " + semana1 +
                    "                                $ " + semana2 +
                    "                              $ " + semana3 +
                    "                                $ " + semana4 +
                    "                                $ " + semana5 +
                    "                        $ " + total
            );

            lvBancos.getItems().add(listaBancosCompleta.get(i));
            totalSemana1 = totalSemana1 + semana1;
            totalSemana2 = totalSemana2 + semana2;
            totalSemana3 = totalSemana3 + semana3;
            totalSemana4 = totalSemana4 + semana4;
            totalSemana5 = totalSemana5 + semana5;
            totalMes= totalMes + total;
        }
        totalBancosMes = totalBancosMes + "Total Bancos                                 "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5+
                "                              "+"$ "+totalMes;
        lvTotalBancos.getItems().add("Total Bancos                                 "+ "$ "+totalSemana1+
                "                            "+"$ "+totalSemana2+
                "                              "+"$ "+totalSemana3+
                "                              "+"$ "+totalSemana4+
                "                              "+"$ "+totalSemana5
        );
        lvFinalTotalBancos.getItems().add("           $ "+totalMes);
    }

        public String verificarMes(String mesSeleccionado){
         String mesEntero= "";
            if (mesSeleccionado.equals("Enero")) {
                mesEntero = "01";
            } else {
                if (mesSeleccionado.equals("Febrero")) {
                    mesEntero = "02";
                } else {
                    if (mesSeleccionado.equals("Marzo")) {
                        mesEntero = "03";
                    } else {
                        if (mesSeleccionado.equals("Abril")) {
                            mesEntero = "04";
                        } else {
                            if (mesSeleccionado.equals("Mayo")) {
                                mesEntero = "05";
                            } else {
                                if (mesSeleccionado.equals("Junio")) {
                                    mesEntero = "06";
                                } else {
                                    if (mesSeleccionado.equals("Julio")) {
                                        mesEntero = "07";
                                    } else {
                                        if (mesSeleccionado.equals("Agosto")) {
                                            mesEntero = "08";
                                        } else {
                                            if (mesSeleccionado.equals("Septiembre")) {
                                                mesEntero = "09";
                                            } else {
                                                if (mesSeleccionado.equals("Octubre")) {
                                                    mesEntero = "10";
                                                } else {
                                                    if (mesSeleccionado.equals("Noviembre")) {
                                                        mesEntero = "11";
                                                    } else {
                                                        if (mesSeleccionado.equals("Diciembre")) {
                                                            mesEntero = "12";
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return mesEntero;
        }


    public void gestionDeEventos() {
        cmbMes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String valorAnterior, String valorNuevo) {
                if(valorNuevo!=null) {
                    lvCuentasxCobrar.getItems().clear();
                    lvTotalCuentasxCobrar.getItems().clear();
                    lvFinalTotalCuentasxCobrar.getItems().clear();

                    lvCuentasxPagar.getItems().clear();
                    lvTotalCuentasxPagar.getItems().clear();
                    lvFinalTotalCuentasxPagar.getItems().clear();

                    lvIngresos.getItems().clear();
                    lvTotalIngresos.getItems().clear();
                    lvFinalTotalIngresos.getItems().clear();

                    lvGastos.getItems().clear();
                    lvTotalGastos.getItems().clear();
                    lvFinalTotalGastos.getItems().clear();

                    lvBancos.getItems().clear();
                    lvTotalBancos.getItems().clear();
                    lvFinalTotalBancos.getItems().clear();

                    lvDiferencia.getItems().clear();
                    lvFinalDiferencia.getItems().clear();

                    lvMargendeDiferencia.getItems().clear();
                    lvFinalMargendeDiferencia.getItems().clear();

                    totalsem1Ingreso=0.0;
                     totalsem2Ingreso=0.0;
                     totalsem3Ingreso=0.0;
                     totalsem4Ingreso=0.0;
                    totalsem5Ingreso=0.0;

                     totalsem1Gastos=0.0;
                     totalsem2Gastos=0.0;
                     totalsem3Gastos=0.0;
                     totalsem4Gastos=0.0;
                    totalsem5Gastos=0.0;

                     totalIngresos=0.0;
                     totalGastos=0.0;


                     totalMargenRentabilidad="";
                     totalDifencia="";
                     totalCuentasxCobrarMes="";
                     totalCuentasxPagarMes="";
                     totalIngresosMes="";
                     totalGastosMes="";
                     totalBancosMes="";


                    arrayCuentasxCobrar.clear();
                  arrayCuentasxPagar.clear();
                   arrayIngresos.clear();
                   arrayGastos.clear();
                    arrayBancos.clear();


                    mostrarCuentasPorCobrar();
                    mostrarCuentasPorPagar();
                    mostrarIngresos();
                    mostrarGastos();

                    mostrarBancos();
                }
            }
        });
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
