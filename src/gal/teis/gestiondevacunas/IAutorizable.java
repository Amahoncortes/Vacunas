package gal.teis.gestiondevacunas;

/**
 *Interfaz que provee a {@link VacunaAutorizacion} de los métodos
 * necesarios para autorizar o rechazar una vacuna
 * 
 * @author Amaho
 */
public interface IAutorizable {
boolean autorizar();
 boolean rechazar();
}



