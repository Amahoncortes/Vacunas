package gal.teis.leerfichero;

import java.awt.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Amaho
 */
public class Ventana extends JFrame {

    //Constructor
    public Ventana() {
        setSize(400, 500);
        setLocationRelativeTo(null);
        setTitle("Trabajo con vacunas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);

    }

    public void mostrarTextoVentana(String texto) {
        setVisible(true); //mostrarVentana
        JLabel textoVentana = new JLabel(texto);
        add(textoVentana);
    }
}
