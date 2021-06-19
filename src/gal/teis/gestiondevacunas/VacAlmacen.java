package gal.teis.gestiondevacunas;

import gal.teis.gestiondevacunas.Vacuna;
import java.util.HashMap;
import java.util.Objects;

/**
 * Clase con métodos necesarios para almacenar y gestionar vacunas
 *
 * @author Amaho
 */
public class VacAlmacen {

    /*Primero queremos crear una colección de vacunas.
     Tenemos varias alternativas a la hora de crear colecciones, 
     de modo que, dado que los datos más importantes de las vacunas
     son Strings, vamos a implementar un Hashmap, ya que queremos hacer una coleccion en la que 
     no nos importa ordenar las vacunas de ninguna forma y queremos darle a las vacunas una clave,
     en este caso, su codigo.*/
    //Empezamos definiendo el Hashmap, donde almacenaremos todas las vacunas
    /**
     * Almacen de vacunas con pares de clave (codigo) - valor (vacuna).
     * Generalmente usaremos unicamente el código para acceder a las vacunas
     */
    private final HashMap<String, Vacuna> almacen = new HashMap<String, Vacuna>();

    /*Creo el hashmap como variable de instancia y hago un método para poder acceder a él
    desde otras clases si fuera necesario*/
    public HashMap<String, Vacuna> getAlmacen() {
        return almacen;
    }

    //Ahora definimos los métodos necesarios para la Aplicacion:
    /*Método número 1/11: para listar las vacunas con su información*/
 /*Para implementar este método, dado que estamos utilizando un HashMap que usa
     claves String y valores de tipo Vacuna, podemos utilizar StringBuilder para,
     a medida que recorremos el almacén con un foreach obteniendo todas las vacunas,
     irlas concatenando en una variable que pasamos a String, obteniendo así
     la lista completa.Otra opción es usar directamente los métodos del hashmap, como keySet, 
     que obtiene los codigos de cada vacuna y lo almacena en una variable (en este caso v)
     que al moverse por la coleccion nos proporciona el objeto (de tipo Vacuna) al que
     corresponde esa variable*/
    /**
     * Método para listar todas las vacunas del almacén
     */
    public void verListaVacunasAlmacen() {
        /*Con StringBuilder:
         StringBuilder lista = new StringBuilder();
         for (Vacuna vacuna : almacen.values()) { //Recorremos el almacén buscando objetos de tipo vacuna
         lista.append(vacuna.toString())
         }
         return lista.toString;*/
        //KeySet
        System.out.println("Listando vacunas...");
        if (!almacen.keySet().isEmpty()) {
            for (String v : almacen.keySet()) {
                System.out.println(almacen.get(v).toString());
            }
        } else {
            System.out.println("No se ha añadido ninguna vacuna");
        }

    }

    /*Método número 2/10: Buscar vacuna*/
 /*Volvemos a usar keySet para obtener el codigo
     a partir de la coleccion, y si coincide con el que 
     le pasamos, imprimimos el resultado*/
    /**
     * Método para buscar y mostrar una vacuna del almacén
     *
     * @param codigo
     */
    public boolean buscarVacunaAlmacen(String codigo) {

        boolean encontrada = false;
        System.out.println("Buscando vacuna...");
        if (almacen.containsKey(codigo)) {
            encontrada = true;
            for (String v : almacen.keySet()) {
                System.out.println(almacen.get(v).toString());
            }
        } else {
            System.out.println("Error. No existe ninguna"
                    + " vacuna con el codigo " + codigo);
        }
        return encontrada;

    }

    /*Método número 3/10*/
    /**
     * Método para añadir vacuna_a_añadir a la lista del almacén
     *
     * @param vacuna_a_añadir
     * @return true si la vacuna ha sido agregada al almacén
     */
    public boolean agregarVacunaAlmacen(Vacuna vacuna_a_añadir) {
        boolean agregada = false;
        if (!almacen.containsKey(vacuna_a_añadir.getCodigo())) {
            almacen.put(vacuna_a_añadir.getCodigo(), vacuna_a_añadir);

            agregada = true;

        } else {
            System.out.println("Error. La vacuna con codigo " + vacuna_a_añadir.getCodigo() + " ya ha sido añadida con anterioridad.");
        }
        return agregada;
    }

    /*Método 4/10: */
    /**
     * Método para eliminar vacuna de la lista del almacén
     *
     * @param codigo
     * @return true si la vacuna ha sido eliminada del almacén
     */
    public boolean eliminarVacunaAlmacen(String codigo) {
        boolean eliminada = false;
        if (almacen.containsKey(codigo)) {
            almacen.remove(codigo);
            eliminada = true;
        } else {
            System.out.println("No existe la vacuna con codigo " + codigo);
        }
        return eliminada;

    }

    /*Método 5/10 */
    /**
     *
     * Método que establece el resultado de las fases de la vacuna
     *
     * @param codigo : para identificar que el almacén contiene la vacuna
     * @param numeroFase : la fase que queremos introducir
     * @param estadoFase : resultado que le queremos meter a la fase introducida
     */
    /*Verificamos que el almacen contiene la vacuna. Accedemos a las 
     fases y en función del número de fase introducido, modificamos con
     los setters de VacunaAutorizacion el resultado de la fase correspondiente*/
 /*Método 6/10*/
    /**
     * Método para autorizar o rechazar una vacuna
     *
     * @param codigo Codigo de la vacuna
     * @param tipo : Autorizar y Rechazar (introducir por teclado)
     */
    public String autorizar_rechazar_Vacuna(String codigo, String tipo) {
        StringBuilder mensaje = new StringBuilder();
        boolean autorizada = false;
        boolean rechazada = false;

        System.out.println("Buscando vacuna...");
        for (String v : almacen.keySet()) {
            if (almacen.containsKey(codigo)) {

                mensaje.append(codigo + ": ");
                if (tipo.equalsIgnoreCase("A")) {
                    autorizada = almacen.get(codigo).autorizar();
                    if (autorizada) {
                        mensaje.append(", vacuna autorizada el " + almacen.get(v).getFechaResultado());

                    } else {
                        mensaje.append(",  Vacuna no autorizable");
                    }
                } else if (tipo.equalsIgnoreCase("R")) {
                    rechazada = almacen.get(codigo).rechazar();
                    if (rechazada) {
                        mensaje.append(", rechazada el " + almacen.get(v).getFechaResultado());
                    } else {
                        mensaje.append(", : vacuna no rechazable");
                    }
                }
            } else {
                System.out.println("Error. No existe la vacuna con codigo " + codigo);
            }
        }
        return mensaje.toString();
    }

    /*Método 7/10*/
    /**
     * Método para listar todas las vacunas autorizadas
     */
    public void listarVacunasAutorizadas() {

        int contador = 0;

        System.out.println("Listando vacunas autorizadas...");
        if (!almacen.keySet().isEmpty()) {
            for (String v : almacen.keySet()) {
                if (almacen.get(v).esAutorizada()) {
                    contador++;
                    System.out.println(almacen.get(v).toString());
                }
            }
            System.out.println("Vacunas autorizadas: " + contador);
        } else {
            System.out.println("No hay vacunas");
        }
    }

    /*Método 8/10*/
    /**
     * Método para listar todas las vacunas rechazadas
     */
    public void listarVacunasRechazadas() {

        int contador = 0;

        System.out.println("Listando vacunas rechazadas...");
        if (!almacen.keySet().isEmpty()) {
            for (String v : almacen.keySet()) {
                if (almacen.get(v).esRechazada()) {
                    contador++;
                    System.out.println(almacen.get(v).toString());
                }
            }
            System.out.println("Vacunas rechazadas: " + contador);
        } else {
            System.out.println("No hay vacunas");
        }
    }

    /*Método 9/10*/
    /**
     * Método para listar todas las vacunas pendientes de autorizacion
     */
    public void listarVacunasPendientes() {

        int contador = 0;

        System.out.println("Listando vacunas pendientes...");
        if (!almacen.keySet().isEmpty()) {
            for (String v : almacen.keySet()) {
                if (almacen.get(v).isPendienteAutorizacion()) {
                    contador++;
                    System.out.println(almacen.get(v).toString());
                }
            }
            System.out.println("Vacunas pendientes: " + contador);
        } else {
            System.out.println("No hay vacunas");
        }
    }

    /*Método 10/10*/
    /**
     * Método para obtener el resultado de la última fase investigada de cada
     * vacuna Obtenemos el resultado de la última fase investigada de la vacuna
     * guardada en el almacén
     */
    public void ultimaFaseVacAlmacen() {
        for (String v : almacen.keySet()) {
            System.out.println(v + ", ");
            System.out.println(almacen.get(v).ultimaFase());
        }

    }

    /*Métodos adicionales (Recuperación)*/
    /**
     * Método para modificar los datos de una vacuna
     *
     * @param codigo Codigo de la vacuna
     * @param nom Nombre de la vacuna
     * @param principioActivo Principio de la vacuna
     * @param farmaceutica Compañia farmaceutica
     * @param precio_Recomendado Precio de la vacuna
     * @return
     */
    public String modificarVacuna(String codigo, String nom, String principioActivo, String farmaceutica, double precio_Recomendado) {

        StringBuilder mensaje = new StringBuilder();

        if (almacen.containsKey(codigo)) {
            for (String v : almacen.keySet()) {
                almacen.get(codigo).setNombre(nom);
                almacen.get(codigo).setPrincipio_activo(principioActivo);
                almacen.get(codigo).setFarmaceutica(farmaceutica);
                almacen.get(codigo).setPrecio_recomendado(precio_Recomendado);

            }
        } else {
            mensaje.append("Error. No existe la vacuna con codigo " + codigo);
        }

        return mensaje.toString();
    }

    /**
     * Método para escribir las vacunas en un archivo
     */
    public String ficheroVacunasAlmacen() {
        StringBuilder ficheroVacunas = new StringBuilder();
        if (!almacen.isEmpty()) {

            for (String v : almacen.keySet()) {
                if (almacen.get(v).esAutorizada()) {
                    ficheroVacunas.append("A ");
                }
                if (almacen.get(v).esRechazada()) {
                    ficheroVacunas.append("N ");
                }
                if (almacen.get(v).isPendienteAutorizacion()) {
                    ficheroVacunas.append("P ");
                }
                ficheroVacunas.append("Fecha: " + almacen.get(v).getFechaResultado() + " ");
                ficheroVacunas.append("Codigo: " +almacen.get(v).getCodigo() + " ");
                ficheroVacunas.append("Nombre: " +almacen.get(v).getNombre() + " ");
                ficheroVacunas.append("Principio activo: " +almacen.get(v).getPrincipio_activo() + " ");
                ficheroVacunas.append("Farmaceutica: " +almacen.get(v).getFarmaceutica() + " ");
                ficheroVacunas.append("Precio: " +almacen.get(v).getPrecio_recomendado() + " ");
                ficheroVacunas.append("Fases completadas: " +almacen.get(v).getFasesCompletadas() + " ");
                ficheroVacunas.append("\n");
            }
            System.out.println("Datos de la vacuna almacenados.");
        } else {
            System.out.println("No hay ninguna vacuna");
        }
        return ficheroVacunas.toString();
    }

}
