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

public class CuentasporpagarDAO {
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;


    public CuentasporpagarDAO(){
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

    public void GuardarDatos(Integer NdeSemana, String RazonSocial, Double Monto, Date Fecha){
        Session session = factory.openSession();
        session.beginTransaction();
        Cuentasporpagar userRegister = new Cuentasporpagar(0,NdeSemana, RazonSocial, Monto, Fecha);
        session.save(userRegister);
        session.getTransaction().commit();
        session.close();
    }


    public List<Cuentasporpagar> listaCuentasporpagar(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Cuentasporpagar.class);
        ProjectionList cuentasporpagarLista = Projections.projectionList();
        cuentasporpagarLista.add(Projections.property("NdeSemana"),"NdeSemana");
        cuentasporpagarLista.add(Projections.property("RazonSocial"),"RazonSocial");
        cuentasporpagarLista.add(Projections.property("Monto"), "Monto");
        cuentasporpagarLista.add(Projections.property("Fecha"), "Fecha");
        criteria.setProjection(cuentasporpagarLista);

        List<Cuentasporpagar> cuentasporpagar = new ArrayList<>();
        List<Cuentasporpagar> cuentasporpagarList = criteria.setResultTransformer(new AliasToBeanResultTransformer(Cuentasporpagar.class)).list();
        String[] tabla = new String[cuentasporpagarList.size()];
        int i =0;
        for(Iterator iterator = cuentasporpagarList.iterator(); iterator.hasNext();){
            cuentasporpagar.add((Cuentasporpagar) iterator.next());
            i++;
        }
        return cuentasporpagar;
    }

}
