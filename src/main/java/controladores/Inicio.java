package controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dtos.LibroDto;
import servicios.ImplConexion;
import servicios.InterfazConexion;
import servicios.ImplCrud;
import servicios.InterfazCrud;
import util.metodosExternos;

/**
 * Clase principal de la aplicación ivp
 */
public class Inicio {

	/**
	 * Método de acceso a la aplicación de consola
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		InterfazConexion cpi = new ImplConexion();
		InterfazCrud crud = new ImplCrud();
		
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		metodosExternos util = new metodosExternos();

		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			util.mostrarMenu(); // mostramos menu
			opcion = sc.nextLine().charAt(0) - '0';
			// control de errores
			while (opcion < 0 || opcion > 4) {

				System.out.println("\n\t\t\t**ERROR**");
				System.out.print("\t\tIntroduce una opcion: ");
				opcion = sc.nextLine().charAt(0) - '0';
			}
			System.out.flush();
			switch (opcion) {

			case 1:
				try {
					Connection conexion = cpi.generaConexion();

					if (conexion != null) {
						System.out.println("---MOSTRAR LIBROS---");
						listaLibros = crud.seleccionaTodosLibros(conexion);
						conexion.close();
						int n = util.CapturaEntero("Desea ver todos los libro (Opcion 1) o ver un libro (Opcion 2)", 1,
								2);
						if (n == 1) {
							for (int i = 0; i < listaLibros.size(); i++)
								System.out.println("\n" + listaLibros.get(i).toString());
						} else {
							System.out.println("\nId libro disponible:");
							for (int i = 0; i < listaLibros.size(); i++)
								System.out.println(listaLibros.get(i).getIdLibro());
							System.out.print("\n¿Qué libro desea ver? Seleccione por id: ");
							int id = sc.nextInt();
							for (int i = 0; i < listaLibros.size(); i++) {
								if(listaLibros.get(i).getIdLibro()==id)
									System.out.println("\n" + listaLibros.get(i).toString());
							}

						}
					}

				} catch (Exception e) {
					System.out.println("[ERROR-Main] Se ha producido un error al ejecutar la aplicación: " + e);
				}
				break;
			case 2:
				try {
					Connection conexion = cpi.generaConexion();

					if (conexion != null) {
						System.out.println("---INSERTAR LIBROS---");
						listaLibros = crud.seleccionaTodosLibros(conexion);
						for (int i = 0; i < listaLibros.size(); i++)
							System.out.println("\n" + listaLibros.get(i).toString());
						crud.opcIDU(conexion, 1);
						conexion.close();
					}

				} catch (Exception e) {
					System.out.println("[ERROR-Main] Se ha producido un error al ejecutar la aplicación: " + e);
				}
				break;
			case 3:
				try {
					Connection conexion = cpi.generaConexion();

					if (conexion != null) {
						System.out.println("---BORRAR LIBROS---");
						listaLibros = crud.seleccionaTodosLibros(conexion);
						for (int i = 0; i < listaLibros.size(); i++)
							System.out.println("\n" + listaLibros.get(i).toString());
						crud.opcIDU(conexion, 2);
						conexion.close();
					}

				} catch (Exception e) {
					System.out.println("[ERROR-Main] Se ha producido un error al ejecutar la aplicación: " + e);
				}
				break;
			case 4:
				try {
					Connection conexion = cpi.generaConexion();

					if (conexion != null) {
						System.out.println("---ACTUALIZA LIBROS---");
						listaLibros = crud.seleccionaTodosLibros(conexion);
						for (int i = 0; i < listaLibros.size(); i++)
							System.out.println("\n" + listaLibros.get(i).toString());
						crud.opcIDU(conexion, 3);
						conexion.close();

					}

				} catch (Exception e) {
					System.out.println("[ERROR-Main] Se ha producido un error al ejecutar la aplicación: " + e);
				}
				break;

			}
			if (opcion != 0) {
				System.out.print("\n\n\tPulsa una tecla para volver al menú... ");
				sc.nextLine();
				System.out.flush();
			}

		} while (opcion != 0);
		System.out.println("\n\tSaliendo de la aplicacion  \n\tPulse cualquier tecla para cerrar el programa");
		sc.nextLine();
	}

}
