package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dtos.LibroDto;
/**
 * Clase de utilidad que contiene los métodos de paso a DTO
 * ivp
 */
public class ADto {
	
	/**
	 * Método que pasa un resultSet con ... a lista de Entidad
	 * @param resultadoConsulta
	 * ivp
	 * @return lista de libros
	 */
	public ArrayList<LibroDto> resultsALibrosDto(ResultSet resultadoConsulta){
		
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		
		//Leemos el resultado de la consulta hasta que no queden filas
		try {
			while (resultadoConsulta.next()) {
				
				listaLibros.add(new LibroDto(resultadoConsulta.getLong("id_libro"),
						resultadoConsulta.getString("titulo"),
						resultadoConsulta.getString("autor"),
						resultadoConsulta.getString("isbn"),
						resultadoConsulta.getInt("edicion"))
						);
			}
			
			int i = listaLibros.size();
			System.out.println("[INFORMACIÓN-ADto-resultsALibrosDto] Número de libros: "+i);
			
		} catch (SQLException e) {
			System.out.println("[ERROR-ADto-resultsALibrosDto] Error al pasar el result set a lista de LibroDto" + e);
		}
		
		return listaLibros;
		
	}
	
}
