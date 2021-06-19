package gal.teis.Libreria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Amaho
 */
public class Menu {

    private ArrayList<String> opciones = new ArrayList<String>();
    private int numOpciones;

    public Menu(ArrayList opciones) {
        this.opciones = opciones;
        this.numOpciones = opciones.size();
    }

    /**
     * Imprime o menú na pantalla
     */
    public void printMenu() {
        for (int i = 1; i <= opciones.size(); i++) {
            System.out.println(i + ".- " + opciones.get(i - 1));
        }
        
    }

    /**
     * Comproba que o parámetro está dentro do rango de opcións coas que se
     * creou o menú
     *
     * @param op int - valor a analizar como pertencente ou non ao rango de
     * opcións do menú
     * @return boolean - true se está no rango e false en caso contrario
     */
    public boolean rango(byte op) {
        boolean enrango = true;
        if (op < 1 || op > numOpciones) {
            enrango = false;
            System.out.println("\tError: introducir un número dentro de los posibles "
                    + "\n\t\tVuelve a introducir un número \n");
        }
        return enrango;
    }

    /**
     * Lee un n´º por teclado ata que se introduce un nº correcto para o rango
     * establecido polas opcións do menú
     *
     * @param sc Scanner - Instancia de Scanner para ler
     */
    public void rango(Scanner sc) {
        boolean enrango = true;
        byte op = ControlData.lerByte(sc);
        do {
            if (op < 1 || op > numOpciones) {
                enrango = false;
                System.out.println("\tError: introducir un número dentro de los posibles "
                        + "\n\t\tVuelve a introducir un número \n");
            }
        } while (!enrango);
    }
    
    
    
    
}
