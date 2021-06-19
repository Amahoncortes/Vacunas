package gal.teis.gestiondevacunas;

import gal.teis.gestiondevacunas.IAutorizable;
import java.time.LocalDate;

/**
 * Clase que registra si las distintas fases de la vacuna han sido superadas y
 * autoriza o rechaza una vacuna gracias a los métodos implementados de
 * {@link IAutorizable}
 *
 * @author Amaho
 */
public abstract class VacunaAutorizacion implements IAutorizable {

    //Variables para determinar si las fases han sido superadas
    private boolean fase1Superada, fase2Superada, fase3Superada;

    //Está pendiente de autorizacion?
    private boolean pendienteAutorizacion = true;

    private byte fasesCompletadas = 0;

    //Está autorizada?
    private boolean autorizada;

    //Está rechazada?
    private boolean rechazada;

    //Variable fecha
    LocalDate fechaResultado;

    //Getters y Setters 
    public byte getFasesCompletadas() {
        return fasesCompletadas;
    }

    public void setFasesCompletadas(byte fasesCompletadas) {
        this.fasesCompletadas = fasesCompletadas;
    }

    public boolean isPendienteAutorizacion() {
        return pendienteAutorizacion;
    }

    public boolean isFase1Superada() {
        return fase1Superada;
    }

    /**
     * La fase 1 solo se puede superar si la vacuna se encuentra en fase inicial
     * (0)
     *
     * @param fase1Superada
     */
    public void setFase1Superada(boolean fase1Superada) {
        if (fasesCompletadas == 0) {
            fasesCompletadas++;
            this.fase1Superada = fase1Superada;
        } else {
            System.out.println("Error. Esta fase ya ha sido superada");
        }

    }

    public boolean isFase2Superada() {
        return fase2Superada;
    }

    /**
     * la fase 2 solo se puede superar si hemos superado la fase 1
     *
     * @param fase2Superada
     */
    public void setFase2Superada(boolean fase2Superada) {

        
        if (fase1Superada) {
            fasesCompletadas++;
            this.fase2Superada = fase2Superada;
        } else {
            System.out.println("Error. No se puede acceder a esta fase. Fases anteriores sin completar");
        }

    }

    public boolean isFase3Superada() {
        return fase3Superada;
    }

    /**
     * La fase 3 solo se puede superar si hemos superado todas las anteriores
     *
     * @param fase3Superada
     */
    public void setFase3Superada(boolean fase3Superada) {
       
        if (fasesCompletadas == 2 && fase1Superada && fase2Superada) {
             fasesCompletadas++;
            this.fase3Superada = fase3Superada;
        } else {
            System.out.println("Error. No se puede acceder a esta fase. Fases anteriores sin superar");
        }
    }

    /**
     *
     * @return la fecha actual
     *
     */
    public LocalDate getFechaResultado() {
        return fechaResultado;

    }

    /**
     * Establece la fecha actual
     */
    public void setFechaResultado() {
        fechaResultado = LocalDate.now();
    }

    /**
     * Método que comprueba si una vacuna reune las condiciones necesarias para
     * ser autorizada. Condiciones: No puede haber sido rechazada,
     * fasesCompletadas == 3 y cada faseSuperada debe devolver true
     *
     * @return
     */
    public boolean esAutorizable() {
        boolean autorizable = false;

        if (!rechazada && fasesCompletadas == 3 && isFase3Superada()) {
            autorizable = true;
        }

        return autorizable;
    }

    /**
     * Método que comprueba si una vacuna reune las condiciones necesarias para
     * ser rechazada. Condiciones: No puede haber sido autorizada,
     * fasesCompletadas == 3 y cada faseSuperada debe devolver true
     *
     * @return
     */
    public boolean esRechazable() {
        boolean rechazable = false;

        if (!autorizada && fasesCompletadas == 3 && isFase3Superada()) {
            rechazable = true;
        }

        return rechazable;
    }

    /**
     * Método para autorizar una vacuna
     *
     * @return Si esta autorizada o no
     */
    @Override
    public boolean autorizar() {
        /*Comprobamos que no haya sido autorizada, que esté pendiente de autorizacion
         y que cumple las condiciones*/

        if (pendienteAutorizacion && !autorizada && esAutorizable()) {
            pendienteAutorizacion = false;
            autorizada = true;
            setFechaResultado(); //Recoge el momento en el que la vacuna es autorizada
        }
        return autorizada;
    }

    /**
     * Método para rechazar una vacuna
     *
     * @return Si está rechazada o no
     */
    @Override

    public boolean rechazar() {

        if (pendienteAutorizacion && !rechazada && esRechazable()) {
            pendienteAutorizacion = false;
            rechazada = true;
            autorizada = false;  //Si una vacuna está rechazada, no puede estar autorizada
            setFechaResultado(); //Recoge el momento en el que la vacuna es rechazada
        }
        return rechazada;
    }

    /**
     * Determina si una vacuna está autorizada
     *
     * @return true si está autorizada, false si no
     */
    public boolean esAutorizada() {
        return autorizada;
    }

    /**
     * Determina si una vacuna está rechazada
     *
     * @return true si está rechazada, false si no
     */
    public boolean esRechazada() {

        return rechazada;

    }

    /**
     * Método 10/11: Comprueba el estado de cada fase de la vacuna y nos dice si
     * esta superada o no
     *
     * @return El estado de la ultima fase implementada de la vacuna
     */
    //PREGUNTARLE A ESTHER SI EN LUGAR DE IF/ELSE PUEDO UTILIZAR 
    //EL OPERADOR TERNARIO
    public String ultimaFase() {

        StringBuilder mensaje = new StringBuilder();

        //TODO: añadir el resultado 
        switch (fasesCompletadas) {
            case 0:
                mensaje.append("No hay ninguna fase superada");
                break;
            case 1:
                mensaje.append("Fase 1 completada.Resultado:");
                //OPERADOR TERNARIO??
                String aux = (fase1Superada) ? "superada" : " no superada";
                mensaje.append(aux);

                break;

            case 2:
                mensaje.append("Fase 2 completada.Resultado:");
                //OPERADOR TERNARIO??
                aux = (fase2Superada) ? "superada" : " no superada";
                mensaje.append(aux);
                break;

            case 3:
                mensaje.append("Fase 3 completada. Resultado:");
                //OPERADOR TERNARIO??
                aux = (fase2Superada) ? "superada" : " no superada";
                mensaje.append(aux);
                break;
        }

        return mensaje.toString();
    }

}
