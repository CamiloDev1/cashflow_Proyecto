package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categorias {



    private final IntegerProperty idCategorias = new SimpleIntegerProperty();
    private final StringProperty Clasificacion = new SimpleStringProperty();
    private final StringProperty Categoria = new SimpleStringProperty();
    private final StringProperty SubCategoria = new SimpleStringProperty();

    Categorias categorias;


    public Categorias(){

    }


    public Categorias(Integer idCategorias, String Clasificacion, String Categoria, String SubCategoria ) {
        this.idCategorias.set(idCategorias);
        this.Clasificacion.set(Clasificacion);
        this.Categoria.set(Categoria);
        this.SubCategoria.set(SubCategoria);
    }



    public Integer getIdCategorias() {
        return idCategorias.get();
    }
    public void setIdCategorias(Integer idCategorias) {
        this.idCategorias.set(idCategorias);
    }
    public IntegerProperty idCategorias(){
        return idCategorias;
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


    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }
    public Categorias getCategorias() {
        return categorias;
    }
}
