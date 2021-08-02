package persistencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.transform.AliasToBeanResultTransformer;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlujodeEfectivoDAO {


    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
    public FlujodeEfectivoDAO(){
        System.err.println("Iniciando conexion");
        try {
            Configuration configuration = new Configuration();
            System.err.println("Leyendo configuracion.");
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public void GuardarDatos(LocalDate Fechas, String Entrada,String Descripcion, String Categoria, String SubCategoria , Double Cantidad, Integer NdeSemana, String Clasificacion){
        Session session = factory.openSession();
        session.beginTransaction();
        FlujodeEfectivo userRegister = new FlujodeEfectivo(0, java.sql.Date.valueOf(Fechas) , Entrada , Descripcion , Categoria, SubCategoria, Cantidad, NdeSemana, Clasificacion );
        session.save(userRegister);
        session.getTransaction().commit();
        session.close();
    }



    public List<FlujodeEfectivo> listaFlujodeEfectivo(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(FlujodeEfectivo.class);
        ProjectionList categoriasLista = Projections.projectionList();
        categoriasLista.add(Projections.property("Fechas"),"Fechas");
        categoriasLista.add(Projections.property("Entrada"),"Entrada");
        categoriasLista.add(Projections.property("Descripcion"),"Descripcion");
        categoriasLista.add(Projections.property("Categoria"), "Categoria");
        categoriasLista.add(Projections.property("SubCategoria"), "SubCategoria");
        categoriasLista.add(Projections.property("Cantidad"), "Cantidad");
        categoriasLista.add(Projections.property("NdeSemana"), "NdeSemana");
        categoriasLista.add(Projections.property("Clasificacion"), "Clasificacion");
        criteria.setProjection(categoriasLista);

        List<FlujodeEfectivo> categoria = new ArrayList<>();
        List<FlujodeEfectivo> categoriasList = criteria.setResultTransformer(new AliasToBeanResultTransformer(FlujodeEfectivo.class)).list();
        String[] tabla = new String[categoriasList.size()];
        int i =0;
        for(Iterator iterator = categoriasList.iterator(); iterator.hasNext();){
            categoria.add((FlujodeEfectivo) iterator.next());
            i++;
        }
        return categoria;
    }


    public void ObtenerFlujo(){

    }


}
