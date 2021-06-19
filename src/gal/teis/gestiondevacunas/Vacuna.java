package gal.teis.gestiondevacunas;

import java.util.Objects;

/**
 * Clase para controlar todo lo referente a las vacunas La autorización se lleva
 * a cabo en {@link VacunaAutorizacion}
 *
 * @author Amaho
 */
public class Vacuna extends VacunaAutorizacion {

    private final String codigo;

    private String nombre;

    private String principio_activo;

    private String farmaceutica;

    private double precio_recomendado;

    //Constructores
    public Vacuna(String codigo) {
        this.codigo = codigo;
    }

    public Vacuna(String codigo, String nombre, String principio_activo, String farmaceutica, double precio_recomendado) {

        this.codigo = codigo;

        this.nombre = nombre;

        this.principio_activo = principio_activo;

        this.farmaceutica = farmaceutica;

        this.precio_recomendado = precio_recomendado;

    }

    //Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrincipio_activo() {
        return principio_activo;
    }

    public void setPrincipio_activo(String principio_activo) {
        this.principio_activo = principio_activo;
    }

    public String getFarmaceutica() {
        return farmaceutica;
    }

    public void setFarmaceutica(String farmaceutica) {
        this.farmaceutica = farmaceutica;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    /**
     * El método equals compara 2 vacunas por su código y nos dice si son
     * iguales
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vacuna other = (Vacuna) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    //getter y setter del precio
    public double getPrecio_recomendado() {

        return precio_recomendado;

    }

    public void setPrecio_recomendado(double precio_recomendado) {

        this.precio_recomendado = precio_recomendado;

    }

    /**
     * Método para validar el código a partir del cual se creará la vacuna
     * Necesita tener una V seguida de una vocal mayúscula, 3 o 4 letras
     * minúsculas, y para terminar, 2 números del 4 al 7 o un único número 8
     *
     * @param codigo
     * @return Si el codigo coincide con la expresion regular dada o no
     */
    public static boolean codigoValido(String codigo) {

        return codigo.matches("^V[AEIOU][a-z]{3,4}([4-7]{2}|8)$");

    }

    /**
     *
     * @return El tipo de mensaje que obtenemos en función de si la vacuna está
     * autorizada o no, con los datos organizados en una cadena de texto
     */
    @Override
    public String toString() {

        /*Hacemos un StringBuilder para introducir cadenas
        de texto consecutivas en su interior.
        Imprimimos el mensaje base, independientemente de que 
        esté o no la vacuna autorizada*/
        StringBuilder mensaje = new StringBuilder();

        mensaje.append("Datos de la vacuna: ");

        mensaje.append("\nCodigo: ");
        mensaje.append(codigo);

        mensaje.append("\nNombre: ");
        mensaje.append(nombre);

        mensaje.append("\nPrincipio activo: ");
        mensaje.append(principio_activo);

        /*Si está autorizada, le añadimos la farmacéutica 
        y el precio. Esto lo controlamos desde VacunaAutorizacion*/
        if (esAutorizada()) {
            mensaje.append("\nFarmaceutica: ");
            mensaje.append(farmaceutica);

            mensaje.append("\nPrecio: ");
            mensaje.append(precio_recomendado);

        } else {
            /*Si no está autorizada, pero tampoco rechazada,
            entonces está pendiente de autorización*/
            if (esRechazada()) {
                mensaje.append("\nVacuna rechazada.\n");
            } else {
                mensaje.append("\nVacuna pendiente de autorización.\n");
            }

        }
        return mensaje.toString();
    }

}
