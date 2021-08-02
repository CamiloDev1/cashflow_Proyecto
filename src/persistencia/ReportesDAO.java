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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReportesDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
    public ReportesDAO(){
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







    public List<CuentasporCobrar> listaCuentasporCobrar(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(CuentasporCobrar.class);
        ProjectionList cuentasporCobrarLista = Projections.projectionList();
        cuentasporCobrarLista.add(Projections.property("NdeSemana"),"NdeSemana");
        cuentasporCobrarLista.add(Projections.property("RazonSocial"),"RazonSocial");
        cuentasporCobrarLista.add(Projections.property("Monto"), "RazonSocial");
        cuentasporCobrarLista.add(Projections.property("Fecha"), "RazonSocial");
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



    public String[] listaSubCategoria(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Categorias.class);
        ProjectionList subcategoriasLista = Projections.projectionList();

        subcategoriasLista.add(Projections.property("SubCategoria"), "SubCategoria");
        criteria.setProjection(subcategoriasLista);

        List<Categorias> subcategoria = new ArrayList<>();
        List<Categorias> subcategoriasList = criteria.setResultTransformer(new AliasToBeanResultTransformer(Categorias.class)).list();
        String[] nombre = new String[subcategoriasList.size()];
        int i =0;
        for(Iterator iterator = subcategoriasList.iterator(); iterator.hasNext();){
            subcategoria.add((Categorias) iterator.next());
            nombre[i] = subcategoria.get(i).getSubCategoria();
            System.out.println(nombre[i]);
            i++;
        }
        return nombre;
    }
}
