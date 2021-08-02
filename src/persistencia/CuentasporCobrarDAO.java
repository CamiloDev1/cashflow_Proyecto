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

public class CuentasporCobrarDAO {
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;


    public CuentasporCobrarDAO(){
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
        CuentasporCobrar userRegister = new CuentasporCobrar(0,NdeSemana, RazonSocial, Monto, Fecha);
        session.save(userRegister);
        session.getTransaction().commit();
        session.close();
    }
//Date.valueOf(LocalDate.now()))

    public List<CuentasporCobrar> listaCuentasporCobrar(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(CuentasporCobrar.class);
        ProjectionList cuentasporCobrarLista = Projections.projectionList();
        cuentasporCobrarLista.add(Projections.property("NdeSemana"),"NdeSemana");
        cuentasporCobrarLista.add(Projections.property("RazonSocial"),"RazonSocial");
        cuentasporCobrarLista.add(Projections.property("Monto"), "Monto");
        cuentasporCobrarLista.add(Projections.property("Fecha"), "Fecha");
        criteria.setProjection(cuentasporCobrarLista);

        List<CuentasporCobrar> cuentasporCobrar = new ArrayList<>();
        List<CuentasporCobrar> cuentasporCobrarListaList = criteria.setResultTransformer(new AliasToBeanResultTransformer(CuentasporCobrar.class)).list();
        String[] tabla = new String[cuentasporCobrarListaList.size()];
        int i =0;
        for(Iterator iterator = cuentasporCobrarListaList.iterator(); iterator.hasNext();){
            cuentasporCobrar.add((CuentasporCobrar) iterator.next());
            i++;
        }
        return cuentasporCobrar;
    }

}
