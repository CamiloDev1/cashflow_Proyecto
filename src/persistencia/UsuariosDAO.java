package persistencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.transform.AliasToBeanResultTransformer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuariosDAO {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public UsuariosDAO() {
        System.err.println("Iniciando conexionnxd");
        try {
            Configuration configuration = new Configuration();
            System.err.println("Leyendo configuracion.");
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public boolean BuscarUsuario(String usuario, String contraseña){
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Usuarios.class);

        Criterion nombre = Restrictions.like("Usuario", usuario);
        Criterion contra = Restrictions.like("Contraseña", contraseña);

        LogicalExpression andNombreContra = Restrictions.and(nombre, contra);
        criteria.add(andNombreContra);

        List listaUser = criteria.list();
        Usuarios usuarios = new Usuarios();
        boolean evaluar = true;
        int i =0;
        for(Iterator iterator = listaUser.iterator(); iterator.hasNext();){
            usuarios = (Usuarios) iterator.next();
        }
        if(usuarios.getContraseña().equals(contraseña)&&usuarios.getUsuario().equals(usuario)){
            evaluar = false;
        }
        if(!usuarios.getUsuario().equals(usuario)||!usuarios.getContraseña().equals(contraseña)){
            evaluar=true;
        }
        return evaluar;
    }





}
