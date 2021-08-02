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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BancosDAO {



    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
    public BancosDAO(){
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

    public void GuardarDatos(Integer NdeSemana, String Descripcion, Double Monto, Date Fecha){
        Session session = factory.openSession();
        session.beginTransaction();
        Bancos userRegister = new Bancos(0,NdeSemana, Descripcion, Monto, Fecha);
        session.save(userRegister);
        session.getTransaction().commit();
        session.close();
    }



    public List<Bancos> listaBancos(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Bancos.class);
        ProjectionList bancosLista = Projections.projectionList();
        bancosLista.add(Projections.property("NdeSemana"),"NdeSemana");
        bancosLista.add(Projections.property("Descripcion"),"Descripcion");
        bancosLista.add(Projections.property("Monto"), "Monto");
        bancosLista.add(Projections.property("Fecha"), "Fecha");
        criteria.setProjection(bancosLista);

        List<Bancos> bancos = new ArrayList<>();
        List<Bancos> bancosList = criteria.setResultTransformer(new AliasToBeanResultTransformer(Bancos.class)).list();
        String[] tabla = new String[bancosList.size()];
        int i =0;
        for(Iterator iterator = bancosList.iterator(); iterator.hasNext();){
            bancos.add((Bancos) iterator.next());
            i++;
        }
        return bancos;
    }





}
