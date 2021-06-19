package gal.teis.leerfichero;

import gal.teis.Libreria.Menu;
import gal.teis.Libreria.ControlData;
import gal.teis.leerfichero.Ventana;
import gal.teis.gestiondevacunas.VacAlmacen;
import gal.teis.gestiondevacunas.Vacuna;
import java.awt.BorderLayout;
import java.util.*;
import java.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Aplicación que se encarga de gestionar las vacunas y las distintas
 * operaciones a realizar
 *
 * @author Amaho
 */
public class Aplicacion {

    //Para poder utilizarlo en el método que pinta el menú
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        boolean finalizar = false;
        VacAlmacen almacen = new VacAlmacen();
        Ventana miVentana = new Ventana();
        Scanner lector = new Scanner(new File("src\\Ficheros\\ficheroVacunas.txt"));

        try {
            System.out.println("APLICACION DE GESTION DE VACUNAS...");
            do {

                System.out.println("PRESIONA ENTER PARA CONTINUAR:");
                entrada.nextLine();

                switch (pintarMenu()) {

                    case 1: //Lista completa de vacunas
                        almacen.verListaVacunasAlmacen();
                        break;

                    case 2:
                        buscarVacuna(almacen);
                        break;

                    case 3: //agregar vacuna
                        agregarVacuna(almacen);
                        break;

                    case 4:
                        eliminarVacuna(almacen);
                        break;

                    case 5:
                        introducirFase(almacen);
                        break;

                    case 6:
                        autorizarVacuna_rechazarVacuna(almacen);
                        break;

                    case 7:
                        vacunasAutorizadas(almacen);
                        break;

                    case 8:
                        vacunasNoAutorizadas(almacen);
                        break;

                    case 9:
                        vacunasPendientes(almacen);
                        break;

                    case 10:
                        ultimaFaseVacunas(almacen);
                        break;

                    case 11:
                        modificarVacuna(almacen);
                        break;

                    case 12:
                        agregar5Vacunas(almacen);
                        break;

                    case 13:
                        guardarVacunasFichero(almacen);
                        break;

                    case 14:
                        mostrarVacunasFichero();
                        break;

                    case 15:
                        finalizar = true;
                        System.out.println("Saliendo...");

                }
            } while (!finalizar);

        } catch (Exception e) {
            System.out.println("Se ha producido un problema " + e.getMessage());
        } finally {
            entrada.close();
        }
    }

    //Método 2: 
    /**
     * Método que busca una vacuna a partir de su código
     *
     * @param almacen
     *
     */
    private static void buscarVacuna(VacAlmacen almacen) {
        String codigo;
        boolean codigoValido = false;

        codigo = validacionCodigo();

        almacen.buscarVacunaAlmacen(codigo);

    }

    //Método 3: 
    /**
     * Método que me añade una vacuna con todos sus parámetros
     *
     * @param almacen instancia de la clase almacén
     */
    private static void agregarVacuna(VacAlmacen almacen) {

        /*Para añadir la vacuna,necesitamos pedirle un codigo al usuario
         y comprobar si es válido según las especificaciones
         de la clase Vacuna. Por lo tanto, comprobamos la validez del codigo
         mediante el metodo codigoValido de Vacuna.
         Si el codigo coincide, Le pasamos el resto de parámetros a la vacuna,
         y si el método que agrega vacunas al almacén es verdadero, damos la operacion
         por válida.*/
        String codigo;
        String nombre;
        String principioActivo;
        String farmaceutica;
        double precio_recomendado;
        boolean codigoValido = false;

        /*Primero introducimos un codigo correcto para la vacuna*/
        codigo = validacionCodigo();

        /*Si el codigo es correcto pedimos el resto de los parametros*/
        System.out.print("Introduce un nombre: ");
        nombre = ControlData.lerString(entrada);

        System.out.print("Introduce un principio activo: ");
        principioActivo = ControlData.lerString(entrada);

        System.out.print("Introduce una farmaceutica: ");
        farmaceutica = ControlData.lerString(entrada);

        System.out.print("Introduce un precio: ");
        precio_recomendado = ControlData.lerDouble(entrada);

        Vacuna vacuna_nueva = new Vacuna(codigo, nombre, principioActivo, farmaceutica, precio_recomendado);

        if (almacen.agregarVacunaAlmacen(vacuna_nueva)) {

            System.out.println("La vacuna ha sido añadida con éxito.");
        }
    }

    //Método 4: 
    /**
     * Método para eliminar una vacuna
     *
     * @param almacen instancia de la clase almacén
     */
    private static void eliminarVacuna(VacAlmacen almacen) {

        String codigo;
        boolean codigoValido = false;

        codigo = validacionCodigo();

        if (almacen.eliminarVacunaAlmacen(codigo)) {
            System.out.println("La vacuna ha sido eliminada con exito.");
        } else {
            System.out.println("Por tanto, no ha podido ser eliminada");
        }

    }

    //Método 5: 
    /**
     * Método para introducir el resultadoFase de una fase de la vacuna
     *
     * @param almacen instancia de la clase almacén
     */
    private static void introducirFase(VacAlmacen almacen) {

        String codigo;
        Vacuna v = null;
        boolean repetir = false;
        boolean codigoValido = false;
        String resultadoFase;
        String confirmacion;

        codigo = validacionCodigo();

        if (almacen.getAlmacen().containsKey(codigo)) {
            v = almacen.getAlmacen().get(codigo);
            do {
                switch (v.getFasesCompletadas()) {

                    case 0:
                        //Pido valor de la fase 1
                        System.out.println("Introduce fase 1 [SI para superada | NO para fallar]");
                        resultadoFase = ControlData.lerString(entrada);
                        if (resultadoFase.equalsIgnoreCase("SI")) //true
                        {
                            System.out.println("Fase 1 superada");
                            v.setFase1Superada(true);

                        } else if (resultadoFase.equalsIgnoreCase("NO")) {
                            System.out.println("Fase 1 no superada");
                            v.setFase1Superada(false);

                        } else {
                            System.out.println("Error de entrada. Introduce SI o NO");
                            resultadoFase = ControlData.lerString(entrada);
                        }

                        break;

                    case 1:
                        if (v.getFasesCompletadas() == 1) {
                            //Pido el valor de la fase 2
                            System.out.println("Introduce fase 2 [SI para superada | NO para fallar]");
                            resultadoFase = ControlData.lerString(entrada);

                            if (resultadoFase.equalsIgnoreCase("SI")) //true
                            {
                                System.out.println("Fase 2 superada");
                                v.setFase2Superada(true);

                            } else if (resultadoFase.equalsIgnoreCase("NO")) {
                                System.out.println("Fase 2 no superada");
                                v.setFase2Superada(false);

                            } else {
                                System.out.println("Error de entrada. Introduce SI o NO");
                                resultadoFase = ControlData.lerString(entrada);
                            }
                            break;

                        }
                    case 2:
                        if (v.getFasesCompletadas() == 2) {
                            //Pido el valor de la fase 3
                            System.out.println("Introduce fase 3 [SI para superada | NO para fallar]");
                            resultadoFase = ControlData.lerString(entrada);

                            if (resultadoFase.equalsIgnoreCase("SI")) //true
                            {
                                System.out.println("Fase 3 superada");
                                v.setFase3Superada(true);

                            } else if (resultadoFase.equalsIgnoreCase("NO")) {
                                System.out.println("Fase 3 no superada");
                                v.setFase3Superada(false);

                            } else {
                                System.out.println("Error de entrada. Introduce SI o NO");
                                resultadoFase = ControlData.lerString(entrada);
                            }
                            break;
                        }

                    case 3:
                        if (v.getFasesCompletadas() == 3) {
                            System.out.println("Todas las fases han sido introducidas");
                            repetir = false;
                        }

                        break;
                }

                if (v.getFasesCompletadas() < 3) {
                    System.out.println("Quieres introducir más fases o no?");
                    confirmacion = ControlData.lerString(entrada);
                    if (confirmacion.equalsIgnoreCase("SI"))//quiere repetir{
                    {
                        repetir = true;

                    } else if (confirmacion.equalsIgnoreCase("NO")) {
                        repetir = false;
                    }
                }

            } while (repetir);
        } else {
            System.out.println("Error. No existe la vacuna con el codigo " + codigo);
        }
    }//Método 6:  

    /**
     * código para autorizar o rechazar una vacuna
     *
     * @param almacen instancia de la clase almacén
     */
    private static void autorizarVacuna_rechazarVacuna(VacAlmacen almacen) {
        String codigo;
        String tipo;
        String mensaje;
        boolean codigoValido = false;

        codigo = validacionCodigo();

        System.out.println("Selecciona tipo de operación [A|utorizar / R|echazar: ");
        tipo = entrada.nextLine();

        mensaje = almacen.autorizar_rechazar_Vacuna(codigo, tipo);
        System.out.println(mensaje);
    }

    //Método 7: 
    /**
     * Introducir código que devuelva una lista de vacunas autorizadas
     *
     * @param almacen instancia de la clase almacén
     */
    private static void vacunasAutorizadas(VacAlmacen almacen) {
        almacen.listarVacunasAutorizadas();
    }

    //Método 8: 
    /**
     * Introducir código que devuelva una lista de vacunas no autorizadas
     *
     * @param almacen instancia de la clase almacén
     */
    private static void vacunasNoAutorizadas(VacAlmacen almacen) {
        almacen.listarVacunasRechazadas();
    }

    //Método 9: 
    /**
     * código que devuelva una lista de vacunas pendientes
     *
     * @param almacen instancia de la clase almacén
     */
    private static void vacunasPendientes(VacAlmacen almacen) {
        almacen.listarVacunasPendientes();
    }

    //Método 10:  
    /**
     * código que devuelva una lista de vacunas indicando su ultima fase
     * completada
     *
     * @param almacen instancia de la clase almacén
     */
    private static void ultimaFaseVacunas(VacAlmacen almacen) {
        almacen.ultimaFaseVacAlmacen();
    }

    //Método 11 (adicional)
    /**
     * Método para modificar una vacuna ya creada
     *
     * @param almacen instancia de la clase almacén
     */
    private static void modificarVacuna(VacAlmacen almacen) {
        String codigo;
        String nombre;
        String mensaje;
        String principioActivo;
        String farmaceutica;
        double precio_Recomendado;
        boolean codigoValido = false;

        codigo = validacionCodigo();

        if (almacen.getAlmacen().containsKey(codigo)) {
            System.out.println("Modifica el nombre: ");
            nombre = ControlData.lerString(entrada);

            System.out.println("modifica el principio activo: ");
            principioActivo = ControlData.lerString(entrada);

            System.out.println("Modifica la farmaceutica");
            farmaceutica = ControlData.lerString(entrada);

            System.out.println("Modifica el precio: ");
            precio_Recomendado = ControlData.lerDouble(entrada);

            mensaje = almacen.modificarVacuna(codigo, nombre, principioActivo, farmaceutica, precio_Recomendado);
            mensaje += "Datos modificados con éxito";
            System.out.println(mensaje);
        } else {
            System.out.println("Error. No existe la vacuna con el codigo " + codigo);
        }

    }

    //Método 12 (adicional)
    /**
     * Método para crear 5 vacunas y añadirlas de forma automática
     *
     * @param almacen instancia de la clase almacén
     */
    private static void agregar5Vacunas(VacAlmacen almacen) {
        for (int i = 0; i < 5; i++) {
            System.out.print("Vacuna -> " + (i + 1) + "\n");
            agregarVacuna(almacen);
        }
    }

    //Método 13 (Swing/Ficheros)
    /**
     * Método para guardar todas las vacunas creadas en un fichero a partir de
     * la lista donde se almacenan
     */
    public static void guardarVacunasFichero(VacAlmacen almacen) {
        String ficheroVacunas;
        ficheroVacunas = almacen.ficheroVacunasAlmacen();

        try {
            FileWriter ficheroSalida = new FileWriter("src\\Ficheros\\ficheroVacunas.txt", true); //Abrimos el flujo de escritura
            BufferedWriter salida = new BufferedWriter(ficheroSalida);//Abrimos el buffer
            salida.write(ficheroVacunas); //Escribimos en el fichero
            salida.newLine();//Insertamos salto de linea
            salida.flush(); // Se cierra el buffer de salida
            System.out.println("Vacunas almacenadas");
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

    }

    //Método 14 (Swing/Ficheros)
    /**
     * Método para mostrar, a través de una ventana las vacunas guardadas en el
     * fichero a partir de la lista del almacén
     */
    public static void mostrarVacunasFichero() {

        //Instancias
        JFrame ventana = new JFrame(); //Creamos la ventana
        JTextArea zonaTexto = new JTextArea(); //Hacemos el area de texto donde se va a mostrar el texto del fichero

        try {
            //Abrimos el archivo 
            FileInputStream fichero = new FileInputStream("src\\Ficheros\\ficheroVacunas.txt");
            //Obtienes el objeto de DataInputStream
            DataInputStream textoFichero = new DataInputStream(fichero);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(textoFichero));
            System.out.println("Mostrando vacunas...");
            zonaTexto.read(entrada, "Leyendo archivo...");

        } catch (Exception ex) {
            System.out.println("Error. No existe el archivo");
        }
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerramos ventana

        ventana.getContentPane().add(zonaTexto, BorderLayout.CENTER);

        ventana.pack();
        ventana.setTitle("Trabajando con vacunas");
        ventana.setVisible(true);
    }

    private static String validacionCodigo() {
        String codigo;
        boolean codigoValido = false;
        do {
            System.out.println("Introduce un codigo: ");
            codigo = entrada.nextLine();
            if (Vacuna.codigoValido(codigo)) {
                codigoValido = true;
            } else {
                System.out.println("Error. Codigo incorrecto");
            }

        } while (!codigoValido);
        return codigo;
    }

    /**
     * Dibujamos un menú en consola con unas opciones determinadas
     */
    static byte pintarMenu() {
        byte opcion = 0;
        boolean correcta;

        System.out.println("\n*********************************************");

        /*Solo sale del while cuando se selecciona una opción correcta en rango y tipo*/
        ArrayList<String> misOpciones = new ArrayList<String>();

        misOpciones.add(" 1.Listar todas las vacunas y mostrar todos sus datos");
        misOpciones.add(" 2.Buscar vacuna.");
        misOpciones.add(" 3.Agregar vacuna.");
        misOpciones.add(" 4.Eliminar vacuna.");
        misOpciones.add(" 5.Introducir resultado de las fases de la vacuna.");
        misOpciones.add(" 6.Autorizar/Rechazar vacuna.");
        misOpciones.add(" 7.Ver vacunas autorizadas.");
        misOpciones.add(" 8.Ver vacunas rechazadas.");
        misOpciones.add(" 9.Ver vacunas pendientes de autorizar/rechazar.");
        misOpciones.add("10.Ver la última fase investigada de cada vacuna almacenada.");
        misOpciones.add("11.Modificar vacuna");
        misOpciones.add("12.Añadir 5 vacunas automáticamente");
        misOpciones.add("13.Crear fichero de vacunas");
        misOpciones.add("14.Mostrar texto de fichero en ventana");
        misOpciones.add("15.Salir");
        /*La clase menu permite imprimir el menú a partir de los datos de un ArrayList<String> y 
         utilizar métodos de control de rango*/
        Menu miMenu = new Menu(misOpciones);

        do {

            miMenu.printMenu();

            System.out.println("Elige una opción: ");
            /*La clase ControlData permite hacer un control del tipo leído*/
            opcion = ControlData.lerByte(entrada);

            /*miMenu.rango() lanza una excepción propia en el caso de que el parámetro opción esté fuera
             del rango posible*/
            correcta = miMenu.rango(opcion);
        } while (!correcta);

        return opcion;

    }

}
