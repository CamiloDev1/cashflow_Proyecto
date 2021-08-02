package persistencia;

import javafx.beans.property.*;

import java.util.Date;

public class FlujodeEfectivo {



    private final IntegerProperty idFlujodeEfectivo = new SimpleIntegerProperty();
    private final ObjectProperty<Date> Fechas = new SimpleObjectProperty<>();
    private final StringProperty Entrada = new SimpleStringProperty();
    private final StringProperty Descripcion = new SimpleStringProperty();
    private final StringProperty Categoria = new SimpleStringProperty();
    private final StringProperty SubCategoria = new SimpleStringProperty();
    private final DoubleProperty Cantidad = new SimpleDoubleProperty();
    private final IntegerProperty NdeSemana = new SimpleIntegerProperty();
    private final StringProperty Clasificacion = new SimpleStringProperty();
    FlujodeEfectivo flujodeEfectivo;


    public FlujodeEfectivo(){

    }


    public FlujodeEfectivo(Integer idFlujodeEfectivo, Date Fechas, String Entrada,  String Descripcion,  String Categoria, String SubCategoria, Double Cantidad, Integer NdeSemana, String Clasificacion){
        this.idFlujodeEfectivo.set(idFlujodeEfectivo);
        this.Fechas.set(Fechas);
        this.Entrada.set(Entrada);
        this.Descripcion.set(Descripcion);
        this.Categoria.set(Categoria);
        this.SubCategoria.set(SubCategoria);
        this.Cantidad.set(Cantidad);
        this.NdeSemana.set(NdeSemana);
        this.Clasificacion.set(Clasificacion);
    }

    public Integer getIdFlujodeEfectivo() {
        return idFlujodeEfectivo.get();
    }
    public void setIdFlujodeEfectivo(Integer idFlujodeEfectivo) {
        this.idFlujodeEfectivo.set(idFlujodeEfectivo);
    }
    public IntegerProperty idFlujodeEfectivo(){
        return idFlujodeEfectivo;
    }


    public  Date getFechas(){
        return Fechas.get();
    }
    public void setFechas(Date Fechas) {
        this.Fechas.set(Fechas);
    }
    public ObjectProperty<Date> fechas(){
        return Fechas;
    }

    public void setEntrada(String Entrada){
        this.Entrada.set(Entrada);
    }
    public String getEntrada(){
        return Entrada.get();
    }
    public StringProperty entrada(){
        return Entrada;
    }




    public void setDescripcion(String Descripcion){
        this.Descripcion.set(Descripcion);
    }
    public String getDescripcion(){
        return Descripcion.get();
    }
    public StringProperty descripcion(){
        return Descripcion;
    }


    public void setCategoria(String Categoria){
        this.Categoria.set(Categoria);
    }
    public String getCategoria(){
        return Categoria.get();
    }
    public StringProperty categoria(){
        return Categoria;
    }

    public void setSubCategoria(String SubCategoria){
        this.SubCategoria.set(SubCategoria);
    }
    public String getSubCategoria(){
        return SubCategoria.get();
    }
    public StringProperty subCategoria(){
        return SubCategoria;
    }

    public Double getCantidad() {
        return Cantidad.get();
    }
    public void setCantidad(Double Cantidad) {
        this.Cantidad.set(Cantidad);
    }
    public DoubleProperty cantidad(){
        return Cantidad;
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


    public void setClasificacion(String Clasificacion){
        this.Clasificacion.set(Clasificacion);
    }
    public String getClasificacion(){
        return Clasificacion.get();
    }
    public StringProperty clasificacion(){
        return Clasificacion;
    }


    public void setFlujodeEfectivo(FlujodeEfectivo flujodeEfectivo) {
        this.flujodeEfectivo = flujodeEfectivo;
    }
    public FlujodeEfectivo getFlujodeEfectivo() {
        return flujodeEfectivo;
    }



}
