package persistencia;

import javafx.beans.property.*;

import java.util.Date;

public class Cuentasporpagar {



    private final IntegerProperty idCuentasporpagar = new SimpleIntegerProperty();
    private final IntegerProperty NdeSemana = new SimpleIntegerProperty();
    private final StringProperty RazonSocial = new SimpleStringProperty();
    private final DoubleProperty Monto = new SimpleDoubleProperty();
    private final ObjectProperty<Date> Fecha = new SimpleObjectProperty<>();

    Cuentasporpagar cuentasporpagar;

    public Cuentasporpagar(){

    }

    public Cuentasporpagar(Integer idCuentasporpagar, Integer NdeSemana, String RazonSocial, Double Monto, Date Fecha ) {
        this.idCuentasporpagar.set(idCuentasporpagar);
        this.NdeSemana.set(NdeSemana);
        this.RazonSocial.set(RazonSocial);
        this.Monto.set(Monto);
        this.Fecha.set(Fecha);
    }

    public Integer getIdCuentasporpagar() {
        return idCuentasporpagar.get();
    }
    public void setIdCuentasporpagar(Integer idCuentasporpagar) {
        this.idCuentasporpagar.set(idCuentasporpagar);
    }
    public IntegerProperty idCuentasporpagar(){
        return idCuentasporpagar;
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

    public String getRazonSocial(){
        return RazonSocial.get();
    }
    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial.set(RazonSocial);
    }
    public StringProperty razonSocial(){ return RazonSocial;}

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

    public void setCuentasporpagar(Cuentasporpagar cuentasporpagar){
        this.cuentasporpagar = cuentasporpagar;
    }
    public Cuentasporpagar getCuentasporpagar(){
        return cuentasporpagar;
    }
}
