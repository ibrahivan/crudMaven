package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.metodosExternos;
import java.util.Scanner;
import dtos.LibroDto;
import util.ADto;

/**
 * Implementación de la interfaz de consultas sobre Postgresql ivp
 */
public class ImplCrud implements InterfazCrud {
	/**
	 * Meotodo con el que hacemos el select, devuelve una lista actualizada
	 */
	@Override
	public ArrayList<LibroDto> seleccionaTodosLibros(Connection conexionGenerada) {

		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		ADto adto = new ADto();

		try {

			// Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			// Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM gbp_almacen.gbp_alm_cat_libros ORDER BY id_libro ASC");

			// Llamada a la conversión a dtoAlumno
			listaLibros = adto.resultsALibrosDto(resultadoConsulta);
			resultadoConsulta.close();
			declaracionSQL.close();
			

		} catch (SQLException e) {

			System.out.println(
					"[ERROR-ImplCrud-seleccionaTodosLibros] Error generando o ejecutando la declaracionSQL: " + e);
			return listaLibros;

		}
		return listaLibros;

	}

/**
 * Metodo que nos permite hacer el Insert, delete o update, devuelve una lista actualizada con la que iremos trabajando
 */
	@Override
	public void opcIDU(Connection conexionGenerada, Integer opc) {
		// TODO Auto-generated method stub

		PreparedStatement declaracionSQL = null;
		
		Scanner sc = new Scanner(System.in);
		LibroDto libro = new LibroDto();
		metodosExternos util = new metodosExternos();
		
		boolean p;
		switch (opc) {
		
		
		// Inserta datos de un libro
		case 1:
			p = util.PreguntaSiNo("¿Desea insertar algun libro?");
			while (p) {
			
			System.out.println("Título del libro:");
			libro.setTitulo(sc.next());
			System.out.println("Autor del libro:");
			libro.setAutor(sc.next());
			System.out.println("ISBN del libro:");
			libro.setIsbn(sc.next());
			System.out.println("Edicion del libro:");
			libro.setEdicion(sc.nextInt());
			
			try {

				// Se abre una declaración y Se define la consulta de la declaración y se
				// ejecuta
				declaracionSQL = conexionGenerada.prepareStatement(
						"INSERT INTO gbp_almacen.gbp_alm_cat_libros (titulo, autor, isbn, edicion) VALUES (?,?,?,?)");

				// Se insertan los datos
				declaracionSQL.setString(1, libro.getTitulo());
				declaracionSQL.setString(2, libro.getAutor());
				declaracionSQL.setString(3, libro.getIsbn());
				declaracionSQL.setInt(4, libro.getEdicion());
				declaracionSQL.executeUpdate();

				declaracionSQL.close();
				
				
				
				
			} catch (SQLException e) {

				System.out.println("[ERROR-ImplCrud-insertaLibros] Error generando o ejecutando la declaracionSQL: " + e);
			}
			p = util.PreguntaSiNo("\t¿Desea insertar otro libro?");
			
			}
			
			break;
			
		// Borra un libro
		case 2:
			p = util.PreguntaSiNo("¿Desea borrar algun libro?");
			p = util.PreguntaSiNo("¿Estas seguro?");
			while (p) {
			System.out.println("Que libro quiere borrar por su id: ");
			libro.setIdLibro(sc.nextLong());
			try {
				// Se abre una declaración y se define la consulta de la declaración y se
				// ejecuta
				declaracionSQL = conexionGenerada
						.prepareStatement("DELETE FROM gbp_almacen.gbp_alm_cat_libros WHERE id_libro = ?");
				declaracionSQL.setLong(1, libro.getIdLibro());
				declaracionSQL.executeUpdate();

				declaracionSQL.close();
				

			} catch (SQLException e) {

				System.out.println("[ERROR-ImplCrud-borraLibros] Error generando o ejecutando la declaracionSQL: " + e);

			}
			p = util.PreguntaSiNo("Desea borrar algun libro mas?");
			}
			
			break;

		case 3:
			// Actualiza datos de algun libro
			//Paso 1 , preguntar 
			p = util.PreguntaSiNo("\t¿Desea actualizar algun dato?");
			while (p) {
			System.out.print("Ingrese el ID del libro que desea actualizar: ");
			int idAActualizar = sc.nextInt();

			// Paso 2: Leer el atributo a actualizar
			System.out.print("Elija el atributo a actualizar (titulo, autor, isbn, edicion.): ");
			sc.nextLine(); // Limpia el buffer de entrada
			String atributo = sc.nextLine();

			// Paso 3: Leer el nuevo valor del atributo
			System.out.print("Ingrese el nuevo valor para " + atributo.toUpperCase() + ": ");
			String nuevoValor = sc.nextLine();

			try {
				// Se abre una declaración y se define la consulta de la declaración y se
				// ejecuta
				declaracionSQL = conexionGenerada.prepareStatement(
						"UPDATE gbp_almacen.gbp_alm_cat_libros SET " + atributo.toUpperCase() + " = ? WHERE id_libro = ?");
				declaracionSQL.setString(1, nuevoValor);
				declaracionSQL.setInt(2, idAActualizar);
				declaracionSQL.executeUpdate();

				declaracionSQL.close();
				

			} catch (SQLException e) {

				System.out.println(
						"[ERROR-ImplCrud-actualizaLibros] Error generando o ejecutando la declaracionSQL: " + e);

			}
			p = util.PreguntaSiNo("\t¿Desea actualizar algun dato mas?");

		}
		break;
		}
		
	
	}
}


