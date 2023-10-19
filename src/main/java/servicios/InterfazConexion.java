package servicios;

import java.sql.Connection;

/**
 * Clase que realiza la conexión a PostgreSQL de forma parametrizada
 * 220923 - ivp
 */
public interface InterfazConexion {

	/**
	 * Método que genera la conexión a partir de la configuración guardada en 
	 * .properties
	 * 220923 - ivp
	 * @return Conexión a postgresql abierta
	 */
	public Connection generaConexion();
	
}
