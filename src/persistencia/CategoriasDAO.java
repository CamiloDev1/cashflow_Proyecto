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

public class CategoriasDAO {


    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
    public CategoriasDAO(){
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


   public void GuardarDatos(String Clasificacion, String Categoria, String SubCategoria){
        Session session = factory.openSession();
        session.beginTransaction();
        Categorias userRegister = new Categorias(0,Clasificacion, Categoria, SubCategoria);
        session.save(userRegister);
        session.getTransaction().commit();
        session.close();
    }



    public List<Categorias> listaCategorias(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Categorias.class);
        ProjectionList categoriasLista = Projections.projectionList();

        categoriasLista.add(Projections.property("Clasificacion"),"Clasificacion");
        categoriasLista.add(Projections.property("Categoria"), "Categoria");
        categoriasLista.add(Projections.property("SubCategoria"), "SubCategoria");
        criteria.setProjection(categoriasLista);

        List<Categorias> categoria = new ArrayList<>();
        List<Categorias> categoriasList = criteria.setResultTransformer(new AliasToBeanResultTransformer(Categorias.class)).list();
        String[] tabla = new String[categoriasList.size()];
        int i =0;
        for(Iterator iterator = categoriasList.iterator(); iterator.hasNext();){
            categoria.add((Categorias) iterator.next());
            i++;
        }
        return categoria;
    }


    public String[] listaCategoria(){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Categorias.class);
        ProjectionList categoriaLista = Projections.projectionList();

        categoriaLista.add(Projections.property("Categoria"), "Categoria");
        criteria.setProjection(categoriaLista);

        List<Categorias> categorias = new ArrayList<>();
        List<Categorias> categoriaList = criteria.setResultTransformer(new AliasToBeanResultTransformer(Categorias.class)).list();
        String[] cate = new String[categoriaList.size()];
        int i =0;
        for(Iterator iterator = categoriaList.iterator(); iterator.hasNext();){
            categorias.add((Categorias) iterator.next());
            cate[i] = categorias.get(i).getCategoria();
            System.out.println(cate[i]);
            i++;
        }
        return cate;
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
