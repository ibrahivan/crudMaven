package util;

import java.util.Scanner;

public class metodosExternos {

	//metodo para preguntar si queiere editar algun dato
			public boolean PreguntaSiNo(String p) {
				boolean respuesta = false;
				char tecla;
				boolean error = false;
				Scanner scanner = new Scanner(System.in);

				do {
					error = false;
					System.out.print("\n\n" + p + " (s=Sí; n=No): ");
					// Capturamos la respuesta (una pulsación)
					tecla = scanner.nextLine().charAt(0);
					if (tecla == 's' || tecla == 'S') {
						respuesta = true;
					} else if (tecla == 'n' || tecla == 'N') {
						respuesta = false;
					} else {
						System.out.println("\n\n\t** Error: por favor, responde s o n **");
						error = true;
					}
				} while (error);

				return respuesta;
			}
			
			public void mostrarMenu() {
				System.out.println("\n\t\t----Menú----");
				System.out.println("\n\t\t1. Seleccionar libros");
				System.out.println("\n\t\t2. Insertar libros");
				System.out.println("\n\t\t3. Borrar libros");
				System.out.println("\n\t\t4. Actualizar libros");
				System.out.println("\n\t\t0. Cerrar app");

			}
			
			public int CapturaEntero(String mensaje, int min, int max) {
				Scanner sc = new Scanner(System.in);

				System.out.print(mensaje + " (" + min + ".." + max + "): ");
				int opcion = sc.nextInt();

				while (opcion < min || opcion > max) {
					System.out.println("\tNo has introducido una opción válida.");
					System.out.print("\tVuelve a introducir una opción" + " (" + min + ".." + max + "): ");
					opcion = sc.nextInt();
				}
				return opcion;

			}
}
