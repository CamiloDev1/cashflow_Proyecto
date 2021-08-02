package persistencia;

import javafx.beans.property.*;

import java.util.Date;

public class Bancos {


    private final IntegerProperty idBancos = new SimpleIntegerProperty();
    private final IntegerProperty NdeSemana = new SimpleIntegerProperty();
    private final StringProperty Descripcion = new SimpleStringProperty();
    private final DoubleProperty Monto = new SimpleDoubleProperty();
    private final ObjectProperty<Date> Fecha = new SimpleObjectProperty<>();

    Bancos bancos;

    public Bancos(){

    }

    public Bancos(Integer idBancos, Integer NdeSemana, String Descripcion, Double Monto, Date Fecha ) {
        this.idBancos.set(idBancos);
        this.NdeSemana.set(NdeSemana);
        this.Descripcion.set(Descripcion);
        this.Monto.set(Monto);
        this.Fecha.set(Fecha);
    }


    public Integer getIdBancos() {
        return idBancos.get();
    }
    public void setIdBancos(Integer idBancos) {
        this.idBancos.set(idBancos);
    }
    public IntegerProperty idBancos(){
        return idBancos;
    }


    public Integer getNdeSemana() {
        return NdeSemana.get();
    }
    public void setNdeSemana(Integer NdeSemana) {
        this.NdeSemana.set(NdeSemana);
    }
    public IntegerProperty ndeSemana(){
        return NdeSemana;
    }

    public String getDescripcion(){
        return Descripcion.get();
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion.set(Descripcion);
    }
    public StringProperty descripcion(){ return Descripcion;}

    public Double getMonto() {
        return Monto.get();
    }
    public void setMonto(Double Monto) {
        this.Monto.set(Monto);
    }
    public DoubleProperty monto(){
        return Monto;
    }


    public  Date getFecha(){
        return Fecha.get();
    }
    public void setFecha(Date Fecha) {
        this.Fecha.set(Fecha);
    }
    public ObjectProperty<Date> fecha(){
        return Fecha;
    }


    public void setBancos(Bancos bancos){
        this.bancos = bancos;
    }
    public Bancos getBancos(){
        return bancos;
    }
}
