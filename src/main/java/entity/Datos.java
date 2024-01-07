package main.java.entity;
import javax.persistence.*;
import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Datos {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;
    Session session;
    SessionFactory sessionFactory;

    public Datos() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");

        // Create EntityManager
        entityManager = entityManagerFactory.createEntityManager();

        // Begin transaction
        transaction = entityManager.getTransaction();
        session = entityManager.unwrap(Session.class);
        sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);

    }

    public void addArticulo(String descripcion, Double precio, Double gastos, Integer preparacion) {
        try {
            transaction.begin();
            Articulo articulo = new Articulo();
            articulo.setDescripcion( descripcion);
            articulo.setPvp(precio);
            articulo.setGastosenvio(gastos);
            articulo.setPreparacion(preparacion);
            entityManager.merge(articulo);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Handle exceptions and roll back the transaction if necessary
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }

    public void addPedido(Integer id_cliente, Integer idArticulo, Integer cantidad, String fechaHoraPedido) {

        try {
            transaction.begin();

            if (fechaHoraPedido == null || fechaHoraPedido.trim().isEmpty()) {
                throw new IllegalArgumentException("fechaHoraPedido is null or empty");
            }
            // Parse fechaHoraPedido into a java.util.Date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            java.util.Date parsedDate = dateFormat.parse(fechaHoraPedido);

            // Convert to java.sql.Date
            Date sqlDate = new Date(parsedDate.getTime());


            Pedido pedido = new Pedido();
            pedido.setFecha(sqlDate);
            pedido.setCantidad(cantidad);
            pedido.setIdArticulo(idArticulo);
            pedido.setIdCliente(id_cliente);

            entityManager.persist(pedido);
            entityManager.flush(); // Flush changes to the database

            // Clear the persistence context to detach entities
            entityManager.clear();

            transaction.commit();

            System.out.println("***Pedido correctamente añadido!!***");
            System.out.println(pedido.toString());
        } catch (Exception e) {
            // Handle exceptions and roll back the transaction if necessary
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }



    public void addCliente(String nombre, String domicilio, String email, String nif, Integer tipoCliente) {
        try {
            transaction.begin();
            Cliente cliente = new Cliente();
            cliente.setNif(nif);
            cliente.setDomicilio(domicilio);
            cliente.setEmail(email);
            if (tipoCliente == 1) {
                cliente.setTipoCliente("premium");
                cliente.setTipo(1);
            } else {
                cliente.setTipoCliente("estandar");
                cliente.setTipo(2);
            }
            cliente.setNombre(nombre);
            cliente.setCalcAnual();
            cliente.setDescuentoEnv();

            entityManager.merge(cliente);
            System.out.println("***Cliente Estandar correctamente añadido!!***");
            System.out.println(cliente.toString());
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Handle exceptions and roll back the transaction if necessary
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public List<Articulo> mostrarArticulos() {
        List<Articulo> listArticulo;
        try {
            // get all the objects from Employee table
            listArticulo = entityManager.createNamedQuery("getall", Articulo.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listArticulo;

    }

    public List<Cliente> mostrarClientes() {
        List<Cliente> listaClientes;
        try {
            transaction.begin();
             listaClientes = entityManager.createNamedQuery("getallClientes", Cliente.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return listaClientes;

    }

    public List mostrarClientesEstandar() {
        List<Cliente> clientes = new ArrayList<>();
        List<Cliente> clientesEstandar = new ArrayList<>();

        try {
            transaction.begin();
            clientes = entityManager.createNamedQuery("getallClientes", Cliente.class).getResultList();
            transaction.commit();

            for (Cliente cliente : clientes) {
                if (cliente.getTipo() == 2) {
                    clientesEstandar.add(cliente);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientesEstandar;

    }

    public List mostrarClientesPremium() {
        List<Cliente> clientes = new ArrayList<>();
        List<Cliente> clientesPremium = new ArrayList<>();

        try {
            transaction.begin();
            clientes = entityManager.createNamedQuery("getallClientes", Cliente.class).getResultList();
            transaction.commit();

            for (Cliente cliente : clientes) {
                if (cliente.getTipo() == 1) {
                    clientesPremium.add(cliente);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientesPremium;

    }

    public List<Pedido> mostrarPedidos(){
        List<Pedido> listaPedidos;
        try {
            transaction.begin();
            // get all the objects from Employee table
            listaPedidos = entityManager.createNamedQuery("getallPedidos", Pedido.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return listaPedidos;

    }

    public void eliminarPedido(int numeroPedido)
    {
        try {
            transaction.begin();

            // Use the EntityManager to find the Pedido entity by its id and then remove it
            Pedido pedido = entityManager.find(Pedido.class, numeroPedido);

            if (pedido != null) {
                entityManager.remove(pedido);
                System.out.println("Pedido eliminado correctamente");
            } else {
                System.out.println("Pedido no encontrado con el número: " + numeroPedido);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public List<Pedido> mostrarPedidosPendientes() {

        List<Pedido> pedidosPendientes = new ArrayList<>();
        List<Pedido> pedidos ;

        try {
            transaction.begin();
            pedidos = entityManager.createNamedQuery("getallPedidos", Pedido.class).getResultList();
            transaction.commit();

            for (Pedido pedido : pedidos) {

                Articulo articuloFound = entityManager.find(Articulo.class, pedido.getIdArticulo());

                if (articuloFound != null) {
                    if(articuloFound.getPreparacion() > pedido.calcDiferencia(pedido.getFechaInLocalDateTime(), LocalDateTime.now()))
                         pedidosPendientes.add(pedido);


                } else {
                    System.out.println("Articulo no encontrado con el número: " + pedido.getIdArticulo());
                }

            } } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pedidosPendientes;
    }

    public  List<Pedido> mostrarPedidosPendientes(Integer indexCliente) {
        List<Pedido> pedidosPendientes = new ArrayList<>();
        List<Pedido> pedidos ;

        try {
            transaction.begin();
            pedidos = entityManager.createQuery("SELECT pedido from Pedido pedido where pedido.idCliente = ?1")
                    .setParameter(1, indexCliente)
                    .getResultList();
            transaction.commit();


            for (Pedido pedido : pedidos) {

                Articulo articuloFound = entityManager.find(Articulo.class, pedido.getIdArticulo());

                if (articuloFound != null) {
                    if(articuloFound.getPreparacion() > pedido.calcDiferencia(pedido.getFechaInLocalDateTime(), LocalDateTime.now()))
                        pedidosPendientes.add(pedido);


                } else {
                    System.out.println("Articulo no encontrado con el número: " + pedido.getIdArticulo());
                }

            } } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pedidosPendientes;
    }

    public List<Pedido> mostrarPedidosEnviados() {
        List<Pedido> pedidosEnviados = new ArrayList<>();
        List<Pedido> pedidos;

        try {
            transaction.begin();
            pedidos = entityManager.createNamedQuery("getallPedidos", Pedido.class).getResultList();
            transaction.commit();

             for (Pedido pedido : pedidos) {

                 Articulo articuloFound = entityManager.find(Articulo.class, pedido.getIdArticulo());

                 if (articuloFound != null) {
                     if(articuloFound.getPreparacion() < pedido.calcDiferencia(pedido.getFechaInLocalDateTime(), LocalDateTime.now()))
                         pedidosEnviados.add(pedido);


                 } else {
                     System.out.println("Articulo no encontrado con el número: " + pedido.getIdArticulo());
                 }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pedidosEnviados;
    }

    public List<Pedido> mostrarPedidosEnviados(Integer indexCliente) {
        List<Pedido> pedidosEnviados = new ArrayList<>();
        List<Pedido> pedidos;

        try {
            transaction.begin();
            pedidos = entityManager.createQuery("SELECT pedido from Pedido pedido where pedido.idCliente = ?1")
                    .setParameter(1, indexCliente)
                    .getResultList();
            transaction.commit();


            for (Pedido pedido : pedidos) {

                Articulo articuloFound = entityManager.find(Articulo.class, pedido.getIdArticulo());

                if (articuloFound != null) {
                    if(articuloFound.getPreparacion() < pedido.calcDiferencia(pedido.getFechaInLocalDateTime(), LocalDateTime.now()))
                        pedidosEnviados.add(pedido);


                } else {
                    System.out.println("Articulo no encontrado con el número: " + pedido.getIdArticulo());
                }

            } } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pedidosEnviados;
    }

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

}
